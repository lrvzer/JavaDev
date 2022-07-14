package per.study.juc.utils.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample4 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id;
        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;
        private final TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table.toString() + "]");
                taskGroup.done(table);
            }
        }
    }

    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;
        private final Event event;

        public TaskGroup(int size, Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("All of table done in event: " + event.id);
            }
        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name = 'a'> <column name = 'coll' type = 'varchar2'> </table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tables.add(new Table("table-" + event.id + "-" + i, i * 100));
        }
        return tables;
    }

    public static void main(String[] args) {
        Event[] events = new Event[]{new Event(1), new Event(2)};

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup group = new TaskGroup(tables.size(), event);
            for (Table table : tables) {
                Watcher taskWatcher = new TaskBatch(2, group);
                TrustSourceRecordCount trustSourceRecordCount = new TrustSourceRecordCount(table, taskWatcher);
                TrustSourceColumns trustSourceColumns = new TrustSourceColumns(table, taskWatcher);
                executorService.submit(trustSourceRecordCount);
                executorService.submit(trustSourceColumns);
            }
        }
    }

    static class TrustSourceRecordCount implements Runnable {

        private final Table table;
        private final Watcher taskWatcher;

        TrustSourceRecordCount(Table table, Watcher taskWatcher) {
            this.table = table;
            this.taskWatcher = taskWatcher;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            table.targetCount = table.sourceRecordCount;
            taskWatcher.done(table);
        }
    }

    static class TrustSourceColumns implements Runnable {

        private final Table table;
        private final Watcher taskWatcher;
        TrustSourceColumns(Table table, Watcher taskWatcher) {
            this.table = table;
            this.taskWatcher = taskWatcher;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            table.targetColumnSchema = table.sourceColumnSchema;
            taskWatcher.done(table);
        }
    }

}

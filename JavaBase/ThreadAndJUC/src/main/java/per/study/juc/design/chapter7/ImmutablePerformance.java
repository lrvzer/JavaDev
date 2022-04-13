package per.study.juc.design.chapter7;

public class ImmutablePerformance {
    public static void main(String[] args) {
        long startTimestamp = System.currentTimeMillis();
        // Sync 24043
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("Alex");

        // Immutable 20818
        ImmutableObj syncObj = new ImmutableObj("Alex");
        for (long l = 0; l < 10000000; l++) {
            System.out.println(syncObj);
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));
    }
}


final class ImmutableObj {
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

class SyncObj {

    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
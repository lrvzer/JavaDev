package per.study.thread.design.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BalkingData {

    private final String filename;

    private String context;

    private Boolean changed;

    public BalkingData(String filename, String context) {
        this.filename = filename;
        this.context = context;
        this.changed = true;
    }

    public synchronized void change(String newContext) {
        this.context = newContext;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false; /*变化受理结束*/
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + "calls do save, content=" + context);
        try( Writer writer =  new FileWriter(filename, true)) {
            writer.write(context);
            writer.write("\n");
            writer.flush();
        }
    }

}

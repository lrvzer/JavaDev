package per.study.dp.balking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 代表正在编辑的文档类
 * @Author: Lrwei
 * @Date: 2023/6/25
 **/
public class Document {
    // 如果文档发生改变，changed会被设置为true
    private boolean changed = false;

    // 一次需要保存的内容，可以将其理解为内容缓存
    private final List<String> content = new ArrayList<>();

    // 自动保存文档的线程
    private static AutoSaveThread autoSaveThread;

    private final FileWriter writer;

    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath + documentName), true);
    }

    // 静态方法，主要用于创建文档，顺便启动自动保存文档的线程
    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    // 文档编辑，往content队列追加字符串
    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            // 文档改变，changed会变为true
            this.changed = true;
        }
    }

    // 文档关闭的时候首先中断自动保存线程，然后关闭writer释放资源
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    public void save() throws IOException {
        synchronized (this) {
            if (!changed) {
                return;
            }

            System.out.println(Thread.currentThread() + " execute the save action");
            for (String cacheLine : content) {
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
            this.writer.flush();
            // 此刻没有新的内容编辑
            this.changed = false;
            this.content.clear();
        }
    }
}

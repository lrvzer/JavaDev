package per.study.thread.design.chapter18;

public class Test {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThread("Alice", activeObject).start();
        new MakeClientThread("Sgen", activeObject).start();

        new DisplayClientThread("Bobby", activeObject).start();
    }

}

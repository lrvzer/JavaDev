package per.study.thread.design.chapter5;

/**
 * SharedResource
 */
public class Gate {

    private int count = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.count++;
        this.name = name;
        this.address = address;

        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*****BROKEN*****" + this);
        }
    }

    @Override
    public String toString() {
        return "No." + count + ":" + name + "," + address;
    }
}

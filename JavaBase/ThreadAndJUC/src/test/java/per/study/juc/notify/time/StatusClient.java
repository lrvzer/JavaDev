package per.study.juc.notify.time;

public class StatusClient {
    public static void main(String[] args) {
        StatusTest status = new StatusTest();
        status.setStatus(StatusTest.Status.CONNECTED);
        System.out.println(status.getStatus());
    }
}

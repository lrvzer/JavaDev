package per.study.juc.notify.time;

public class StatusTest
{
    private Status status;


    public Status getStatus() {
        if (status == null) {
            setStatus(Status.START);
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    enum Status
    {
        /* 一个线路刚开始状态 */ START, /* 已经接通状态 */CONNECTED, /* 结束状态 */END
    }

}

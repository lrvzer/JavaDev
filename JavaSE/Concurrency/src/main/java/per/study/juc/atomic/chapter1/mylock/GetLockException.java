package per.study.juc.atomic.chapter1.mylock;

public class GetLockException extends Exception{

    public GetLockException() {
        super();
    }

    public GetLockException(String message) {
        super(message);
    }

}

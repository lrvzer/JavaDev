package per.study.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public int sum(int a, int b) {
        return a + b;
    }
}

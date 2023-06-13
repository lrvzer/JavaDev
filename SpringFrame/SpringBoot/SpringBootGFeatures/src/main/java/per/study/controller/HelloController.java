package per.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // 配置中为空时，默认为localhost
    @Value("${app.address:localhost}")
    String address;

    @GetMapping("/address")
    public String getAddress() {
        return address;
    }
}

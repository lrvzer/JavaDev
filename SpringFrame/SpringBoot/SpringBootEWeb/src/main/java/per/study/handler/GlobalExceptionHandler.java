package per.study.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 这个类是集中处理所有@Controller发生的错误
 * @Author: Lrwei
 * @Date: 2023/6/7
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 1.@ExceptionHandler 表示一个方法处理错误，默认只能处理这类发生的指定错误
     * 2.@ControllerAdvice 统一处理所有错误
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e) {
        return "异常原因：" + e.getMessage();
    }
}

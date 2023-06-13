package per.study.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MyYamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper = null; // 把对象转为yaml

    public MyYamlHttpMessageConverter() {
        super(new MediaType("text", "yaml", StandardCharsets.UTF_8)); // 设置支持的类型
        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(factory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 只要是对象类型，不是基本类型
        return true;
    }

    // @RequestBody
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    // @ResponseBody 把对象怎么写出去
    @Override
    protected void writeInternal(Object methodReturnVal, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try (OutputStream body = outputMessage.getBody()) {
            this.objectMapper.writeValue(body, methodReturnVal);
        }
    }
}

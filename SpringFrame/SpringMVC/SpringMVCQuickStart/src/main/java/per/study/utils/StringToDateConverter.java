package per.study.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (source == null)
            throw new RuntimeException("数据不能为空!");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("数据格式错误!");
        }
    }
}

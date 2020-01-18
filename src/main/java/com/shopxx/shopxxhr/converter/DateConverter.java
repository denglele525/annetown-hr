package com.shopxx.shopxxhr.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

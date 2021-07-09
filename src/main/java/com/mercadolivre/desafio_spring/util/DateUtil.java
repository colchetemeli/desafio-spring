package com.mercadolivre.desafio_spring.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtil {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_TIMEZONE = "America/Sao_Paulo";
    public static final String DATE_REGEX = "([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-[0-9]{4}";

    @SneakyThrows
    public static Date stringToDate(String dateString){
        return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    }

    public static String dateToString(Date date){
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date);
    }
}

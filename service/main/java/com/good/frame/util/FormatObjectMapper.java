package com.good.frame.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class FormatObjectMapper extends ObjectMapper {
    public FormatObjectMapper()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setDateFormat(sdf);
    }
}

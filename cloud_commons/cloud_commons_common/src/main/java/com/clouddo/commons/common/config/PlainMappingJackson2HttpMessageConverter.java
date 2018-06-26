package com.clouddo.commons.common.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/5下午2:55
 */
public class PlainMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public PlainMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}

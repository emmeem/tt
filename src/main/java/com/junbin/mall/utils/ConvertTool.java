package com.junbin.mall.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertTool {
    private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    public static <T> T convertObject(Object source, Class<T> destinationClass) {
        return MAPPER.map(source, destinationClass);
    }

    public static <T> List<T> convertList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(MAPPER.map(sourceObject, destinationClass));
        }
        return destinationList;
    }
}

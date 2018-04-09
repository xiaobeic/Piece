package com.jjg.utils;

import org.springframework.beans.BeanUtils;

public class DataObjectConvertUtils {
	public static void copyProperties(Object toObj, Object fromObj) {
		BeanUtils.copyProperties(fromObj, toObj);
	}
}

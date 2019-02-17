package com.astrika.abg.util;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("customStringConverter")
public class CustomStringConverter implements Converter<String, String> {
	
	Logger l=Logger.getLogger(CustomStringConverter.class);

	@Override
	public String convert(String source) {
		source = source.replaceAll("\"", "'");
    	source = source.replaceAll("<", "&lt;");
    	source = source.replaceAll(">", "&gt;");
		return source;
	}

}

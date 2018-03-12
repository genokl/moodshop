package jm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import jm.basic.util.CommonUtils;
/**
 * 自定义参数转换器
 * 日期类型
 * @author wxy
 * @version 1.0
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {   
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(CommonUtils.checkFull(source)){
				return null;
			}else{
				return simpleDateFormat.parse(source);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}

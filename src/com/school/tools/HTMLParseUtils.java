package com.school.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author wk
 * 通过输入jquery选择器进行选择
 *
 */
public class HTMLParseUtils {

	public static Map<String,String> getValues(Map<String,String> patterns,String html)
	{
		Map<String,String> map = new HashMap<String,String>();
		Set<String> setStrings = patterns.keySet();
		Document document = Jsoup.parse(html);
		for(String key : setStrings)
		{
			String select_str = patterns.get(key);
			Elements  elements = document.select(select_str);
			if(elements.hasText())
			{
				Element e = elements.get(0);
			    map.put(key, e.html());
			}
		}
		return map;
	}
	public static Map<String,String> getValuesText(Map<String,String> patterns,String html)
	{
		Map<String,String> map = new HashMap<String,String>();
		Set<String> setStrings = patterns.keySet();
		Document document = Jsoup.parse(html);
		for(String key : setStrings)
		{
			String select_str = patterns.get(key);
			Elements  elements = document.select(select_str);
			if(elements.hasText())
			{
				Element e = elements.get(0);
				if(e.hasText())
				{
					map.put(key, e.text());
				}
			    
			}
		}
		return map;
	}
	public static String getLastElementText(String html,String select)
	{
		Document document = Jsoup.parse(html);
		Elements element = document.select(select);
		int length = element.size();
		if(length<=0)
		{
			System.out.println(length);
			return null;
		}
		else
		{
			return element.get(length-1).html();
		}
	}
	

}

/**
 * 
 */
package com.fanl.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.fanl.pojo.Message;

/**
 * <p>Title:NewsaCrawlService.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019��5��2��
 */
public interface AcademyCrawlService {
	public Map<String,List<String>> academyCraw(Integer id);
	public String lookMore(Integer pid,long id);
}

/**
 * 
 */
package com.fanl.service;

import java.util.List;
import java.util.Map;

import com.fanl.pojo.Message;

/**
 * <p>Title:JwcCrawl.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019��5��1��
 */
public interface JwcCrawlService {
	public List<Message> jwcCraw();
	public String lookMore(long id);
}

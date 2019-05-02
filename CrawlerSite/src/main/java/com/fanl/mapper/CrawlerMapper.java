/**
 * 
 */
package com.fanl.mapper;

import org.springframework.stereotype.Repository;

import com.fanl.pojo.Message;

/**
 * <p>Title:CrawlerMapper.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019Äê5ÔÂ1ÈÕ
 */
@Repository
public interface CrawlerMapper {
	public void insertMessage(Message message);
	public void deleteAllMessage();
	public Message selectMessageById(long id);
}

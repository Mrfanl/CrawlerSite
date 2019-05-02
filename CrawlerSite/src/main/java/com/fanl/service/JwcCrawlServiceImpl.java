/**
 * 
 */
package com.fanl.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanl.mapper.CrawlerMapper;
import com.fanl.pojo.Message;

/**
 * <p>
 * Title:JwcCrawlServiceImpl.java
 * </P>
 * <p>
 * Description
 * </P>
 * 
 * @author ACER
 * @date 2019年5月1日
 */
@Service
public class JwcCrawlServiceImpl implements JwcCrawlService {

	private static String jwcUrl = "http://jwc.ahu.cn";

	@Autowired
	public CrawlerMapper crawlerMapper = null;
	
	/**
	 * 爬取信息摘要
	 */
	public List<Message> jwcCraw() {
		List<Message> list = new ArrayList<Message>();
		try {
			Document doc = Jsoup.parse(new URL(jwcUrl).openStream(), "GBK", jwcUrl);
			Elements es = doc.select("td[title]");
			crawlerMapper.deleteAllMessage();                                                                                                                                  
			for (Element e : es) {
				Elements aTag = e.getElementsByTag("a");
				String href = jwcUrl+"/main/"+aTag.attr("href");
				Message message = new Message();
				message.setDiegest(e.text());
				message.setUrl(href);
			//	crawlerMapper.insertMessage(message);
				list.add(message);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查看讯息详情
	 */
	public String lookMore(long id) {
		Message message = crawlerMapper.selectMessageById(id);
		Document doc = null;
		try {
			doc = Jsoup.parse(new URL(message.getUrl()).openStream(), "GBK", jwcUrl);
			Elements links = doc.select("link[href]");
			for(Element e:links) {
				if(e.attr("href").equals("style.css")) {
					e.attr("href",jwcUrl+"/main/"+e.attr("href"));
				}
			}
			Elements imgs = doc.select("img");
			for(Element e:links) {
				if(e.attr("src").trim().length()!=0&&e.attr("src").charAt(0)=='/') {
					e.attr("src",jwcUrl+"/main/"+e.attr("src"));
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc.toString();
	}

}

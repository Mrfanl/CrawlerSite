/**
 * 
 */
package com.fanl.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.fanl.mapper.CrawlerMapper;
import com.fanl.pojo.Message;

/**
 * <p>Title:NewsaCrawlServiceImpl.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019年5月2日
 */
@Service
public class AcademyCrawlServiceImpl implements AcademyCrawlService {
	
	public static String[] baseUrl = {"http://sjc.ahu.edu.cn","http://cs.ahu.edu.cn","http://wxy.ahu.edu.cn"};
	
	@Autowired
	private CrawlerMapper crawlerMapper = null;
	
	public Map<String,List<String>> academyCraw(Integer id) {
		
		return crawl(baseUrl[id],id);
	}
	
	public String lookMore(Integer pid,long id){
		String bUrl = baseUrl[pid];
		String url = crawlerMapper.selectMessageById(id).getUrl();
		Elements article = null;
		try {
			Document doc = Jsoup.parse(new URL(url).openStream(),"utf-8",bUrl);
			
			Elements imgs = doc.select("img[src]");
			for(Element e:imgs) {
				if(!e.attr("src").trim().equals("")) {
					e.attr("src",bUrl+""+e.attr("src"));
				}
			}
			
			if(pid==0)
				article = doc.select("div#container1");
			else if(pid==1)
				article = doc.select("div[frag=窗口9]");
			else 
				article = doc.select("div[frag=窗口3]");

		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return article.first().html();
		
	}
	
	public Map<String,List<String>> crawl(String url,int id) {
		String[] windowid = {"42","44","13","12","21","31"};
		Map<String,List<String>> map = new LinkedHashMap<String, List<String>>();
		List<String> newsList = new ArrayList<String>();
		List<String> infoList = new ArrayList<String>();
		crawlerMapper.deleteAllMessage();
		try {
			Document doc = Jsoup.parse(new URL(url).openStream(), "utf-8", url);
			Element div1 = doc.select("div[frag=窗口"+windowid[id*2]+"]").first();
			Elements as1 = div1.select("a[title]");
			for(Element e:as1) {
				newsList.add(e.text());
				Message m = new Message();
				m.setDiegest(e.text());
				m.setUrl(e.attr("abs:href"));
				crawlerMapper.insertMessage(m);
			}
			
			Element div2 = doc.select("div[frag=窗口"+windowid[id*2+1]+"]").first();
			Elements as2 = div2.select("a[title]");
			for(Element e:as2) {
				infoList.add(e.text());
				Message m = new Message();
				m.setDiegest(e.text());
				m.setUrl(e.attr("abs:href"));
				crawlerMapper.insertMessage(m);
			}
			map.put("news",newsList);
			map.put("info", infoList);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}

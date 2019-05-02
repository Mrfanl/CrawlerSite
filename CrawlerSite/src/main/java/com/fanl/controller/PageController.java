/**
 * 
 */
package com.fanl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanl.pojo.Message;
import com.fanl.service.JwcCrawlService;
import com.fanl.service.AcademyCrawlService;

/**
 * <p>Title:PageController.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019Äê5ÔÂ1ÈÕ
 */
@Controller
public class PageController {
	
	@Autowired
	private JwcCrawlService jwcCrawlService = null;
	@Autowired
	private AcademyCrawlService academyCrawlService = null;
	
	@RequestMapping("/index")
	public String index() {
		return "page";
	}
	@RequestMapping("/newsacademy")
	public String newsacademy() {
		return "page";
	}
	@RequestMapping("/computeracademy")
	public String computeracademy() {
		return "page";
	}
	@RequestMapping("/literatureacademy")
	public String literatureacademy() {
		return "page";
	}
	
	
	@RequestMapping("/jwc/crawl")
	public @ResponseBody
	List<Message> jwcCrawl(){
		System.out.println(11);
		List<Message> list = jwcCrawlService.jwcCraw();
		return list;
	}
	@RequestMapping("/academy/crawl")
	public @ResponseBody 
	Map<String,List<String>> newsaCrawl(Integer id){
		
		return academyCrawlService.academyCraw(id);
	}
	
	@RequestMapping("/jwc/lookmore")
	public @ResponseBody
	String lookMore(long id) {
		String more = jwcCrawlService.lookMore(id);
		return more;
	}
	@RequestMapping(value="/academy/lookmore")
	public @ResponseBody
	Map<String,String> acaLookMore(Integer pid,Long id) {
		System.out.println(pid+" "+id);
		Map<String,String> map  = new HashMap<String, String>();
		
		 map.put("article", academyCrawlService.lookMore(pid, id));
		 return map;
	}

}

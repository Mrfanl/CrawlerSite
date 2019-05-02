/**
 * 
 */
package com.fanl.pojo;

/**
 * <p>Title:Message.java</P>
 * <p>Description</P>
 * @author ACER
 * @date 2019Äê5ÔÂ1ÈÕ
 */
public class Message {
	private Long id;
	private String diegest;
	private String url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiegest() {
		return diegest;
	}
	public void setDiegest(String diegest) {
		this.diegest = diegest;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", diegest=" + diegest + ", url=" + url + "]";
	}
	
	
}

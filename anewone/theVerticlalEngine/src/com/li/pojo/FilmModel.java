package com.li.pojo;

public class FilmModel {

	//电影编号
	private String id;
	//电影名
	private String fname;
	//评分
	private Float score;
	//图片链接
	private String src;
	//链接
	private String href;
	//评论
	private String comment;
	//评论链接
	private String c_src;
	//关键字
	private String key_word;
	
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getC_src() {
		return c_src;
	}
	public void setC_src(String c_src) {
		this.c_src = c_src;
	}
	
	
	//source->Generate Getters and Setters
	
}

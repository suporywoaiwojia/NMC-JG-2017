package com.menksoft.cms.freemarker.Cms.entity;

import java.util.Date;


/**
 * @author 呼和
 */
public class CmsContents {
private long id;
	private String columnId;
	//标题
	private String title;
	//摘要
	private String summary;
	//作者
	private String author;
	//来源
	private String source;
	//浏览权限
	private String views;
	//置顶
	private String putTop;
	//推荐
	private String recommend;
	//发布日期
	private Date publishDate;
	//创建日期
	private Date creatDate;

	private String titleColor;


	//封面路径
	private String coverPath;
	//封面名称
	private String coverName;
	//封面描述
	private String coverDescribe;
	//文档路径
	private String documentPath;
	//文档名称
	private String documentName;
	
	private String url;
	private String hits;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getPutTop() {
		return putTop;
	}

	public void setPutTop(String putTop) {
		this.putTop = putTop;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getCoverName() {
		return coverName;
	}

	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}

	public String getCoverDescribe() {
		return coverDescribe;
	}

	public void setCoverDescribe(String coverDescribe) {
		this.coverDescribe = coverDescribe;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocDownPath() {
		return docDownPath;
	}

	public void setDocDownPath(String docDownPath) {
		this.docDownPath = docDownPath;
	}

	public String getDocSavePath() {
		return docSavePath;
	}

	public void setDocSavePath(String docSavePath) {
		this.docSavePath = docSavePath;
	}

	public String getDocumentDescribe() {
		return documentDescribe;
	}

	public void setDocumentDescribe(String documentDescribe) {
		this.documentDescribe = documentDescribe;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//文档下载路径
	private String docDownPath;
	//文档存储路径
	private String docSavePath;
	//文档描述
	private String documentDescribe;
	//内容类型 1新闻 2图片 3产品 4视频 5文章 6文库 7音频
	private String contentType;

	//上传类型
	private String username;
	
}

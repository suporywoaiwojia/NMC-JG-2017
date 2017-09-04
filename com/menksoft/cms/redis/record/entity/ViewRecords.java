package com.menksoft.cms.redis.record.entity;

public class ViewRecords{
	//序列ID
	private String id;
	//会员ID
	private String memberid;
	//观看标题
	private String title;
	//观看日期
	private String  viewdate;
	//内容地址
	private String url;
	//观看次数
	private String num;
	//内容封面
	private String coverPath;
	//内容ID
	private long contentId;
	
	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViewdate() {
		return viewdate;
	}

	public void setViewdate(String viewdate) {
		this.viewdate = viewdate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	
	
}

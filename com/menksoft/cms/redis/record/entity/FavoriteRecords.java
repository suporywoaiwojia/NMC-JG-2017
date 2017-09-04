package com.menksoft.cms.redis.record.entity;

public class FavoriteRecords{
	//序列ID
	private String id;
	//会员ID
	private String memberid;
	//收藏标题
	private String title;

	//内容地址
	private String url;

	//内容封面
	private String coverPath;
	//内容id
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


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public void setCoverPath(String coverpath) {
		this.coverPath = coverpath;
	}
	
}

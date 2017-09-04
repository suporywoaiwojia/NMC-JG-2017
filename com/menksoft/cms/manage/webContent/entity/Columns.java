/**
 * 
 */
package com.menksoft.cms.manage.webContent.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.manage.data.entity.Content;
import com.menksoft.cms.manage.data.entity.ItemContent;

/**
 * 站点栏目管理
 * @author lenovo
 *
 */
/**
 * @author 呼和
 */
@Entity(name = "r_tab_column")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "parent" })
public class Columns{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column
	private long id;
	// 上级栏目标识
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_column_parent")
	@Index(name = "index_column_parent")
	private Columns parent;
	
	//通用栏目下的内容
    @OneToMany(fetch = FetchType.LAZY,mappedBy="columnContents")
    private List<Content>  contents;
   

	// 名称
	@Column(length = 30)
	private String name;
	// 子节点网页访问路径
	@Column(length = 100)
	private String columnPath;
	//子节点action访问路径
	@Column(length = 50)
	private String actionPath;

	// 栏目模版
	@Column(length = 100)
	private String listModel;
	// 内容模版
	@Column(length = 100)
	private String contentModel;
	// 专辑模版
	@Column(length = 100)
	private String albumModel;
	// 页数
	@Column
	private Integer pageNum = 0;
	// 排序
	@Column
	private Integer columnOrder;
	// 审核开关 0关闭 1开启
	@Column(length = 1)
	private String approve;
	
	// 浏览开关 0私有 1部分公开 2公开 3订阅
	@Column(length = 2)
	private String views;
	
	// 状态   0关闭 1开启
	@Column(length = 1)
	private String state;
	// 栏目父节点的节点路径(标识栏目的层级路径)
	@Column(length = 100)
	@Index(name = "index_column_parentPath")
	private String parentPath;
	
	//项目编码
	@Column(length = 4)
	private String no;
	@Column(length = 50)
	private String tablename;
	@Column(length = 2)
	private String type;
	
	@Column(length = 100)//二级模版
	private String secondModel;
	//栏目类型
	@Column(length = 10)
	private String columnType;
	
	 //栏目下的附件保存地址
	@Column(length=200)
	private String conFilesSavPath;
	
	public String getConFilesSavPath() {
		return conFilesSavPath;
	}

	public void setConFilesSavPath(String conFilesSavPath) {
		this.conFilesSavPath = conFilesSavPath;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getSecondModel() {
		return secondModel;
	}

	public void setSecondModel(String secondModel) {
		this.secondModel = secondModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}


	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public String getAlbumModel() {
		return albumModel;
	}

	public void setAlbumModel(String albumModel) {
		this.albumModel = albumModel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Columns getParent() {
		return parent;
	}

	public void setParent(Columns parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getColumnPath() {
		return columnPath;
	}

	public void setColumnPath(String columnPath) {
		this.columnPath = columnPath;
	}

	public String getListModel() {
		return listModel;
	}

	public void setListModel(String listModel) {
		this.listModel = listModel;
	}

	public String getContentModel() {
		return contentModel;
	}

	public void setContentModel(String contentModel) {
		this.contentModel = contentModel;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}


	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}



	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	
}
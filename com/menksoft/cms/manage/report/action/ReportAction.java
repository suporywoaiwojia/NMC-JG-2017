package com.menksoft.cms.manage.report.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.menksoft.cms.manage.report.service.ReportService;



@Controller
public class ReportAction {
	@Resource
	ReportService rService;
	/**
	 * 项目信息统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/project/{language}", method=RequestMethod.GET)
	public String getProrePort(ModelMap modelMap, @PathVariable("language")  String language){
		List<Object[]> list_l=new ArrayList<Object[]>();
		List<Object[]> list_p=new ArrayList<Object[]>();
		List<Object[]> list=new ArrayList<Object[]>();
		String levelsql="select id,name from r_data_level order by bm";
		String prosql1="select a.name,count(b.id) from r_tab_column a left join ";
		String prosql3=" on a.id=b.column_id where a.columntype='3' group by a.no order by a.no";
		try {
			list_l=rService.getListreportBysql(levelsql);
			for(Object[] obj_l:list_l){
				String prosql2="(select column_id,id from r_tab_project where level_id="+obj_l[0]+" and state in(3,5)) b";
				list_p=rService.getListreportBysql(prosql1+prosql2+prosql3);
				Object[] obj=new Object[11];
				obj[0]=obj_l[1];
				for(int a=1;a<obj.length;a++){
					obj[a]=list_p.get(a-1)[1];
				}
				list.add(obj);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return "manage/report/pro_report.jsp";
	}
	/**
	 * 歌曲信息统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/song/{language}", method=RequestMethod.GET)
	public String getsong(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();//曲目
		List<Object[]> list_q=new ArrayList<Object[]>(); //曲谱
		List<Object[]> list_l=new ArrayList<Object[]>(); //录音
		String sql="";String sql1="";String sql2="";String sql3="";String sql4="";
		int gc=0;int cs=0;
		try {
//			String sql="select a.name,count(b.id) from r_tab_song b left join r_tab_project a on a.id=b.project_id group by b.project_id";
			sql="select CASE type WHEN '1' THEN '长调' WHEN '2' THEN '马头琴' WHEN '3' THEN '呼麦' end as name,count(*) from r_tab_song  where state in (3,5) group by type order by type";
			list=rService.getListreportBysql(sql);
			//查询状态为通过的曲目ID
			String sql5="select id from r_tab_song where state in(3,5)";
			sql1="select CASE kindtype WHEN '1' THEN '长调' WHEN '2' THEN '马头琴' WHEN '3' THEN '呼麦' end as name,count(*) from r_tab_content_file where type=3 and filetype='02' and contentId in ("+sql5+") group by kindtype order by kindtype";
			list_q=rService.getListreportBysql(sql1);
			sql2="select CASE kindtype WHEN '1' THEN '长调' WHEN '2' THEN '马头琴' WHEN '3' THEN '呼麦' end as name,count(*) from r_tab_content_file where type=3 and filetype='03' and contentId in ("+sql5+") group by kindtype order by kindtype";
			list_l=rService.getListreportBysql(sql2);
			sql3="select case when  sum(length(word)-length(replace(word,'\\r\\n',''))) is null then 0 when  sum(length(word)-length(replace(word,'\\r\\n',''))) is not null then  sum(length(word)-length(replace(word,'\\r\\n',''))) end from r_tab_lyrics where song_id in("+sql5+")";
			gc=rService.getIntreportBysql(sql3);
			sql4="select count(*)from r_tab_legend  where song_id in("+sql5+")";
			cs=rService.getIntreportBysql(sql4);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		modelMap.put("list_q", list_q);
		modelMap.put("list_l", list_l);
		modelMap.put("gc", gc);
		modelMap.put("cs", cs);
		return "manage/report/song_report.jsp";
	}
	/**
	 * 歌曲 曲谱 歌词 传说  音频数量统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/song/{type}/{language}", method=RequestMethod.GET)
	public String getsongType(ModelMap modelMap,  @PathVariable("type")  String type,@PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();//曲目
		String sql="";
		String title="";
		try {
			if(type.equals("2")){// 长调
				sql="select a.name,count(b.id) from r_data_songstyle a left join r_tab_song b  on a.id=b.songstyle_id where b.type='1' and b.state in(3,5) group by a.id";
				title="长调风格统计";
			}else if(type.equals("3")){// 录音
				sql="select a.name,count(b.id) from r_data_hmstyle a left join r_tab_song b  on a.id=b.hmstyle_id where b.type='3' and b.state in(3,5) group by a.id;";
				title="呼麦风格统计";
			}else if(type.equals("4")){//歌词
				sql="select a.name,count(b.id) from r_data_mtstyle a left join r_tab_song b  on a.id=b.mtstyle_id where b.type='2' and b.state in(3,5) group by a.id;";
				title="马头琴风格统计";
			}
			list=rService.getListreportBysql(sql);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		modelMap.put("title", title);
		return "manage/report/song_style_report.jsp";
	}
	/**
	 * 传承人信息统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/inheritor/{language}", method=RequestMethod.GET)
	public String getInheritor(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list_rs=new ArrayList<Object[]>();//人数
		List<Object[]> list=new ArrayList<Object[]>();//人数
		List<Object[]> list_jj=new ArrayList<Object[]>();//简介
		List<Object[]> list_zp=new ArrayList<Object[]>();//照片
		List<Object[]> list_sbp=new ArrayList<Object[]>();//申报片
		List<Object[]> list_sbw=new ArrayList<Object[]>();//申报文本
		String sql="";
		String title="";
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		try {
			String sql5="select id from r_tab_inheritor where state in(3,5) ";
			sql="select a.name,count(b.id) from r_tab_column a left join (select * from r_tab_inheritor where state in(3,5)) b on b.column_id=a.id where a.columnType='3' group by a.no order by a.no";
			list_rs=rService.getListreportBysql(sql);
			sql1="select a.name,count(b.id) from r_tab_column a left join (select * from r_tab_inheritor where (summary!=null or summary!='') and state in(3,5) ) b on b.column_id=a.id where  a.columnType='3'  group by a.no order by a.no";
			list_jj=rService.getListreportBysql(sql1);
			sql2="select a.name ,count(b.id) from r_tab_column a left join (select * from r_tab_content_file where type='2' and fileType='01' and contentId in("+sql5+")) b on b.cultural=a.no where  a.columnType='3'  group by a.no order by a.no";
			list_zp=rService.getListreportBysql(sql2);
			sql3="select a.name ,count(b.id) from r_tab_column a left join (select * from r_tab_content_file where type='2' and fileType='02' and contentId in("+sql5+")) b on b.cultural=a.no where  a.columnType='3'  group by a.no order by a.no";
			list_sbw=rService.getListreportBysql(sql3);
			sql4="select a.name ,count(b.id) from r_tab_column a left join (select * from r_tab_content_file where type='2' and fileType='03' and contentId in("+sql5+")) b on b.cultural=a.no where  a.columnType='3'  group by a.no order by a.no";
			list_sbp=rService.getListreportBysql(sql4);
			 int count=rService.getIntreportBysql("select count(*) from r_tab_inheritor where  state in(3,5)");
			 for(int a=0;a<list_rs.size();a++){
				 Object[] obj=new  Object[3];
					obj[0]=list_rs.get(a)[0];
					obj[1]=list_rs.get(a)[1];
					if(count==0)
						obj[2]="0%";
					else{
						 NumberFormat numberFormat = NumberFormat.getInstance();  
						 numberFormat.setMaximumFractionDigits(2);
						 int v=Integer.valueOf(obj[1].toString());
						 String result = numberFormat.format(((float)v/ (float) count * 10000)/100);  
						
						obj[2]=result+"%";
					}
					list.add(obj);	
			 }
			 
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list_sbw", list_sbw);
		modelMap.put("list_sbp", list_sbp);
		modelMap.put("list_zp", list_zp);
		modelMap.put("list_jj", list_jj);
		modelMap.put("list", list);
		modelMap.put("title", title);
		return "manage/report/in_report.jsp";
	}
	
	/**
	 * 传承人性别 级别 民族统计
	 * @param modelMap
	 * @param type
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/inheritor/{type}/{language}", method=RequestMethod.GET)
	public String getInheritorType(ModelMap modelMap,  @PathVariable("type")  String type,@PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();//曲目
		String sql="";
		String title="";
		try {
			String sql2="select * from r_tab_inheritor where state in(3,5)";
			if(type.equals("2")){// 性别
				sql="select case sex when '1' then '男' when '2' then '女' end as sex, count(*) from r_tab_inheritor where state in(3,5) group by sex";
				title="传承人性别统计";
			}else if(type.equals("3")){// 级别
				sql="select a.name,count(b.id) from r_data_level a left join ("+sql2+") b on a.id=b.level_id group by a.id";
				title="传承人级别统计";
			}else if(type.equals("4")){// 民族
				sql="select a.name,count(b.id) from r_data_naction a left join ("+sql2+")b on a.id=b.naction_id group by a.id";
				title="传承人民族统计";
			}
			list=rService.getListreportBysql(sql);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		modelMap.put("title", title);
		return "manage/report/in_type_report.jsp";
	}
	/**
	 * 传承人年龄统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/inheritor_age/{language}", method=RequestMethod.GET)
	public String getInheritorforAge(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();//80+
		List<Object[]> list_7=new ArrayList<Object[]>();//70
		List<Object[]> list_6=new ArrayList<Object[]>();//60
		List<Object[]> list_5=new ArrayList<Object[]>();//50
		List<Object[]> list_4=new ArrayList<Object[]>();//40
		List<Object[]> list_3=new ArrayList<Object[]>();//30
		List<Object[]> list_2=new ArrayList<Object[]>();//30-
	
		String sql_age="select * from r_tab_inheritor where  CEILING(datediff(case  when born_e is null then CURDATE() when born_e='' then CURDATE() else born_e end,born_s)/365) ";
		String sql="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+">=80 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql2="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+"<30 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql3="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+" between 30 and 39 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql4="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+" between 40 and 49 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql5="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+" between 50 and 59 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql6="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+" between 60 and 69 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		String sql7="select a.name ,count(b.id) from r_tab_column a left join ("+sql_age+" between 70 and 79 and state in(3,5)) b on a.no=b.cultural where a.columnType='3' group by a.no order by a.no";
		int count=0;
		try {
			list=rService.getListreportBysql(sql);
			
			list_2=rService.getListreportBysql(sql2);
			list_3=rService.getListreportBysql(sql3);
			list_4=rService.getListreportBysql(sql4);
			list_5=rService.getListreportBysql(sql5);
			list_6=rService.getListreportBysql(sql6);
			list_7=rService.getListreportBysql(sql7);
			count=rService.getIntreportBysql("select count(*) from r_tab_inheritor where state in(3,5)");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list_7", list_7);
		modelMap.put("list_6", list_6);
		modelMap.put("list_5", list_5);
		modelMap.put("list_4", list_4);
		modelMap.put("list_3", list_3);
		modelMap.put("list_2", list_2);
		modelMap.put("list", list);
		modelMap.put("count", count);
		return "manage/report/in_age_report.jsp";
	}
	/**
	 * 出版情况统计
	 * @param modelMap
	 * @param type
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/pub/{type}/{language}", method=RequestMethod.GET)
	public String getPub(ModelMap modelMap,  @PathVariable("type")  String type,@PathVariable("language")  String language){
		List<Object[]> list=new ArrayList<Object[]>();//年份显示
		List<Object[]> publish=new ArrayList<Object[]>();//出版社信息
		List<String> year=new ArrayList<String>();//年份显示
		List<Object[]> zj=new ArrayList<Object[]>();//总计
		int albumNum=0;
		List<Object[]> count=new ArrayList<Object[]>();//统计数
		String title="";
		String sql="";
		String sql2="";
		String url="";
		String sql3="";		
		try {
			String sql5="select id from r_tab_publication where state in(3,5)";
			if(type.equals("1")){// 出版物信息
				sql="select case type when '01' then '专著' when '02' then '论文' when '03' then '曲集' when '04' then '出版物' when '05' then '报道' end as type, count(*) from r_tab_publication where  state in(3,5) group by type order by type";
				title="出版物信息统计";
				
				list=rService.getListreportBysql(sql);
				sql2="select count(*) from r_tab_content_file where type='4' and filetype='03' and kindtype='04' and contentId in("+sql5+")";
				albumNum=rService.getIntreportBysql(sql2);
				url="manage/report/pub_report.jsp";
			}else if(type.equals("2")){// 出版情况
				sql="select pubyear,publishing_id,count(*) from r_tab_publication where  state in(3,5) group by pubyear,publishing_id";
				title="出版物出版情况统计";
				list=rService.getListreportBysql(sql);
				sql2="select id,name from r_data_publishing order by id";
				publish=rService.getListreportBysql(sql2);
				url="manage/report/pub_situation_report.jsp";
				sql3="select a.name ,count(b.id) from r_data_publishing a left join (select * from r_tab_publication where state in(3,5)) b on a.id=b.publishing_id group by a.id order by a.id";
				zj=rService.getListreportBysql(sql3);
				//第一中显示方式 年份为表头  出版社为列分类
//				for(Object[] obj:list)
//					year.add(String.valueOf(obj[0])); //存储年份
//				for(Object[] obj1:publish){//存储 统计出版社 及出版社对应年份的数量
//					for(Object[] obj:list){
//						//year.add(String.valueOf(obj[0]));	
//						Object[] obj3=new Object[2];
//						obj3[0]=obj1[0];//存储对应出版社ID
//						if(obj[1].equals(obj1[0]))
//							obj3[1]= obj[2];//存储对应年度数量
//						else
//							obj3[1]=0;
//						count.add(obj3);
//					}
//				}
				//第一中显示方式 出版社为表头  年份为列分类
				for(Object[] obj_list:list){
					year.add(String.valueOf(obj_list[0])); //存储对应年份
					
					for(Object[] obj_pub:publish){
						Object[]obj_count=new Object[2] ;//创建统计表内数据
						obj_count[0]=obj_list[0]; //第一个字段存储年份
						if(obj_pub[0].equals(obj_list[1])) //第二个字段存储对应出版社对应年份的数据
							obj_count[1]=obj_list[2];
						else
							obj_count[1]=0;
						count.add(obj_count);
					}
					
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("zj", zj);
		modelMap.put("year", year);
		modelMap.put("publish", publish);
		modelMap.put("list", list);
		modelMap.put("count", count);
		modelMap.put("albumNum", albumNum);
		modelMap.put("title", title);
		return url;
	}
	/**
	 * 传承人项目信息统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/inheritor_pro/{language}", method=RequestMethod.GET)
	public String getInheritorforPro(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();
		List<Object[]> list1=new ArrayList<Object[]>();
	
		String sql="select * from r_tab_project where state in(3,5)";
		String sql2="select * from r_tab_inheritor where state in(3,5)";
		String sql3="select a.name,count(b.id) from ("+sql+") a left join ("+sql2+")b on a.id=b.project_id group by a.id ";
		int count=0;//总人数
		try {
			list1=rService.getListreportBysql(sql3);
			count=rService.getIntreportBysql("select count(*) from r_tab_inheritor where state in(3,5)" );
			for(int a=0;a<list1.size();a++){
				Object[] obj=new  Object[3];
				obj[0]=list1.get(a)[0];
				obj[1]=list1.get(a)[1];
				if(count==0)
					obj[2]="0%";
				else{
					 NumberFormat numberFormat = NumberFormat.getInstance();  
					 numberFormat.setMaximumFractionDigits(2);
					 int v=Integer.valueOf(obj[1].toString());
					 String result = numberFormat.format(((float)v/ (float) count * 10000)/100);  
					
					obj[2]=result+"%";
				}
				list.add(obj);	
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelMap.put("list", list);
		return "manage/report/in_pro_report.jsp";
	}
	
	/**
	 * 出版物项目信息统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/pub_pro/{language}", method=RequestMethod.GET)
	public String getPubforPro(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();
		List<Object[]> list1=new ArrayList<Object[]>();
	
		String sql="select * from r_tab_project where state in(3,5)";
		String sql2="select * from r_tab_publication where state in(3,5)";
		String sql3="select a.name,count(b.id) from ("+sql+") a left join ("+sql2+")b on a.id=b.project_id group by a.id ";
		int count=0;//总人数
		try {
			list1=rService.getListreportBysql(sql3);
			count=rService.getIntreportBysql("select count(*) from r_tab_publication where state in(3,5)" );
			for(int a=0;a<list1.size();a++){
				Object[] obj=new  Object[3];
				obj[0]=list1.get(a)[0];
				obj[1]=list1.get(a)[1];
				if(count==0)
					obj[2]="0%";
				else{
					 NumberFormat numberFormat = NumberFormat.getInstance();  
					 numberFormat.setMaximumFractionDigits(2);
					 int v=Integer.valueOf(obj[1].toString());
					 String result = numberFormat.format(((float)v/ (float) count * 10000)/100);  
					
					obj[2]=result+"%";
				}
				list.add(obj);	
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelMap.put("list", list);
		return "manage/report/pub_pro_report.jsp";
	}
	
	/**
	 * 专项活动数据比重统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/act/{language}", method=RequestMethod.GET)
	public String getActreport(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();
		String sql="select case type when '01' then '演出' when '02' then '比赛' when '03' then '会议' when '04' then '田野调查' when '05' then '培训' end as name,"+
				"count(*) from r_tab_activities where state in(3,5) group by type";
		
		try {
			list=rService.getListreportBysql(sql);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return "manage/report/act_report.jsp";
	}
	/**
	 * 专项活动分类统计
	 * @param modelMap
	 * @param type
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/act/{type}/{language}", method=RequestMethod.GET)
	public String getActTypereport(ModelMap modelMap, @PathVariable("type")  String type,@PathVariable("language")  String language){
		
		List<Object[]> list=new ArrayList<Object[]>();
		
		String sql1="select a.name,count(b.id),a.peoplenum from r_tab_activities a left join ";
		String url="";
		try {
			if(type.equals("01")){
				String sql2="select * from r_tab_content_file where type='5' and filetype='03'";
				String sql=sql1+"("+sql2+") b on a.id=b.contentId where a.state in(3,5) and a.type='"+type+"' group by a.id";
				list=rService.getListreportBysql(sql);
				url="manage/report/act_yc_report.jsp";
			}else if(type.equals("02")){
				String sql2="select * from r_tab_content_file where type='5' and filetype='03'";
				String sql=sql1+"("+sql2+") b on a.id=b.contentId where a.state in(3,5) and a.type='"+type+"' group by a.id";
				list=rService.getListreportBysql(sql);
				url="manage/report/act_bs_report.jsp";
			}else if(type.equals("03")){
				String sql2="select * from r_tab_content_file where type='5' and filetype='02'";
				String sql=sql1+"("+sql2+") b on a.id=b.contentId where a.state in(3,5) and a.type='"+type+"' group by a.id";
				list=rService.getListreportBysql(sql);
				url="manage/report/act_hy_report.jsp";
			}else if(type.equals("04")){
				sql1="select id,name from r_tab_activities where type='"+type+"' and state in(3,5)"; //查询活动信息
				List<Object[]> list1=rService.getListreportBysql(sql1);
				for(int a=0;a<list1.size();a++){
					String sql2="select filetype, count(*) from r_tab_content_file where type='5' and contentId="+list1.get(a)[0]+" group by filetype"; //查询对应附件信息
					String sql3="select count(distinct InheritorId) from r_tab_content_file where type='5' and contentId="+list1.get(a)[0]+" and (inheritorId !=null or inheritorid !='')"; //查询传承人数量
					List<Object[]> list2=rService.getListreportBysql(sql2);
					Object[] obj=new Object[4]; 
					int zp=0;int qm=0;int ccr=0;
					for(int b=0;b<list2.size();b++){
						if(list2.get(b)[0].equals("01"))
							zp=Integer.valueOf(list2.get(b)[1].toString());
						else if(list2.get(b)[0].equals("03"))
							qm=Integer.valueOf(list2.get(b)[1].toString());
					}
					obj[0]=list1.get(a)[1];//活动名称
					obj[1]=zp;//照片数量
					obj[2]=qm;//曲目数量
					ccr=rService.getIntreportBysql(sql3);
					obj[3]=ccr;
					list.add(obj);
				}
				url="manage/report/act_dc_report.jsp";
			}else if(type.equals("05")){
				sql1="select id,name from r_tab_activities where type='"+type+"' and state in(3,5)"; //查询活动信息
				List<Object[]> list1=rService.getListreportBysql(sql1);
				for(int a=0;a<list1.size();a++){
					String sql2="select filetype, count(*) from r_tab_content_file where type='5' and contentId="+list1.get(a)[0]+" group by filetype"; //查询对应附件信息
					String sql3="select count(distinct InheritorId) from r_tab_content_file where type='5' and contentId="+list1.get(a)[0]+" and (inheritorId !=null or inheritorid !='')"; //查询传承人数量
					List<Object[]> list2=rService.getListreportBysql(sql2);
					Object[] obj=new Object[4]; 
					int zp=0;int qm=0;int ccr=0;
					for(int b=0;b<list2.size();b++){
						if(list2.get(b)[0].equals("01"))
							zp=Integer.valueOf(list2.get(b)[1].toString());
						else if(list2.get(b)[0].equals("03"))
							qm=Integer.valueOf(list2.get(b)[1].toString());
					}
					obj[0]=list1.get(a)[1];//活动名称
					obj[1]=zp;//照片数量
					obj[2]=qm;//曲目数量
					ccr=rService.getIntreportBysql(sql3);
					obj[3]=ccr;
					list.add(obj);
				}
				url="manage/report/act_px_report.jsp";
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return url;
	}
	
	/**
	 * 专项活动年份类型统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/act/year/{language}", method=RequestMethod.GET)
	public String getActyearreport(ModelMap modelMap, @PathVariable("language")  String language){
		
		List<Object[]> year=new ArrayList<Object[]>();
		List<Object[]> data=new ArrayList<Object[]>();
		List<Object[]> list=new ArrayList<Object[]>();
		String sqlyear="select distinct year(holedate_s),1  from r_tab_activities where (holedate_s!=null or holedate_s!='') and state in(3,5) order by holedate_s"; //获取年份
		String sqldata="select  distinct year(holedate_s), count(*),type  from r_tab_activities where (holedate_s!=null or holedate_s!='')  and state in(3,5) group by  year(holedate_s),type order by holedate_s"; //获取数据
		try {
			year=rService.getListreportBysql(sqlyear);
			data=rService.getListreportBysql(sqldata);
			for(Object[] year_o:year){ //构造LIST 0 年份 1 2 3 4 5为对应年份类别数据
				Object[] obj=new Object[6];
				obj[0]=year_o[0];obj[1]=0;obj[2]=0;obj[3]=0;obj[4]=0;obj[5]=0;
				for(Object[] data_o:data){
					if(data_o[0].equals(year_o[0])){
						if(data_o[2].equals("01")){
							obj[1]=data_o[1];
						}else if(data_o[2].equals("02")){
							obj[2]=data_o[1];
						}else if(data_o[2].equals("03")){
							obj[3]=data_o[1];
						}else if(data_o[2].equals("04")){
							obj[4]=data_o[1];
						}else if(data_o[2].equals("05")){
							obj[5]=data_o[1];
						}
					}
				}
				list.add(obj);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return "manage/report/act_year_report.jsp";
	}
	/**
	 * 专项活动级别统计
	 * @param modelMap
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/report/act/level/{language}", method=RequestMethod.GET)
	public String getActLevelreport(ModelMap modelMap, @PathVariable("language")  String language){
		List<Object[]> list=new ArrayList<Object[]>();
		String sql1="select a.name,count(b.id) from r_data_level a"; 
		String sql2="select * from r_tab_activities where state in(3,5)"; 
		String sql=sql1+" left join ("+sql2+") b on a.id=b.level_id group by a.id";
		try {
			list=rService.getListreportBysql(sql);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return "manage/report/act_level_report.jsp";
	}
}


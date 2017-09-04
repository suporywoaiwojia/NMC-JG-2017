<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" /> 
		<meta http-equiv="cache-control" content="no-cache" /> 
		<meta http-equiv="expires" content="0" />
		
		<title>码表信息</title>
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
	<style type="text/css"> 


		table{ border-collapse:collapse;border:1px #525152 solid;margin:0 auto;margin-top:20px;} 
		th,td{border:1px #525152 solid;text-align:center;font-size:12px;line-height:30px;} 
		
		
		/*模拟对角线*/ 
	.out{ 
		border-top:92px #D6D3D6 solid;/*上边框宽度等于表格第一行行高*/ 
		width:0px;/*让容器宽度为0*/ 
		height:0px;/*让容器高度为0*/ 
		border-left:80px #BDBABD solid;/*左边框宽度等于表格第一行第一格宽度*/ 
		position:relative;/*让里面的两个子容器绝对定位*/ 
		} 
		b{font-style:normal;display:block;position:absolute;top:-70px;left:-40px;width:35px;} 
		em{font-style:normal;display:block;position:absolute;top:-35px;left:-70px;width:55x;} 


</style> 
	
	</head>	  
	<body>
		<div style="width:100%;height:600px;">
		
          <div style="margin:20px 0; text-align:center">
			<h1>传承人数量统计</h1>
          </div>
		  <div style="margin:20px 0; text-align:center">
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;">传承人数据量统计</a>
			  <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_pro()">传承人项目数量统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(2)">传承人性别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(3)">传承人级别统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report(4)">传承人民族统计</a>
			 <a href="#" class="easyui-linkbutton"  style="width:120px; margin-left:20px;" onclick="report_age()">传承人年龄统计</a>
          </div>
			<table > 
				
				<tr>
				  <th width="81" rowspan="2" style="width:80px;"> 
					<div class="out"> 
					<b>类别</b> 
				  <em>分类</em>					</div></th>
				  <th width="75">民间文学</th>
				  <th colspan="4">传统表演</th>
				  <th colspan="3">手工技艺</th>
				  <th colspan="2">传统风俗</th>
				  <th width="75" rowspan="2">总计</th> 
			  </tr>
				<tr> 
					<th width="75">民间文学</th> 
					<th width="75">传统音乐</th> 
					<th width="75">传统舞蹈</th> 
					<th width="75">传统戏剧</th>
					<th width="75">传统曲艺</th>
					<th width="75">传统体育、游艺与杂技</th>
					<th width="75">传统美术</th>
					<th width="75">传统技艺</th>
					<th width="75">传统医药</th>
					<th width="75">民俗</th> 
				</tr> 
					
				<tr> 
					<td class="t1">传承人数量</td> 
					<c:set value="0" var="rs1" />  
					<c:set value="0" var="rs2" />  
					<c:set value="0" var="rs3" />  
					<c:set value="0" var="rs4" />  
					<c:forEach items="${list}" var="list" varStatus="r">
						<c:choose> 
							<c:when test="${r.index eq '0'}"> 
								<c:set value="${rs1 + list[1]}" var="rs1" />
							</c:when>
							<c:when test="${r.index >=1 and r.index<=4}"> 
								<c:set value="${rs2 + list[1]}" var="rs2" />
							</c:when>
							<c:when test="${r.index >=5 and r.index<= 7}"> 
								<c:set value="${rs3 + list[1]}" var="rs3" />
							</c:when>
							<c:when test="${r.index >=8}"> 
								<c:set value="${rs4 + list[1]}" var="rs4" />
							</c:when>
						</c:choose>
					<td>${list[1]}(${list[2]})</td> 
					</c:forEach>
					<th width="75" rowspan="2">${rs1+rs2+rs3+rs4}</th> 
			  </tr> 
				<tr>
					  <th class="t1">传承人合计
					    </td>
					  <th class="t1">${rs1}</td>
					<th colspan="4" class="t1">${rs2}</td>
					<th colspan="3" class="t1">${rs3}</td>
				  <th colspan="2" class="t1">${rs4}</td>
		      </tr>
				<tr> 
					<td class="t1">简介数量</td> 
					<c:set value="0" var="jj1" />
					<c:set value="0" var="jj2" />  
					<c:set value="0" var="jj3" />  
					<c:set value="0" var="jj4" />    
					<c:forEach items="${list_jj}" var="list_jj"  varStatus="j">
						<c:choose> 
							<c:when test="${j.index eq '0'}"> 
								<c:set value="${jj1 + list_jj[1]}" var="jj1" />
							</c:when>
							<c:when test="${j.index >=1 and j.index<=4}"> 
								<c:set value="${jj2 + list_jj[1]}" var="jj2" />
							</c:when>
							<c:when test="${j.index >=5 and j.index<= 7}"> 
								<c:set value="${jj3 + list_jj[1]}" var="jj3" />
							</c:when>
							<c:when test="${j.index >=8}"> 
								<c:set value="${jj4 + list_jj[1]}" var="jj4" />
							</c:when>
						</c:choose>
					<td>${list_jj[1]}</td> 
					</c:forEach>
					<th width="75" rowspan="2">${jj1+jj2+jj3+jj4}</th> 
			  </tr> 
			   <tr>
				  <th class="t1">简介合计</td>
				  <th class="t1">${jj1}</td>
				<th colspan="4" class="t1">${jj2}</td>
				<th colspan="3" class="t1">${jj3}</td>
				  <th colspan="2" class="t1">${jj4}</td>
		      </tr>
		      <tr> 
					<td class="t1">照片数量</td> 
					<c:set value="0" var="zp1" />  
					<c:set value="0" var="zp2" />  
					<c:set value="0" var="zp3" />  
					<c:set value="0" var="zp4" />  
					<c:forEach items="${list_zp}" var="list_zp"   varStatus="z">
						<c:choose> 
							<c:when test="${z.index eq '0'}"> 
								<c:set value="${zp1+ list_zp[1]}" var="zp1" />
							</c:when>
							<c:when test="${z.index >=1 and z.index<=4}"> 
								<c:set value="${zp2 + list_zp[1]}" var="zp2" />
							</c:when>
							<c:when test="${z.index >=5 and z.index<= 7}"> 
								<c:set value="${zp3 + list_zp[1]}" var="zp3" />
							</c:when>
							<c:when test="${z.index >=8}"> 
								<c:set value="${zp4 + list_zp[1]}" var="zp4" />
							</c:when>
						</c:choose>
					<td>${list_zp[1]}</td> 
					</c:forEach>
					<th width="75" rowspan="2">${zp1+zp2+zp3+zp4}</th> 
			  </tr> 
			  <tr>
				  <th class="t1">照片合计</td>
				  <th>${zp1}</td>
				<th colspan="4" class="t1">${zp2}</td>
				<th colspan="3" class="t1">${zp3}</td>
				  <th colspan="2" class="t1">${zp4}</td>
		      </tr>
			  <tr> 
					<td class="t1">申报文本数量</td> 
					<c:set value="0" var="wb1" />  
					<c:set value="0" var="wb2" />  
					<c:set value="0" var="wb3" />  
					<c:set value="0" var="wb4" />  
					<c:forEach items="${list_sbw}" var="list_sbw"  varStatus="z">
						<c:choose> 
							<c:when test="${z.index eq '0'}"> 
								<c:set value="${wb1+ list_sbw[1]}" var="wb1" />
							</c:when>
							<c:when test="${z.index >=1 and z.index<=4}"> 
								<c:set value="${wb2 + list_sbw[1]}" var="wb2" />
							</c:when>
							<c:when test="${z.index >=5 and z.index<= 7}"> 
								<c:set value="${wb3 + list_sbw[1]}" var="wb3" />
							</c:when>
							<c:when test="${z.index >=8}"> 
								<c:set value="${wb4 + list_sbw[1]}" var="wb4" />
							</c:when>
						</c:choose>
					<td>${list_sbw[1]}</td> 
					</c:forEach>
					<th width="75" rowspan="2">${wb1+wb2+wb3+wb4}</th> 
			  </tr> 
			    <tr>
				  <th class="t1">申报文本合计
				    </td>
				  <th>${wb1}</td>
				<th colspan="4" class="t1">${wb2}</td>
				<th colspan="3" class="t1">${wb3}</td>
				  <th colspan="2" class="t1">${wb4}</td>
		      </tr>
			   <tr> 
					<td class="t1">申报片数量</td> 
					<c:set value="0" var="sp1" />  
					<c:set value="0" var="sp2" />  
					<c:set value="0" var="sp3" />  
					<c:set value="0" var="sp4" />  
					<c:forEach items="${list_sbp}" var="list_sbp"   varStatus="z">
						<c:choose> 
							<c:when test="${z.index eq '0'}"> 
								<c:set value="${sp1+ list_sbp[1]}" var="sp1" />
							</c:when>
							<c:when test="${z.index >=1 and z.index<=4}"> 
								<c:set value="${sp2 + list_sbp[1]}" var="sp2" />
							</c:when>
							<c:when test="${z.index >=5 and z.index<= 7}"> 
								<c:set value="${sp3 + list_sbp[1]}" var="sp3" />
							</c:when>
							<c:when test="${z.index >=8}"> 
								<c:set value="${sp4 + list_sbp[1]}" var="sp4" />
							</c:when>
						</c:choose>
					<td>${list_sbp[1]}</td> 
					</c:forEach>
					<th width="75" rowspan="2">${sp1+sp2+sp3+sp4}</th> 
			  </tr> 
			   <tr>
				  <th class="t1">申报片合计
				    </td>
				  <th>${sp1}</td>
				<th colspan="4" class="t1">${sp2}</td>
				<th colspan="3" class="t1">${sp3}</td>
			     <th colspan="2" class="t1">${sp4}</td>
		      </tr>
		  </table> 
        </div>
		
	</div>
	<script type="text/javascript">
		function report(type){
			var url="${basePath}action/report/inheritor/"+type+"/CN";
			window.location.href=url;
				
		}
		function report_pro(type){
			var url="${basePath}action/report/inheritor_pro/CN";
			window.location.href=url;
				
		}
		function report_age(){
			var url="${basePath}action/report/inheritor_age/CN";
			window.location.href=url;
		}
	</script>
	</body>
</html>
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
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/easyui_tm.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/styles.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0_tm/css/demo.css">
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0_tm/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0_tm/js/public.js"></script>
		<script type="text/javascript">
		//列表页面跳转
		function pageRefresh(page){
			var url="${basePath}action/pub/list/MN/"+page;
			$("#pro_search").attr("action",url);
			$("#pro_search").submit();
		}
		//执行查询
		function searchlist(){
			pageRefresh(1);
		}
		//跳转新建页面
		function newpage(){
			window.parent.topage(' ᠬᠡᠪᠯᠡᠭᠳᠡᠬᠦᠨ ᠦ ᠰᠤᠷᠠᠭ','pub/newpage');
		}
		//跳转编辑页面
		function edit(ids){
			window.parent.topage(' ᠬᠡᠪᠯᠡᠭᠳᠡᠬᠦᠨ ᠦ ᠰᠤᠷᠠᠭ-ᠨᠠᠶᠢᠷᠠᠭᠤᠯᠬᠤ','pub/editpage/'+ids);
		}
		
		function deldata(ids){
			var url="${basePath}action/pub/del/"+ids;
			$.ajax({type:"GET", url:url,dataType:"text", success:function(datas) {
				if(datas=='0'){
					$.messager.alert('','  ！','info');
				}else if(datas=='1'){
					$.messager.alert('','！','info',function(){searchlist();});
				}
					
			}});
		}
		$(function(){
			
			//查询下拉项数据回显
			if('${pub.state}'!='')
				$('#state').combobox('select','${pub.state}');
			
			if('${pub.type}'!='')
				$('#type').combobox('select','${pub.type}');
			if('${pub.publishing.id}'!='')
				$('#publishing').combobox('select','${pub.publishing.id}');
		
		})
		</script>
	
	</head>
	  
	<body>
		<div style="width:100%;position:fixed; bottom:5%"  class="mongol_div">
		 	 <div style="padding:20px 0 0 30px;" class="mongol_div">
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:120px; " onclick="searchlist()">ᠯᠠᠪᠯᠠᠬᠤ</a>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:120px" onclick="delall()">ᠤᠰᠠᠳᠬᠠᠬᠤ</a>
		     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:120px" onclick="puball('pub','mn',${pagingTools.currentPage})">ᠨᠡᠶᠢᠲᠡᠯᠡᠬᠦ</a>
	      </div>
			  <div style="margin:20px 0 60px 30px;" class="mongol_div">
		  <form id="pro_search" method="get" action="">
            <table>
		      <tr>
		        <td>ᠠᠩᠭᠢᠯᠠᠯ:</td>
		        <td><select class="easyui-combobox" name="type" id='type' data-options="editable:false,panelHeight:'auto',width:200">
                  <option value="" >----</option>
                  <option value="01" > ᠲᠤᠰᠬᠠᠢ ᠪᠦᠲᠦᠭᠡᠯ</option>
						<option value="02" > ᠥᠭᠦᠯᠡᠯ</option>
						<option value="03" > ᠠᠶ᠎ᠠ ᠶᠢᠨ ᠲᠡᠭᠦᠪᠦᠷᠢ</option>
						<option value="04" > ᠴᠣᠮᠣᠭ</option>
						<option value="05" > ᠮᠡᠳᠡᠭᠡᠯᠡᠬᠦ</option>
                </select></td>
		        <td> ᠬᠡᠪᠯᠡᠭᠰᠡᠨ ᠪᠠᠶᠢᠭᠤᠯᠤᠮᠵᠢ:   </td>
		        <td><select class="easyui-combobox" name="publishing.id" id='publishing' data-options="editable:false,panelHeight:'auto',width:200">
                  <option value="0" >----</option>
                  <c:forEach items="${pub_list}" var="pub_list" >
                    <option value="${pub_list.id}" >${pub_list.name}</option>
                  </c:forEach>
                </select></td>
	          </tr>
		      <tr>
		        <td> ᠨᠣᠮ ᠤᠨ ᠪᠠᠷᠢᠮᠵᠢᠶ᠎ᠠ ᠳ᠋ᠤᠭᠠᠷ:</td>
		        <td><input class="easyui-textbox" style="width:200px" name="isbn" id="isbn" value="${pub.isbn}" /></td>
		        <td >ᠪᠠᠶᠢᠳᠠᠯ:</td>
		        <td><select class="easyui-combobox" name="state" data-options="editable:false,panelHeight:'auto',width:200" id="state">
                  <option value="" >----</option>
                   <option value="0" > </option>
                  <option value="1"> </option>
                  <option value="2" > </option>
                  <option value="3" >ᠡᠪᠲᠡᠷᠡᠬᠦ</option>
                  <option value="4" >ᠪᠤᠴᠠᠭᠠᠬᠤ</option>
                  <option value="5"></option>
                </select></td>
	          </tr>
		      <tr>
              
				 <td> ᠨᠡᠷ᠎ᠡ:</td>
                <td>
				  <input class="easyui-textbox" style="width:200px" name="name" value="${pub.name}">                </td>
				 <td> ᠵᠣᠬᠢᠶᠠᠭᠴᠢ:</td>
                <td>
				  <input class="easyui-textbox" style="width:200px" name="inheritor.name" id="inheritor" value="${pub.inheritor.name}">                </td>
			  </tr>
            </table>
            </form>
          </div>
         
          <div style="margin:20px 0;">
		  <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
				data-options="total:${pagingTools.count},pageNumber:${pagingTools.currentPage},pageSize:${pagingTools.pageRecord},displayMsg:'',layout:['sep','first','prev','manual','next','last','efresh']">
			</div>
	      <table id="dg" title=" ᠯᠠᠪᠯᠠᠭᠰᠠᠨ ᠳ᠋ᠦᠩ" style="width:100%;height:560px"   class="easyui-datagrid"  data-options="
				rownumbers:false,
				singleSelect:false,
				autoRowHeight:false,
				pagination:false,
				onClickCell:onClickCell,
				onAfterEdit:onAfterEdit">
	        <thead>
			  <tr>
			  	<th field="ck" checkbox="true"></th>
				<th field="caozuo" width="10%" align="left">
                 ᠠᠵᠢᠯᠯᠠᠭᠤᠯᠬᠤ
                </th>
				<th field="name" width="20%" align="left"> ᠨᠡᠷ᠎ᠡ</th>
				<th field="type" width="10%" align="left"> ᠠᠩᠭᠢᠯᠠᠯ</th>
				<th field="inher" width="12%" align="left"> ᠵᠣᠬᠢᠶᠠᠭᠴᠢ</th>
				<th field="isbn" width="10%" align="left"> ᠨᠣᠮ ᠤᠨ ᠪᠠᠷᠢᠮᠵᠢᠶ᠎ᠠ ᠳ᠋ᠤᠭᠠᠷ</th>
				<th field="publising" width="10%" align="left"> ᠬᠡᠪᠯᠡᠭᠰᠡᠨ ᠪᠠᠶᠢᠭᠤᠯᠤᠮᠵᠢ</th>
			
				<th field="creatdate" width="8%" align="left"> </th>
				<th field="sort" width="10%" data-options="editor:{type:'numberbox',options:{max:99}}" align="center">  </th>
				<th field="statename" width="8%" align="left">ᠪᠠᠶᠢᠳᠠᠯ</th>
                <th field="state" width="10%"  hidden="true" align="center">状态</th>
			  </tr>
	        </thead>
			<tbody>
			<c:forEach items="${pagingTools.dataSet}" var="pub"  varStatus="status">
			<tr>
				<td>${pub.id}</td>
				<td><c:if test="${pub.state ne 2}"><a href="#" onclick="edit('${pub.id}')">ᠨᠠᠶᠢᠷᠠᠭᠤᠯᠬᠤ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="del('${pub.id}')">ᠤᠰᠠᠳᠬᠠᠬᠤ</a></c:if>
				<c:if test="${pub.state eq 3||pub.state eq 5}">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="publishstate('pub','${pub.id}',${pagingTools.currentPage},'mn')"></a></c:if>
				</td>
				<td>${pub.name}</td>
				
				
				<td>
					<c:choose>  
						<c:when test="${pub.type eq '01'}">
						 ᠲᠤᠰᠬᠠᠢ ᠪᠦᠲᠦᠭᠡᠯ
				  		</c:when>
						<c:when test="${pub.type eq '02'}">
						 ᠥᠭᠦᠯᠡᠯ
				  		</c:when>
						<c:when test="${pub.type eq '03'}">
						 ᠠᠶ᠎ᠠ ᠶᠢᠨ ᠲᠡᠭᠦᠪᠦᠷᠢ
				  		</c:when>
						<c:when test="${pub.type eq '04'}">
						 ᠴᠣᠮᠣᠭ
				  		</c:when>
						<c:when test="${pub.type eq '05'}">
						 ᠮᠡᠳᠡᠭᠡᠯᠡᠬᠦ
				  		</c:when>
					</c:choose>
				</td>
				<td>
					${pub.inheritor.name} </td>
				<td>${pub.isbn} </td>
				<td>
				${pub.publishing.name} </td>
			
				<td>
				${pub.l_type}
				</td>
				<td>${pub.sort}</td>
				<td>
					 <c:choose>  
						<c:when test="${pub.state eq '0'}">
						 
				  		</c:when>
						<c:when test="${pub.state eq '1'}">
						 
				  		</c:when>
						<c:when test="${pub.state eq '2'}">
						 
				  		</c:when>
						<c:when test="${pub.state eq '3'}">
						ᠨᠡᠪᠲᠡᠷᠡᠬᠦ
				  		</c:when>
						<c:when test="${pub.state eq '4'}">
						ᠪᠤᠴᠠᠭᠠᠬᠤ
				  		</c:when>
						<c:when test="${pub.state eq '5'}">
						
				  		</c:when>
					</c:choose>
				</td>
				<td>${pub.state}</td>
				
			</tr>
			</c:forEach>
		</tbody>
	      </table>
			
        </div>
	</div>
	
	</body>
</html>
<script type="text/javascript">
	var height=$(window).height()*0.7;
	$('#dg').height(height);
	</script>
	<script type="text/javascript">

		function editsort(id,sortnum){
			var url="${basePath}action/editsort";
			var data={"tablename":"r_tab_publication","id":id,sort:sortnum};
			$.ajax({type:"POST", url:url,data:data,dataType:"JSON"});
		}
		var basePath='${basePath}';
	</script>
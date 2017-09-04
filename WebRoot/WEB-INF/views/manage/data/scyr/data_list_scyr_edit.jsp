<%@page import="java.util.List"%>
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
	    <link rel="shortcut icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css"> 
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}js/validate.js"></script>
        
        <link rel="stylesheet" type="text/css" href="${basePath}webupload/style.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}webupload/css/webuploader.css" />  
        <script type="text/javascript" src="${basePath}webupload/jquery.js"></script>
        <script type="text/javascript" src="${basePath}webupload/dist/webuploader.js"></script>
		<script type="text/javascript">
	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
			}
			function myparser(s){
				if (!s) return new Date();
				var ss = (s.split('-'));
				var y = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var d = parseInt(ss[2],10);
				if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
					return new Date(y,m-1,d);
				} else {
					return new Date();
				}
			}
		//右下角弹出的的提示信息
		function showMessage(message){
				$.messager.show({
				title:'消息',
				msg:message,
				timeout:3000,
				showType:'slide'
			});
			}
		
		</script>
  </head>
 
<body>
<div class="easyui-panel" title="项目信息" style="width:100%;padding:20px 30px;">
  <div  style="padding:0 0 10px;" >
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:300px;" onclick="save()">保存</a>	   
  </div>  
   <div style="margin:10px 0"> 
	<table align="center" >
        <tr>
        <td >头像:</td>
        <td>
                <div class="uploader" id="uploader">
                  <div class="queueList">
                   <div id="dndArea" class="placeholder">
                    <div  id="filePicker">
                   </div>
                   <ul class="filelist">
						<li >
                        <p class="title"></p>
                        <p class="imgWrap">
                         <img src="${rapper.filesHttpPath}" />
                        </p>
                        <div class="file-panel">
                        <span class="cancel">删除</span>
                       </div>
                        </li>
                   </ul>
                </div>
               </div>
              </div>
		</td>	
			<!--上传结束 -->
	</div>      
  <div style="margin:10px 0">
    <form id="web_form" action="" method="post">
	<table align="center" >
   <input type="hidden" id="rapper" name="rapper.id" value="${rapper.id}">  
		<tr>
        <tr>
        <td >姓名:</td>
        <td><input id="name"  name="name" value="${rapper.name}" data-options="required:true,validType:'length[0,50]',missingMessage:'资料名称不能为空',invalidMessage:'作者长度不能超出50个字符'" class="easyui-textbox" style="text-align:center;width:300px;height:30px" /></td>        
        <td style="padding-left:40px;">性别:</td>
        <td><input id="sex" name="sex" value="${rapper.sex}" data-options="required:true,validType:'length[0,10]',missingMessage:'作者不能为空',invalidMessage:'作者长度不能超出20个字符'" class="easyui-textbox" style="width:300px;height:30px;text-align:center;" /></td>        
        <td style="padding-left:40px;">单位:</td>
        <td><input  id="address" name="address" value="${rapper.address}" data-options="required:true,missingMessage:'作者单位不能为空'" class="easyui-textbox" style="text-align:center;width:300px;height:30px"/></td>
      </tr>
      <tr> 
		<td>国籍:</td>
        <td>
           <select id="country" class="easyui-combobox" name="country.id" style="width:300px;height:30px; text-align:center;">
                    
            <c:forEach items="${countryList}" var="country" >
              <c:if test="${country.name!='全部'}">  
                 <option value="${country.id}" <c:if test="${rapper.country.id==country.id}">selected="selected"</c:if> >${country.name}</option>	
              </c:if>
            </c:forEach>
          </select>   
       </td>
       <td  style="padding-left:40px;">所属栏目</td>
         <td>
         <input  value="${column.name}" class="easyui-textbox" readonly="readonly" style="text-align:center;width:300px;height:30px" />
         <input type="hidden" id="column" name="column.id" value="${column.id}"/>
       </td>
        <td  style="padding-left:40px;">电话</td>
         <td>
         <input   class="easyui-textbox" name="phone" id="phone" value="${rapper.phone}" style="text-align:center;width:300px;height:30px" />
       
       </td>
       </tr>
       <tr>
       <td >邮箱</td>
         <td>
         <input  class="easyui-textbox" name="email" id="email" value="${rapper.email}" style="text-align:center;width:300px;height:30px" />
       </td>
       <td style="padding-left:40px;">创建用户:</td>
        <td>
          <input value="${rapper.publishU.userName}"  readonly="readonly" class="easyui-textbox"  style="text-align:center;width:300px;height:30px" />   
          <input type="hidden" id="publishU" name="publishU.id" value="${rapper.publishU.id}">   
       </td>

      <c:if test="${column.approve=='1'}">
        <td style="padding-left:40px;">审核人员:</td>
        <td>
          <select id="approve" class="easyui-combobox" name="approve.id" style="width:300px;height:30px; text-align:center;">
            <c:forEach items="${approveList}" var="approve" >
                <option value="${approve.id}" <c:if test="${rapper.approve.id==approve.id}" >selected="selected"</c:if> >${approve.userName}</option>		
            </c:forEach>
          </select>       
        </td>
        </c:if>
        <c:if test="${column.approve=='0'}">
        <td style="padding-left:40px">审核开关:</td>
        <td>
        关闭
        </td>
        </c:if>
       </tr>
      <tr></tr>
      <tr>
	    <td >简介:</td>
		<td colspan="5">
        <input id="summary" name="summary" value="${rapper.summary}" class="easyui-textbox" data-options="multiline:true" style="width:1100px;height:150px" />
        </td>
	  </tr> 
    </table>
   <!--上传返回的保存和访问地址-->
    
       <input type="hidden" id="filesName" name="filesName" value="${rapper.filesName}" />
       <input type="hidden" id="filesUploadPath" name="filesUploadPath" value="${rapper.filesUploadPath}" />
       <input type="hidden" id="filesHttpPath" name="filesHttpPath" value="${rapper.filesHttpPath}"  />
  </form>
  </div>
  
		    
</div>
</body>
<script type="text/javascript">
		function save(){
			var url = "${basePath}action/manage/data/rapper/update";
			var data = $("#web_form").serialize();
			$.post(url, data,
				   function(result){
					 if(result=='1'){
						 $.messager.alert('消息','保存成功','info');
					 }else if(result=='0'){
						 $.messager.alert('消息','保存失败','info');
				      }
				   }, "text");
				
		}
		
	</script>
	<script type="text/javascript">

  $(function(){
	  
       var $wrap = $('#uploader'),

            // 图片容器
           $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
           $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
          //  $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $('#uploadBtn' ),

            // 没选择文件之前的内容。
           $placeHolder = $wrap.find( '.placeholder' ),

           $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

       

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '选择并上传图片'
            },
            formData: {
                uid: 123,
				httpPath:'${basePath}website/upload_file/portrait/',
				savePath:'website/upload_file/temp/',
				
            },
           auto:true,
            swf: '${basePath}webupload/dist/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: "${basePath}action/file_upload",
            // runtimeOrder: 'flash',

            accept: {
                title: 'Images',
                 extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/jpg,image/jpeg,image/png'
            },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

      

        uploader.on('ready', function() {
            window.uploader = uploader;
        });
		
		//现在使用的，上传成功后返回的数据
        uploader.on('uploadSuccess', function(file,response) {
        
		   //将返回的数据分割为数组，取出网络访问路径，磁盘存储路径,name
		  var resArray = String(response._raw).split(",");
		 
		  //设置当前行内的input的httpPath,savePath,name,
		   $("#filesName").val(resArray[2]);
		   $("#filesHttpPath").val(resArray[1]);
		   $("#filesUploadPath ").val(resArray[0]);
        });

        // 当有文件添加进来时执行，负责view的创建 ,此时对当前容器下的三个隐藏的输入框的值，赋值
        function addFile( file ) {
			
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '</div>').appendTo( $li ),
					
					
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
              //  $info = $('<p class="error"></p>'),
         
		   
                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }
                  //此处
				
                 //   $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="'+src+'">');
                        $wrap.empty().append( img );
                    } else {
                        $.ajax('../../server/preview.php', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function( response ) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append( img );
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
			//	alert(prev);
                if ( prev === 'progress' ) {
                   $prgress.hide().width(0);
			}
            //    } else if ( prev === 'queued' ) {
           //         $li.off( 'mouseenter mouseleave' );
            //        $btns.remove();
           //     }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                  //  $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });
            //将取得的html代码添加到表单中
            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

          //  $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                       
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
				
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

       // $info.on( 'click', '.retry', function() {
        //    uploader.retry();
       // } );

       // $info.on( 'click', '.ignore', function() {
       //     alert( 'todo' );
       // } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
		
    });
</script>
</html>

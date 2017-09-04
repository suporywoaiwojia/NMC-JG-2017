<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
    	<title>编辑幻灯片</title>
    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
        <link rel="stylesheet" type="text/css" href="${basePath}webupload/style.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}webupload/css/webuploader.css" />  
        <script type="text/javascript" src="${basePath}webupload/jquery.js"></script>
        <script type="text/javascript" src="${basePath}webupload/dist/webuploader.js"></script>
        
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/easyui.css">
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/icon.css">
        <link rel="stylesheet" type="text/css" href="${basePath}ui1.0/css/demo.css">
        <script type="text/javascript" src="${basePath}ui1.0/js/jquery.min.js"></script>
        <script type="text/javascript" src="${basePath}ui1.0/js/jquery.easyui.min.js"></script>	
       <style>
		input {
			padding: 1px;
			border-width: 2px;
			border-style: inset;
		}   
	   </style>
        <script type="text/javascript">

		var NUM = 0;
			//点击选择图，选择对应的图片，成功后，添加输入框和内容显示区域
		var imageHtml ='<tr style="padding-top:40px;margin-left:40px;">'+
				  '<td><input type="checkbox" name="checkbox" /></td>'+
				  '<td>'+
				  '<div class="uploader" id="uploader"><div class="queueList"><div id="dndArea" class="placeholder">'+
				  ' <div  id="filePicker"></div>'+
				  '<input type="hidden" name="filesName" />'+
				  '<input type="hidden" name="filesUploadPath" />'+
				  '<input type="hidden" name="filesHttpPath" />'+
				  '</div></div></div>'+
				  '</td>'+
				  '<td>图片链接'+
				  '</td>'+
				  '<td style="padding-left:10px;">'+
				'<input  id="links" name="links"   class="easyui-textbox" style="width:300px;height:30px;"/>'+
				 '</td>'+
				'<td style="padding-left:10px;">图片描述'+
				'</td>'+
				'<td style="padding-left:10px;">'+
				'<input  id="describes" name="describes"   class="easyui-textbox" style="width:300px;height:30px;"/>'+
			'</td>'+
			'</tr>';
			</script>  
            
  	</head>
  
  	<body>
<div class="easyui-panel"   style="width:100%;padding:15px">
  <div  style="padding:0 0 10px;" >
    <a id="save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px;" onclick="save()">保存</a>	   
  </div>       
  <div style="margin:10px 0">
  <form id="web-form" action="" method="post">
    <table align="center" id="imagesTable" >
      <tr>
       <td></td>
       <td></td>
       <input type="hidden" id = "id" name="id" value="${slideshow.id}"/>
        <td>幻灯名称</td>
        <td style="padding-left:10px;"><input id="name"  name="name" value="${slideshow.name}"  data-options="required:true,validType:'length[0,10]',missingMessage:'幻灯名称不能为空',invalidMessage:'长度不能超出10个字符'" class="easyui-textbox" style="width:300px;height:30px;"/></td> 
		<td>
        </td>
        <td >
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  id="addImage" >增加图片</a>
		
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"  id="delImage" >删除图片</a>
		</td>
	</tr>
     <c:forEach items="${slideshow.slideshowFiles}" var="slideshowFile" varStatus="status">
      <tr style="padding-top:40px;margin-left:40px;">
		        <td><input type="checkbox" name="checkbox" /></td>
                <td>
                   <div class="uploader" id="uploader_${status.index}"><div class="queueList">
                     <div id="dndArea" class="placeholder">  
                      <div  id="filePicker_${status.index}"></div>  
                      <ul class="filelist">
						<li id="${slideshowFile.id}">
                        <p class="title"></p>
                        <p class="imgWrap">
                         <img src="${slideshowFile.httppath}" />
                        </p>
                        <p class="progress"><span></span></p>
                        <span class="success"></span>
                        </li>
                      </ul>
                   <input type="hidden" name="filesName" value="${slideshowFile.filename}"/>  
                   <input type="hidden" name="filesUploadPath" value="${slideshowFile.filepath}"/>    
			       <input type="hidden" name="filesHttpPath" value="${slideshowFile.httppath}"/>    
                   </div></div></div>    
                </td>    
			    <td>
                  图片链接    
                </td>    
	            <td style="padding-left:10px;">    
                    <input  id="links" name="links"   class="easyui-textbox" style="width:300px;height:30px;" value="${slideshowFile.link}"/>    
			   </td>    
			   <td style="padding-left:10px;">
                 图片描述    
               </td>    
			   <td style="padding-left:10px;">    
                 <input  id="describes" name="describes"   class="easyui-textbox" style="width:300px;height:30px;"  value="${slideshowFile.detailed}"/>    
		       </td>    
      </tr> 
      <script type="text/javascript">NUM = ${status.index};</script>
      </c:forEach>
    </table>
    </form>
  </div>  
  </div>
     
  	</body>
</html>

        <script type="text/javascript">
		$(function(){
			//表单提交
			$("#save").click(function(){
				var url = "${basePath}action/manage/slideshow/save";
				 $('#web-form').form('submit', {
						url: url,
						onSubmit: function(){
							var isValid = $(this).form('validate');
							if (!isValid)
								return isValid;	// return false will stop the form submission
						},
						success: function(data){
							if(data!=0){
								$.messager.alert('消息','保存成功','info',function(){ window.location.href="${basePath}action/manage/slideshow/edit/"+data;});
							}else{
								$.messager.alert('消息','保存失败','info');
							}
						}
			});
				});
			$("#addImage").click(function(){
			NUM++;
			
			//添加容器，修改对应的容器id名称
            var table = $('#imagesTable');
			$(table.append(imageHtml));
			$("#uploader").attr('id', 'uploader_' + NUM);
			$("#filePicker").attr('id', 'filePicker_' + NUM);
			initUpLoad(NUM);
			});
				
			$('#delImage').click(function(){
					var checked = $('input[type="checkbox"]:checked');
					if(checked.length == 0){
						//art.dialog({content: Const.MESSAGE_FAIL_VALIDATE_DELETE_UNCHECKED, time: 2});
					}else{
						for(var i=0; i<checked.length; i++){
							var rowIndex = checked[i].parentElement.parentElement.rowIndex;
							document.getElementById('imagesTable').deleteRow(rowIndex);
						}
					}
				});	
			});	
			
   function initUpLoad(NUM){
		
       var $wrap = $('#uploader_'+NUM),

            // 图片容器
           $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
           $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $('#saveBtn' ),

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
                id: '#filePicker_'+NUM,
                label: '选择并上传图片'
            },
            formData: {
                uid: 123,
				httpPath:'${basePath}website/upload_file/sildeshow/',
				savePath:'website/upload_file/sildeshow/',
				
            },
            auto:true,
            swf: '../../dist/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: "${basePath}action/file_upload",
            // runtimeOrder: 'flash',

            // accept: {
            //     title: 'Images',
            //     extensions: 'gif,jpg,jpeg,bmp,png',
            //     mimeTypes: 'image/*'
            // },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });
		
		//现在使用的，上传成功后返回的数据
        uploader.on('uploadSuccess', function(file,response) {
        
		   //将返回的数据分割为数组，取出网络访问路径，磁盘存储路径,name
		  var resArray = String(response._raw).split(",");
		 
		  //设置当前行内的input的httpPath,savePath,name,
		   $wrap.find("[name='filesName']").val(resArray[2]);
		   $wrap.find("[name='filesHttpPath']").val(resArray[1]);
		   $wrap.find("[name='filesUploadPath']").val(resArray[0]);
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
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
					
					
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),
         
		   
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

                    $info.text( text ).appendTo( $li );
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
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

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
                    $info.remove();
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

            $info.html( text );
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
                        alert( '上传成功' );
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

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    }


</script>
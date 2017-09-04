[#macro pagination listDynamics=0 currentPage=0 totalPages=0]
<div class="pages">
	
	<ul>
		[#--动态列表获取--]
		
		[#if (currentPage>1)]
		<li class="pageprev">
    		<a href="${currentPage-1}" >上一页</a></li>
			
		[#else]
		<li class="pageprev">
			<span>上一页</span></li>
		[/#if]   
		[#if (totalPages<9)]	[#--总页数小于9的情况每页都显示--]
			[#list 1..(totalPages) as pages]
				[#if (pages==currentPage&&currentPage!=0)]	[#--如果是当前页 --]
				<li class="current">
					<a href="#">${currentPage}</a>
					</li>
				[#else]
					[#if (pages>0)]
					<li ><a href="${pages}" >${pages}</a></li>
					[/#if]
				[/#if]
			[/#list]
		[#else]
			[#if (currentPage<5)]
				[#list 1..5 as pages]
					
					[#if (pages==currentPage)]
						<li class="current">
					<a href="#">${currentPage}</a>
					</li>
					[#else]
						<li><a href="${pages}"  >${pages}</a></li>
					[/#if]
					
				[/#list]
				
				[#if (currentPage==4)]
					<li><a href="6" >6</a></li>
				[/#if]
				
				<li class="nomarginright">...	</li>
				<li><a href="${totalPages}" >${totalPages}</a></li>
				
			[#elseif (currentPage>=5&&currentPage<(totalPages-3))]
				<li><a href="1" >1</a></li>
				<li class="nomarginright">...	</li>
				[#list (currentPage-2)..(currentPage+2) as pages]
					[#if (pages==currentPage)]
						<li class="current">
					<a href="#">${currentPage}</a>
					</li>
					[#else]
						<li><a href="${pages}" >${pages}</a></li>
					[/#if]
				[/#list]
				<li class="nomarginright">...	</li>
				<li><a href="${totalPages}" >${totalPages}</a></li>
			[#else]
				<li><a href="1" >1</a></li>
				<li class="nomarginright">...	</li>
				[#if (currentPage==totalPages-3)][#--保证当前页两边都各有显示2页--]
					<li><a href="${currentPage-2}" >${currentPage-2}</a></li>
					
				[/#if]
				[#list (totalPages-4)..(totalPages) as pages]
					[#if (pages==currentPage)]
						<li class="current">
					<a href="#">${currentPage}</a></li>
					[#else]
						<li><a href="${pages}"  onclick="pagination(${pages})">${pages}</a></li>
					[/#if]
				[/#list]
			[/#if]
		[/#if]	
		
		[#if (currentPage<totalPages)]
			<li class="pagenext"><a href="${currentPage+1}"  >下一页</a></li>
		[#else]
			<li class="pagenext"><span>下一页</span></li>
		[/#if]
		<li class="clear"></li>
</ul>
    </div>
 [/#macro]
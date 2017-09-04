[#macro pagination]
<div class="common-pagination">
		[#--动态列表获取--]
		
		[#if (currentPage>1)]
    		<a href="${currentPage-1}" class="page-up" target="_self"><img src="${basePath}images/mn/page-up-b.png" /></a>
		[#else]
			<a class="page-up" ><img src="${basePath}images/mn/page-up-a.png" /></a>
		[/#if]   
		[#if (totalPages<9)]	[#--总页数小于9的情况每页都显示--]
			[#list 1..(totalPages) as pages]
				[#if (pages==currentPage)]	[#--如果是当前页 --]
					<a class="current"><span>${currentPage}</span></a>
				[#else]
					[#if (pages>0)]
					<a href="${pages}" class="pages" target="_self"><span>${pages}</span></a>
					[/#if]
				[/#if]
			[/#list]
		[#else]
			[#if (currentPage<5)]
				[#list 1..5 as pages]
					
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages" target="_self"><span>${pages}</span></a>
					[/#if]
					
				[/#list]
				
				[#if (currentPage==6)]
					<a href="6" class="pages" target="_self"><span>6</span></a>
				[/#if]
				
				<span class="pages" >...</span>	
				<a href="${totalPages}" class="pages" target="_self"><span>${totalPages}</span></a>
				
			[#elseif (currentPage>=4&&currentPage<(totalPages-3))]
				<a href="1" class="pages" target="_self"><span>1</span></a>
				<span class="pages">...</span>	
				[#list (currentPage-1)..(currentPage+1) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages" target="_self"><span>${pages}</span></a>
					[/#if]
				[/#list]
				<span class="pages">...</span>	
				<a href="${totalPages}" class="pages" target="_self"><span>${totalPages}</span></a>
			[#else]
				<a href="1" target="_self"><span>1</span></a>
				<span class="pages">...</span>	
				[#if (currentPage==totalPages-3)][#--保证当前页两边都各有显示2页--]
					<a href="${currentPage-2}" class="pages" target="_self"><span>${currentPage-2}</span></a>
				[/#if]
				[#list (totalPages-4)..(totalPages) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages" onclick="pagination(${pages})" target="_self"><span>${pages}</span></a>
					[/#if]
				[/#list]
			[/#if]
		[/#if]	
		
		[#if (currentPage<totalPages)]
			<a href="${currentPage+1}"  class="page-dn" target="_self"><img src="${basePath}images/mn/page-dn-b.png" /></a>
		[#else]
			<a class="page-dn"><img src="${basePath}images/mn/page-dn-a.png" /></a>
		[/#if]
	
    </div>
 [/#macro]
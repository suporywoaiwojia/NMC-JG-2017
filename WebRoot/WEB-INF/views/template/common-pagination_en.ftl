[#macro pagination]
<div class="common-pagination">
	[#if listDynamics==1]
		[#if (currentPage>1)]
			<a href="list_${currentPage-1}${pageFormat}" class="enable"><span>Previous</span></a>
		[#else]
			<a class="disable"><span>Previous</span></a>
		[/#if] 
		
		[#if (totalPages<9)]		[#--总页数小于9的情况每页都显示 --]
			[#list 1..(totalPages) as pages]
				[#if (pages==currentPage)]	[#--如果是当前页 --]
					<a class="current"><span>${currentPage}</span></a>
				[#else]
					<a href="list_${pages}${pageFormat}" class="pages"><span>${pages}</span></a>
				[/#if]
			[/#list]
		[#else]
			[#if (currentPage<5)]
				[#list 1..5 as pages]
					
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="list_${pages}${pageFormat}"  class="pages"><span>${pages}</span></a>
					[/#if]
					
				[/#list]
				
				[#if (currentPage==4)]
					<a href="javascript:void(0)" onclick="pagination(6)" class="pages"><span>6</span></a>
				[/#if]
				
				<span class="points">...</span>	
				<a href="list_${totalPages}${pageFormat}" class="pages"><span>${totalPages}</span></a>
				
			[#elseif (currentPage>=5&&currentPage<(totalPages-3))]
				<a href="list_1${pageFormat}" class="pages" onclick="pagination('1')"><span>1</span></a>
				<span class="points">...</span>	
				[#list (currentPage-2)..(currentPage+2) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="list_${pages}${pageFormat}" class="pages"><span>${pages}</span></a>
					[/#if]
				[/#list]
				<span class="points">...</span>	
				<a href="list_${totalPages}${pageFormat}" class="pages"><span>${totalPages}</span></a>
			[#else]
				<a href="list_1${pageFormat}" class="pages" onclick="pagination('1')"><span>1</span></a>
				<span class="points">...</span>	
				[#if (currentPage==totalPages-3)]
					<a href="list_${currentPage-3}${pageFormat}" class="pages"><span>${currentPage-2}</span></a>
				[/#if]
				[#list (totalPages-4)..(totalPages) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="list_${pages}${pageFormat}" class="pages"><span>${pages}</span></a>
					[/#if]
				[/#list]
			[/#if]
		[/#if]	
		[#if (currentPage<totalPages)]
			<a href="list_${currentPage+1}${pageFormat}"  class="enable"><span>Next</span></a>
		[#else]
			<a class="disable"><span>Next</span></a>
		[/#if]
	[#else]
	
		[#--动态列表获取--]
		
		[#if (currentPage>1)]
    		<a href="${currentPage-1}" class="enable"><span>Previous</span></a>
		[#else]
			<a class="disable"><span>Previous</span></a>
		[/#if]   
		[#if (totalPages<9)]	[#--总页数小于9的情况每页都显示--]
			[#list 1..(totalPages) as pages]
				[#if (pages==currentPage)]	[#--如果是当前页 --]
					<a class="current"><span>${currentPage}</span></a>
				[#else]
					[#if (pages>0)]
					<a href="${pages}" class="pages" ><span>${pages}</span></a>
					[/#if]
				[/#if]
			[/#list]
		[#else]
			[#if (currentPage<5)]
				[#list 1..5 as pages]
					
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages" ><span>${pages}</span></a>
					[/#if]
					
				[/#list]
				
				[#if (currentPage==4)]
					<a href="6" class="pages"><span>6</span></a>
				[/#if]
				
				<span class="points" >...</span>	
				<a href="${totalPages}" class="pages"><span>${totalPages}</span></a>
				
			[#elseif (currentPage>=5&&currentPage<(totalPages-3))]
				<a href="1" class="pages"><span>1</span></a>
				<span class="points">...</span>	
				[#list (currentPage-2)..(currentPage+2) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages"><span>${pages}</span></a>
					[/#if]
				[/#list]
				<span class="points">...</span>	
				<a href="${totalPages}" class="pages"><span>${totalPages}</span></a>
			[#else]
				<a href="1" ><span>1</span></a>
				<span class="points">...</span>	
				[#if (currentPage==totalPages-3)][#--保证当前页两边都各有显示2页--]
					<a href="${currentPage-2}" class="pages"><span>${currentPage-2}</span></a>
				[/#if]
				[#list (totalPages-4)..(totalPages) as pages]
					[#if (pages==currentPage)]
						<a class="current"><span>${currentPage}</span></a>
					[#else]
						<a href="${pages}" class="pages" onclick="pagination(${pages})"><span>${pages}</span></a>
					[/#if]
				[/#list]
			[/#if]
		[/#if]	
		
		[#if (currentPage<totalPages)]
			<a href="${currentPage+1}"  class="enable"><span>Next</span></a>
		[#else]
			<a class="disable"><span>Next</span></a>
		[/#if]
	[/#if]  
    </div>
 [/#macro]
<#--
	列表所使用的分页
-->
<#if (settings.pagenav_style!'ajax') == 'ajax'>
    <div id="pagination">
    	<#if pagination.hasNext>
        <a href="${pagination.nextPageFullPath!}">下一页</a>
    	<#else>
        <span>没有更多文章了</span>
    	</#if>
	</div>
<#else>
    <nav class="navigator">
        <#if pagination.hasPrev>
        <a href="${pagination.prevPageFullPath!}"><i class="iconfont icon-previous"></i></a>
        </#if>
        <#if pagination.hasNext>
        <a href="${pagination.nextPageFullPath!}"><i class="iconfont icon-next"></i></a>
        </#if>
    </nav>
</#if>
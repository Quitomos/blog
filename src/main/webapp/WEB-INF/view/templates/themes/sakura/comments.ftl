<#--
	COMMENTS TEMPLATE
-->
<#macro comment target,type>
	<script>
	var configs = {
    	autoLoad: true,
    	showUserAgent: false,
		loadingStyle: "circle"
	}
	</script>
	<#if (!target.disallowComment!false) && (!settings.keep_record_mode!false)>
		<section id="comments" class="comments">
			<div class="comments-main">
				<h3 id="comments-list-title">
					<span>Comments |</span>
					<span class="noticom">${target.commentCount!0} 条评论</span>
				</h3>
				<halo-comment id="${target.id?c}" type="${type}" :configs="configs"/>
			</div>
		</section>
	</#if>
</#macro>
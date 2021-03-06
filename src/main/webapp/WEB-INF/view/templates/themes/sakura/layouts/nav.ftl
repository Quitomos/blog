<ul id="menu-menu-1" class="menu" >
	<@menuTag method="tree">
		<#list menus as menu>
			<li>
				<a <#if menu.url?? && menu.url?trim?length gt 0> href="${menu.url!}" target="_self" </#if>>
                    <span class="faa-parent animated-hover">
                        <#if menu.icon?? && menu.icon?trim?length gt 0>
                            <i class="${menu.icon}" aria-hidden="true"></i>
                        </#if>${menu.name}
                    </span>
				</a>
                <#if menu.children?? && menu.children?size gt 0>
					<ul class="sub-menu">
						<#list menu.children as child>
							<li>
								<a href="${child.url!}" target="_self">
								<#if child.icon?? && child.icon?trim?length gt 1>
									<i class="${child.icon}" aria-hidden="true"></i>
									</#if>${child.name}
                                </a>
							<li>
						</#list>
					</ul>
			    </#if>
			</li>

		</#list>
	</@menuTag>
</ul>
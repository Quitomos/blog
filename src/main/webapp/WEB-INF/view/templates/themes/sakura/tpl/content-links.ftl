<article>
    <#if is_sheet??>
     ${sheet.formatContent!}
    </#if>
    <div class="links">
        <@linkTag method="listTeams" contextPath="${theme_base!}">
            <#list teams as item>
                <h3 class="link-title">
                    <span class="fake-title">${((item.team!'')?length>0)?string((item.team!''), '小伙伴们')}</span>
                </h3>
                <ul class="link-items fontSmooth">
                <#list item.links as link>
                    <li class="link-item">
                        <a class="link-item-inner effect-apollo" href="${link.url!}" title="${link.name!}" target="_blank">
                            <img class="lazyload" data-src="${link.logo!}" src="${res_base_url!}/img/svg/loader/trans.ajax-spinner-preloader.svg" onerror="imgError(this)">
                            <span class="sitename">${link.name!}</span>
                            <div class="linkdes">${link.description!}</div>
                        </a>
                    </li> 
                </#list>
                </ul>
            </#list>
        </@linkTag>
    </div>       
</article>
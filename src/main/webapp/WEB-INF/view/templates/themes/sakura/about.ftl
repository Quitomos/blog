<#--
    @package Akina
-->
<#include "header.ftl">
<@header title="关于 - ${blog_title!}">
    <#if (settings.patternimg!true) && (settings.about_patternimg?? && settings.about_patternimg!='') || ((metas.ri?boolean)!true && settings.rimage_cover_open!true && settings.rimage_url?? && settings.rimage_url!='')>
        <div class="pattern-center-blank"></div>
        <div class="pattern-center single-center">
            <div class="pattern-attachment-img">
                <#if (settings.patternimg!true) && (settings.about_patternimg?? && settings.about_patternimg!='')>
                <img class="lazyload" data-src="${settings.about_patternimg!}" src="${res_base_url!}/source/images/svg/loader/orange.progress-bar-stripe-loader.svg" onerror="imgError(this)">
<#--                <#else>-->
<#--                    <img-->
<#--                        src="${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if>"-->
<#--                        srcset="<#if settings.rimage_cover_lqip == 'loading'>${res_base_url!}/source/images/svg/loader/orange.progress-bar-stripe-loader.svg<#else>${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if>&th=150</#if>"-->
<#--                        data-srcset="${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if>&th=640 640w,-->
<#--                            ${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if>&th=960 960w,-->
<#--                            ${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if>&th=1280 1280w,-->
<#--                            ${settings.rimage_url!}?postid=${post.id}&type=url&itype=${settings.rimage_cover_itype!}<#if settings.rimage_cover_itype != 'image'>&id=${(settings.rimage_cover_id)!''}</#if> 1440w"-->
<#--                        data-sizes="auto"-->
<#--                        class="lazyload<#if settings.rimage_cover_lqip == 'lowquality'> blur-up</#if>" />-->
                </#if>
            </div>
            <header class="pattern-header">
                <#if options.about_title?default("")?trim?length gt 1>
                    <h1 class="entry-title">${options.about_title}</h1>
                <#else>
                    <h1 class="entry-title">关于</h1>
                </#if>
            </header>
        </div>
    <#else>
        <div class="blank"></div>
        <style>
            .toc-container {
                top: 210px;
            }
		</style>
    </#if>
</@header>
<div id="primary" class="content-area">
    <main id="main" class="site-main" role="main">
        <article>
            <#if (metas.toc?boolean)!true>
                <div class="toc-container">
                    <div class="toc"></div>
                </div>
            </#if>
            <div class="entry-content">
                ${settings.about_content!}
                <p>FIN. <i class="fa fa-meetup" aria-hidden="true" style="color:#d34836"></i></p>
            </div><!-- .entry-content -->
        </article><!-- #post-## -->
    </main><!-- #main -->
</div><!-- #primary -->
<#include "footer.ftl">
<@footer />

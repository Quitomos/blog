<#--
/**
 * The template for displaying the footer.
 *
 * Contains the closing of the #content div and all content after.
 *
 * @link https://developer.wordpress.org/themes/basics/template-files/#template-partials
 *
 * @package Akina
 */
-->
<#global res_base_url = settings.cdn?then("//cdn.jsdelivr.net/gh/Quitomos/cdn@master", theme_base)/>
<#macro footer>
</div><!-- #content -->
<#include "comments.ftl">
<#if is_post??>
	<@comment post,"post" />
<#elseif is_sheet??>
	<@comment sheet,"sheet" />
</#if>
<!-- 定义可变属性，会根据页面的改变而变化 -->
<script type='text/javascript'>
	/* <![CDATA[ */
    var PageAttr = {
        "metas": {
			<#if metas??>
				<#list metas?keys as key>
					"${key}": "${metas['${key}']}",
				</#list>
			</#if>
        },
    }
	/* ]]> */
</script>
</div><!-- #page Pjax container-->
<footer id="colophon" class="site-footer" role="contentinfo">
	<div class="site-info">
		<div class="footertext">
			<p class="foo-logo"
			   style="background-image: url('${settings.footer_logo?default('${res_base_url!}/img/sakura.svg')}');"></p>
			<@global.footer />
		</div>
		<div class="footer-device">
			<!-- 请尊重作者，请务必保留版权 -->
			<p style="font-family: 'Ubuntu', sans-serif;">
				<span>Stolen
 					<i class="fa fa-vimeo animated" style="color: #e74c3c;"></i> 
					from
					<a rel="me" target="_blank" href="https://github.com/LIlGG/halo-theme-sakura" style="text-decoration:none;">Halo.LIlGG</a>
				</span>
				 •
				<span>Crafted with
 					<i class="fa fa-heart animated" style="color: #e74c3c;"></i> 
					by 
					<a rel="me" target="_blank" href="https://github.com/Quitomos/blog" style="text-decoration:none;">QUITOMOS</a>
				</span>
			</p>
			<p>
				© ${.now?string("yyyy")} ${(user.nickname)!}
				<#if settings.footer_case_number??  && settings.footer_case_number != "">
				&nbsp;
				<a href="https://beian.miit.gov.cn/" target="_blank">${settings.footer_case_number}</a>
				</#if>
				<#if settings.footer_ga_case_number??  && settings.footer_ga_case_number != "">
				&nbsp;
				<a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=${settings.footer_ga_select_number!}" target="_blank">
					<img src="${res_base_url!}/img/other/gongan.png">${settings.footer_ga_case_number}
				</a>	
				</#if>
			</p>
		</div>
	</div><!-- .site-info -->
</footer><!-- #colophon -->
<div class="openNav">
	<div class="iconflat">
		<div class="icon"></div>
	</div>
	<div class="site-branding">
		<#if blog_logo?? && blog_logo!=''>
			<div class="site-title">
				<a href="${blog_url!}">
					<img src="${blog_logo!}">
				</a>
			</div>
		<#else>
			<h1 class="site-title"><a href="${blog_url!}">${blog_title!}</a></h1>
		</#if>
	</div>
</div><!-- m-nav-bar -->
</section><!-- #section -->
<!-- m-nav-center -->
<div id="mo-nav">
	<div class="m-avatar">
		<img src="${(user.avatar)!'${res_base_url!}/img/avatar.jpg'}">
	</div>

	<#if settings.glitch_text??>
		<p style="text-align: center; color: #333; font-weight: 900; font-family: 'Ubuntu', sans-serif; letter-spacing: 1.5px">${settings.glitch_text}</p>
	</#if>

	<#if settings.focus_infos!true>
		<p style="display:flex; justify-content:center;">
<#--			<#if settings.twitter??>-->
<#--				<a href="${settings.twitter!}" class="social social-twitter" target="_blank"><img src="${res_base_url!}/img/sns/twitter.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.sina??>-->
<#--				<a href="${settings.sina!}" class="social social-sina" target="_blank"><img src="${res_base_url!}/img/sns/sina.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.github??>-->
<#--				<a href="${settings.github!}" class="social social-github" target="_blank"><img src="${res_base_url!}/img/sns/github.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.wechat??>-->
<#--				<a href="${settings.wechat!}" class="social social-wechat" target="_blank"><img src="${res_base_url!}/img/sns/wechat.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.qq??>-->
<#--				<a href="//wpa.qq.com/msgrd?v=3&uin=${settings.qq!}&site=qq&menu=yes" class="social social-wangyiyun" target="_blank"><img src="${res_base_url!}/img/sns/qq.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.bili??>-->
<#--				<a href="${settings.bili!}" class="social social-bili" target="_blank"><img src="${res_base_url!}/img/sns/bilibili.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.wangyiyun??>-->
<#--				<a href="${settings.wangyiyun!}" class="social social-wangyiyun" target="_blank"><img src="${res_base_url!}/img/sns/wangyiyun.png" width="18"/></a>-->
<#--			</#if>-->
<#--			<#if settings.customize_link?? && settings.customize_icon?? && settings.customize_title??>-->
<#--				<a href="${settings.customize_link!}" class="social" target="_blank" title="${settings.customize_title!}">-->
<#--					<img src="${settings.customize_icon}" width="18"/>-->
<#--				</a>-->
<#--			</#if>-->
			<#if accountList?has_content>
				<#list accountList as account>
					<a href="${account.accountUrl!}" class="social" target="_blank" title="${account.accountName!}">
						<img src="${account.accountIcon}" width="18"/>
					</a>
				</#list>
			</#if>
		</p>
	</#if>

	<div class="m-search">
		<form class="m-search-form" method="get" action="/search" role="search">
			<input class="m-search-input" type="search" name="keyword" placeholder="搜索..." required>
		</form>
	</div>
	<#include "layouts/nav.ftl">
	<p class="m-footer">© ${.now?string("yyyy")} ${(user.nickname)!}</p>
</div><!-- m-nav-center end -->
<a href="#" class="cd-top"></a>
<!-- m-cd-top start -->
<button class="m-cd-top" title="Go to top">
	<i class="fa fa-chevron-up" aria-hidden="true"></i>
</button>
<!-- m-cd-top end -->
<!-- search start -->
<form class="js-search search-form search-form--modal" method="get" action="/search" role="search">
	<div class="search-form__inner">
		<div>
			<p class="micro mb-">输入后按回车搜索 ...</p>
			<i class="iconfont icon-search"></i>
			<input class="text-input" type="search" name="keyword" placeholder="Search" required>
		</div>
	</div>
	<div class="search_close"></div>
</form>
<!-- search end -->
<!-- aplayer start -->
<#if settings.is_aplayer && settings.aplayer_float>
<div id="aplayer-float" style="z-index: 100;" class="aplayer" data-global="true" data-id="${settings.aplayer_id!'6733416294'}" data-server="${settings.aplayer_server!'netease'}" data-type="${settings.aplayer_type!'playlist'}" data-fixed="true" data-preload="${settings.aplayer_preload!'none'}" data-order="${settings.aplayer_order!'random'}" data-theme="${settings.aplayer_theme!'orange'}" data-autoplay="${(settings.aplayer_autoplay!false)?string('true', 'false')}"></div>
</#if>
<!-- aplayer end -->
<!-- theme-change start -->
<#if settings.theme_change!true>
<div class="changeSkin-gear no-select">
	<div class="keys">
        <span id="open-skinMenu">切换主题 | Theme Changer &nbsp;<i class="iconfont icon-gear inline-block rotating"></i></span>
    </div>
</div>
<div class="skin-menu no-select">
	<div class="theme-controls row-container">
		<ul class="menu-list">
<#--			<#list 0..7 as i>-->
<#--			<#assign iconStr="settings.bg_icon_${i}" icon = (iconStr?eval)?default("fa fa-television") />-->
<#--			<#assign descStr="settings.bg_desc_${i}" desc = (descStr?eval)?default("") />-->
<#--			<li id="bg_${i}" +data-text="${desc}">-->
<#--            	<i class="${icon}" aria-hidden="true" ></i>-->
<#--          	</li>-->
<#--			</#list>-->
<#--			写死好了-->
			<li id="bg_0" data-text="默认是纯洁的雪白~">
				<i class="fa fa-television" aria-hidden="true" ></i>
			</li>
			<li id="bg_1" data-text="呐，家乡的樱花开了~">
				<i class="iconfont icon-sakura" aria-hidden="true" ></i>
			</li>
			<li id="bg_2" data-text="格子控！">
				<i class="fa fa-slack" aria-hidden="true" ></i>
			</li>
			<li id="bg_3" data-text="小点点~">
				<i class="iconfont icon-dots" aria-hidden="true" ></i>
			</li>
			<li id="bg_4" data-text="柑橘味香气~">
				<i class="fa fa-lemon-o" aria-hidden="true" ></i>
			</li>
			<li id="bg_5" data-text="pixiv主题~">
				<i class="iconfont icon-pixiv" aria-hidden="true" ></i>
			</li>
			<li id="bg_6" data-text="随机图片">
				<i class="iconfont icon-bing" aria-hidden="true" ></i>
			</li>
			<li id="bg_7" data-text="关灯！">
				<i class="fa fa-moon-o" aria-hidden="true" ></i>
			</li>
		</ul>
	</div>
</div>
</#if>
<!-- theme-change end -->
<!-- 定义全局属性 -->
<script type='text/javascript'>
	/* <![CDATA[ */
	var Poi = {
		"pjax":"${(settings.poi_pjax!true)?string('true','')}",
		"windowheight":"${(!(settings.focus_height!true))?string('fixed','auto')}",
		"ajaxurl":"${blog_url!}",
		"resBaseUrl": "${res_base_url!}",
		"formpostion":"bottom",
		"toc": "${(settings.post_toc!true)?string('true','')}",
		"codeLine": "${(settings.code_line!true)?string('true','')}",
		"themeChange": "${(settings.code_line!true)?string('true','')}",
		"headFocus": "${(settings.head_focus!true)?string('true','')}",
		"bgvideo": "${(settings.bgvideo!false)?string('true','')}",
		"tagRandomColorMin": "${settings.tag_randomColorMin!0.965}",
		"tagRandomColorMax": "${settings.tag_randomColorMax!0.969}",
		"nickname": "${user.nickname!}",
		"sitename": "${blog_title!}",
		"themeBase": "${theme_base!}",
		"openToast": "${(settings.open_toast!true)?string('true','')}",
		"toastWidth": ${settings.toast_width!260},
		"toastHeight": ${settings.toast_height!60},
		"toastTop": "${settings.toast_top!"top"}",
		"toastBackground": "${settings.theme_skin!'#fe9600'}",
		"toastColor": "${settings.toast_color!'#ffffff'}",
		"toastFontSize": ${settings.toast_font_size!14},
		"copyMonitor": "${(settings.copy_monitor!true)?string('true','')}",
		"copyrightNotice": "${(settings.copyright_notice!true)?string('true','')}",
		"photosStyle": "${(settings.photos_style)!'masonry'}",
		"photosGutter": ${(settings.photos_gutter)!10},
		"tocDepth": ${(settings.toc_depth)!0},
		"i18n": "${settings.i18n!'auto'}",
		"mathjax": "${(settings.mathjax!false)?string('true','')}",
		"coverNum": "${(settings.rimage_cover_back_num)!'0'}",
		"rimageUrl": "${(settings.rimage_url)!''}",
		"coverOpen": "${(settings.rimage_cover_back_open!false)?string('true','')}",
		"meEmail": "${(settings.email)!''}",
		"defaultTheme": "${(settings.default_theme)!'bg_0'}",
		"defaultGroup": "${(settings.default_group!'')?replace(' ', '-')}"
	};
	var bgConfig = {
<#--	<#list 0..7 as i>-->
<#--		<#assign name = (("settings.bg_name_" + i)?eval)?default(""), -->
<#--				desc = (("settings.bg_desc_" + i)?eval)?default(""),-->
<#--				url = (("settings.bg_url_" + i)?eval)?default(""),-->
<#--				strategy = (("settings.bg_img_strategy_" + i)?eval)?default(""),-->
<#--				isNight = (("settings.bg_night_" + i)?eval)?default("") />-->
<#--		-->
<#--		"bg_${i}": {-->
<#--			"id": "${i}",-->
<#--			"name": "${name}",-->
<#--			"desc": "${desc}",-->
<#--			"url": "${url}",-->
<#--			"strategy": "${strategy}",-->
<#--			"isNight": "${(isNight!true)}"-->
<#--		},-->
<#--	</#list>-->
		<#--写死好了-->
		"bg_0": {
			"id": "0",
			"name": "white",
			"desc": "默认是纯洁的雪白~",
			"url": "",
			"strategy": "",
			"isNight": ""
		},

		"bg_1": {
			"id": "1",
			"name": "sakura",
			"desc": "呐，家乡的樱花开了~",
			"url": "${res_base_url}/img/bg/bg_1.png",
			"strategy": "none",
			"isNight": ""
		},

		"bg_2": {
			"id": "2",
			"name": "grids",
			"desc": "格子控！",
			"url": "${res_base_url}/img/bg/bg_2.jpg",
			"strategy": "none",
			"isNight": ""
		},

		"bg_3": {
			"id": "3",
			"name": "dots",
			"desc": "小点点~",
			"url": "${res_base_url}/img/bg/bg_3.png",
			"strategy": "none",
			"isNight": ""
		},

		"bg_4": {
			"id": "4",
			"name": "orange",
			"desc": "柑橘味香气~",
			"url": "${res_base_url}/img/bg/bg_4.jpg",
			"strategy": "none",
			"isNight": ""
		},

		"bg_5": {
			"id": "5",
			"name": "pixiv",
			"desc": "pixiv主题~",
			"url": "${res_base_url}/img/bg/bg_5.png",
			"strategy": "none",
			"isNight": ""
		},

		"bg_6": {
			"id": "6",
			"name": "random",
			"desc": "随机图片",
			"url": "https://picsum.photos/200/300/?blur=2",
			"strategy": "cover",
			"isNight": ""
		},

		"bg_7": {
			"id": "7",
			"name": "dark",
			"desc": "关灯！",
			"url": "${res_base_url}/img/bg/bg_7.png",
			"strategy": "cover",
			"isNight": "true"
		},
	};
	/* ]]> */
</script>
<script type='text/javascript' src='${res_base_url!}/script/utils.min.js?ver=1.3.1'></script>
<script type="text/javascript" src="${res_base_url!}/source/lib/lazysizes/lazysizes.min.js"></script>
<script type="text/javascript" src="${res_base_url!}/source/js/lib.js"></script>
<!-- 相册 -->
<#if settings.photos_style == "justify">
<script src="${res_base_url!}/source/lib/justifiedGallery/jquery.justifiedGallery.min.js"></script>
<#elseif settings.photos_style == "masonry" || settings.photos_style == "packery">
<script src="${res_base_url!}/source/lib/isotope.pkgd.min/index.js"></script>
<script src="${res_base_url!}/source/lib/imagesloaded/imagesloaded.pkgd.min.js"></script>
<#if settings.photos_style == "packery">
<script type='text/javascript' src='${res_base_url!}/source/lib/packery-mode.pkgd.min/index.js'></script>
</#if>
</#if>
<script type="text/javascript" src="${res_base_url!}/source/js/highlight/highlight.pack.js"></script>
<#if settings.code_line!true>
	<script type="text/javascript" src="${res_base_url!}/source/js/highlight/highlightjs-line-numbers.min.js"></script>
</#if>
<#if settings.is_aplayer!false>
	<script src="${res_base_url!}/source/lib/APlayer/APlayer.min.js" defer></script>
	<script src="${res_base_url!}/plugins/aplayer/js/index.js" defer></script>
</#if>
<#if settings.post_toc!true>
	<script src="${res_base_url!}/source/lib/tocbot/dist/tocbot.min.js" defer></script>
</#if>
<script src="${res_base_url!}!'/js/halo-comment.js'}" defer></script>
<#if settings.tag_cloud!true>
<script src="${res_base_url!}/source/lib/jqcloud2/jqcloud.min.js" defer></script>
<script type='text/javascript'>
	var wordcloud = [
		<@tagTag method="list" contextPath="${theme_base!}">
		<#list tags as tag>
		{'text': '${tag.name!}', 'weight': '${tag.postCount!}', 'link': '${tag.fullPath!}'},
		</#list>
		</@tagTag>
	]
</script>
</#if>
<#if settings.category_radar!true>
<script src="${res_base_url!}/source/js/echarts/echarts.min.js" defer></script>
<script type='text/javascript'>
	var categoryRadar = {
		<@categoryTag method="list" contextPath="${theme_base!}">
		<#list categories as category>
		'${category.name!}': '${category.postCount!}',
		</#list>
		</@categoryTag>
	}
</script>
</#if>
<script type='text/javascript' src='${res_base_url!}/source/js/qrcode.min.js' defer></script>
<script type='text/javascript' src='${res_base_url!}/source/lib/flv.min/index.js' defer></script>
<script type='text/javascript' src='${res_base_url!}/script/i18n.min.js?ver=1.3.1' defer></script>
<script type='text/javascript' src='${res_base_url!}/script/app.min.js?ver=1.3.1'></script>
<#nested />
<#if settings.live2d_switch!true>
<script src="${res_base_url!}/source/lib/jquery-ui/jquery-ui.min.js" async defer></script>
<#include "plugins/live2d/ftl/live2d.ftl">
<@live2d/>
</#if>
<div class="site-statistics">
	<@global.statistics />
</div>
</body>
</html>
</#macro>
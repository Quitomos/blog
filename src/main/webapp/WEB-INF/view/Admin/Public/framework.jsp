<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="${sessionScope.option.optionSiteIcon}">
    <title>
        ${sessionScope.option.optionSiteTitle}后台
            <rapid:block name="title"></rapid:block>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="${pageContext.request.contextPath}/admin" style="color:#009688;">
        ${sessionScope.option.optionSiteTitle}后台
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/" target="_blank">前台首页</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${sessionScope.backUser.userAvatar}" class="layui-nav-img">
                    ${sessionScope.backUser.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/admin/user/profile?userid=${sessionScope.backUser.userId}">个人信息</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/logout">退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->


            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">文章</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/admin/article/insert">写文章</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/article/list">全部文章</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/category">文章分类</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/tag">文章标签</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">公告</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/admin/notice/list">全部公告</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/notice/insert">添加公告</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">用户</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/admin/user/list">全部用户</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/user/insert">添加用户</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/admin/comment">
                    评论
                </a>
            </li>
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/admin/journal">
                        日志
                    </a>
                </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/admin/links">友情链接</a>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/admin/option">站点信息设置</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/menu">顶部导航设置</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/account">外链账户设置</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/admin/page">其他页面设置</a></dd>
                </dl>
            </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        ©${sessionScope.option.optionSiteTitle}
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/layui/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/js/back.js"></script>
<rapid:block name="footer-script">

</rapid:block>
<script>
    //给文本编辑器的iframe引入代码高亮的css
    $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"${pageContext.request.contextPath}/css/highlight.css\">\n");
</script>

</body>
</html>

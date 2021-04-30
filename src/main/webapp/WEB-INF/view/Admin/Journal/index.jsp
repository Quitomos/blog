<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cn.quitomos.blog.enums.ArticleStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 日志
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input {
            display: inline-block;
            width: 33.333% !important;
        }
        .layui-input-block {
            margin:0px 10px;
        }
        .layui-table {
            margin-top: 0;
        }
        .layui-col-md4 {
            padding:10px;
        }
        .layui-col-md8 {
            padding:10px;
        }
        .layui-btn {
            margin: 2px 0!important;
        }
    </style>

    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
            <a href="${pageContext.request.contextPath}/admin">首页</a>
            <a><cite>日志</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>添加日志</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/journal/insertSubmit">

                <br>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">内容 <span style="color: #FF5722; ">*</span></label>
                    <div class="layui-input-block">
                        <textarea name="journalContent" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">临时图片 </label>
                    <div class="layui-input-block">
                        <input name="contentImage" id="contentImage" type="text" value="" placeholder="图片链接" autocomplete="off" class="layui-input" disabled>
                        <p id="demoText2"></p>
                        <button type="button" class="layui-btn" id="test2">上传图片</button>
                    </div>
                </div>
                <br>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="layui-col-md8">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>日志列表</legend>
            </fieldset>
            <div class="layui-tab layui-tab-card">
                <form id="articleForm" method="post">
                    <input type="hidden" name="currentUrl" id="currentUrl" value="">
                    <table class="layui-table">
                        <colgroup>
                            <col width="50">
                            <col width="300">
                            <col width="150">
                            <col width="50">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>内容</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="a">
                            <tr>
                                <td>${a.journalId}</td>
                                <td>${a.journalContent}</td>
                                <td>
                                    <fmt:formatDate value="${a.journalCreateTime}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a href="javascript:void(0)"
                                       onclick="deleteJournal(${a.journalId})"
                                       class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
                <%@ include file="../Public/paging.jsp" %>
            </div>
        </div>
    </div>


</rapid:override>
<rapid:override name="footer-script">
    <script>
        //上传图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test2',
                url: '${pageContext.request.contextPath}/admin/journal/contentimg',
                before: function (obj) {
                    console.log(obj);
                },
                done: function (res) {
                    $("#contentImage").attr("value", res.data.src);
                    if (res.code > 0) {
                        return layer.msg(res.msg);
                    }
                },
                error: function () {
                    var demoText = $('#demoText2');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });
        });</script>
</rapid:override>
<%@ include file="../Public/framework.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 页面设置
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
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
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
                <span class="layui-breadcrumb" lay-separator="/">
                  <a href="${pageContext.request.contextPath}/admin">首页</a>
                    <a>设置</a>
                  <a><cite>其他页面设置</cite></a>
                </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>添加页面</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/page/insertSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        图片
                        <div class="layui-upload">
                            <div class="layui-upload-list" style="">
                                <img class="layui-upload-img" src="" id="demo1" width="192"
                                     height="108">
                                <p id="demoText"></p>
                            </div>
                            <button type="button" class="layui-btn" id="test1">上传图片</button>
                        </div>
                    </div>
                    <input type="hidden" name="pageImage" id="pageImage" value="">
                    <br>
                    <div class="layui-input-block">
                        标题 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="pageTitle" id="pageTitle"
                               placeholder="请输入页面标题" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        超链接
                        <input type="text" name="pageUrl" id="pageUrl"
                               placeholder="请输入超链接" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        超链接target
                        <input type="text" name="pageTarget" id="pageTarget"
                               placeholder="请输入target" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        order
                        <input type="text" name="pageOrder" id="pageOrder"
                               placeholder="请输入排序值" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        描述
                        <input type="text" name="pageDescription" id="pageDescription"
                               placeholder="请输入页面描述" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="layui-col-md8">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>页面列表</legend>
            </fieldset>
            <form id="pageForm" method="post">
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="50">
                        <col width="130">
                        <col width="150">
                        <col width="100">
                        <col width="150">
                        <col width="200">
                        <col width="100">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>排序值</th>
                        <th>标题</th>
                        <th>超链接</th>
                        <th>target</th>
                        <th>描述</th>
                        <th>图片</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageList}" var="p">
                        <tr>
                            <td>${p.pageId}</td>
                            <td>${p.pageOrder}</td>
                            <td>${p.pageTitle}</td>
                            <td>
                                <a href="${p.pageUrl}"
                                   target="_blank">
                                        ${p.pageUrl}
                                </a>
                            </td>
                            <td>${p.pageTarget}</td>
                            <td>${p.pageDescription}</td>
                            <td>
                                <img src="${pageContext.request.contextPath}/img/page/${p.pageImage}" width="96" height="54">
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/page/edit?pageid=${p.pageId}"
                                   class="layui-btn layui-btn-mini">编辑</a>
                                <a href="${pageContext.request.contextPath}/admin/page/delete?pageid=${p.pageId}"
                                   class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
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
                elem: '#test1',
                url: '${pageContext.request.contextPath}/admin/page/image',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#pageImage").attr("value", res.data.title);
                    if (res.code > 0) {
                        return layer.msg(res.msg);
                    }
                },
                error: function () {
                    var demoText = $('#demoText');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });

        });
    </script>
</rapid:override>
<%@ include file="../Public/framework.jsp" %>

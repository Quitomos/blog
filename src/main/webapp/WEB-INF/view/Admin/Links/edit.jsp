<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 友情链接
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
                  <a><cite>友情链接</cite></a>
                </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>编辑友链</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/links/insertSubmit">
                <div class="layui-form-item">
                    <input type="hidden" name="linksId" value="${links.linksId}">
                    <div class="layui-input-block">
                        图片
                        <div class="layui-upload">
                            <div class="layui-upload-list" style="">
                                <img class="layui-upload-img" src="${links.linksImage}" id="demo1" width="100"
                                     height="100">
                                <p id="demoText"></p>
                            </div>
                            <button type="button" class="layui-btn" id="test1">上传图片</button>
                        </div>
                    </div>
                    <input type="hidden" name="linksImage" id="linksImage" value="${links.linksImage}">
                    <br>
                    <div class="layui-input-block">
                        名字 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="linksName" id="linksName"
                               value="${links.linksName}" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        超链接 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="linksUrl" id="linksUrl"
                               value="${links.linksUrl}" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        介绍
                        <input type="text" name="linksDescription" id="linksDescription"
                               value="${links.linksDescription}" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        order
                        <input type="text" name="linksOrder" id="linksOrder"
                               value="${links.linksOrder}" autocomplete="off" class="layui-input">
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="layui-col-md8">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>友链列表</legend>
            </fieldset>
            <form id="linksForm" method="post">
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="100">
                        <col width="150">
                        <col width="200">
                        <col width="200">
                        <col width="50">
                        <col width="100">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>名字</th>
                        <th>超链接</th>
                        <th>介绍</th>
                        <th>图片</th>
                        <th>order</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${linksList}" var="p">
                        <tr>
                            <td>${p.linksId}</td>
                            <td>${p.linksName}</td>
                            <td>
                                <a href="${p.linksUrl}"
                                   target="_blank">
                                        ${p.linksUrl}
                                </a>
                            </td>
                            <td>${p.linksDescription}</td>
                            <td>
                                <img src="${p.linksImage}" width="96" height="54">
                            </td>
                            <td>${p.linksOrder}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/links/edit?linksid=${p.linksId}"
                                   class="layui-btn layui-btn-mini">编辑</a>
                                <a href="${pageContext.request.contextPath}/admin/links/delete?linksid=${p.linksId}"
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
                url: '${pageContext.request.contextPath}/admin/links/image',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#linksImage").attr("value", res.data.src);
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

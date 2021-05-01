<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 站点信息
</rapid:override>
<rapid:override name="header-style">
    <style>
        .layui-form-item .layui-input-inline {
            width: 300px;
        }
        .layui-form-label {
            width: 120px;
        }
        .layui-word-aux {
            color: #FF5722 !important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/admin">首页</a>
              <a>设置</a>
              <a><cite>站点信息设置</cite></a>
        </span>
    </blockquote>
    <br><br>
    <form class="layui-form" action="${pageContext.request.contextPath}/admin/option/editSubmit"
          method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">站点名称 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" value="${option.optionSiteTitle}" name="optionSiteTitle" id="optionSiteTitle" required
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">站点图标</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img class="layui-upload-img" src="${option.optionSiteIcon}" id="demo1" width="100"
                             height="100">
                        <p id="demoText"></p>
                    </div>
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                    <input type="hidden" name="optionSiteIcon" id="optionSiteIcon" value="${option.optionSiteIcon}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">站点描述 </label>
            <div class="layui-input-inline">
                <input type="text" name="optionMetaDescription" value="${option.optionMetaDescription}"
                       placeholder="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">欢迎语 </label>
            <div class="layui-input-inline">
                <input type="text" name="optionMetaHello" value="${option.optionMetaHello}" id="optionMetaHello"
                       class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">博主用户id <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" value="${option.optionHostId}" name="optionHostId" id="optionHostId" required
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">访问量 </label>
            <div class="layui-input-inline">
                <input type="text" value="${option.optionViews}" placeholder="" autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <br>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">关于我 </label>
            <div class="layui-input-block">
                <textarea name="optionAbout" class="layui-textarea">${option.optionAbout}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">临时图片上传 </label>
            <div class="layui-input-inline">
                <input name="aboutMeImage" id="aboutMeImage" type="text" value="" placeholder="图片链接" autocomplete="off" class="layui-input" disabled>
                <p id="demoText2"></p>
                <button type="button" class="layui-btn" id="test2">上传图片</button>
            </div>
        </div>
        <br>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">保存</button>
            </div>
        </div>
    </form>


</rapid:override>
<rapid:override name="footer-script">

    <script>
        //上传icon
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '${pageContext.request.contextPath}/admin/option/icon',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#optionSiteIcon").attr("value", res.data.src);
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

        //上传图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test2',
                url: '${pageContext.request.contextPath}/admin/option/icon',
                before: function (obj) {
                    console.log(obj);
                },
                done: function (res) {
                    $("#aboutMeImage").attr("value", res.data.src);
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
        });
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp" %>

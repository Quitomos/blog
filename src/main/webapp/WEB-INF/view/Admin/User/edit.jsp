<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 编辑用户
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
              <a>用户</a>
              <a><cite>编辑用户</cite></a>
        </span>
    </blockquote>
    <br><br>
    <form class="layui-form" action="${pageContext.request.contextPath}/admin/user/editSubmit" id="userForm"
          method="post">
        <input type="hidden" name="userId" id="userId" value="${backUser.userId}">
        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img class="layui-upload-img" src="${pageContext.request.contextPath}/img/avatar/${backUser.userAvatar}" id="demo1" width="100"
                             height="100">
                        <p id="demoText"></p>
                    </div>
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                    <input type="hidden" name="userAvatar" id="userAvatar" value="${backUser.userAvatar}">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" value="${backUser.userName}" name="userName" id="userName" required
                       lay-verify="userName"
                       autocomplete="off" class="layui-input" onblur="checkUserName()">
            </div>
            <div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="password" name="userPassword" value="${backUser.userPassword}" id="userPassword" required
                       autocomplete="off" class="layui-input" min="3" max="20">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="text" name="userNickname" value="${backUser.userNickname}" required
                       placeholder="" autocomplete="off" min="2" max="10"
                       class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Email <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <input type="email" name="userEmail" value="${backUser.userEmail}" id="userEmail" required
                       lay-verify="userEmail"
                       class="layui-input" onblur="checkUserEmail()">
            </div>
            <div class="layui-form-mid layui-word-aux" id="userEmailTips"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="userStatus" value="1" title="正常" <c:if test="${backUser.userStatus==1}">checked</c:if>>
                <input type="radio" name="userStatus" value="0" title="禁用" <c:if test="${backUser.userStatus==0}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <input type="radio" name="userRole" value="1" title="标准" <c:if test="${backUser.userRole.code==1}">checked</c:if>>
                <input type="radio" name="userRole" value="0" title="管理员" <c:if test="${backUser.userRole.code==0}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="demo1" id="submit-btn">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>


</rapid:override>
<rapid:override name="footer-script">

    <script>
        //上传图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '${pageContext.request.contextPath}/admin/user/upload',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#userAvatar").attr("value", res.data.title);
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 外链账户
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
            <a><cite>外链账户设置</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>添加账户</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/account/insertSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="accountName" placeholder="请输入账户名称, 如LeetCode" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        超链接
                        <input type="text" name="accountUrl" placeholder="请输入超链接" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标
                        <input type="text" name="accountIcon" placeholder="请输入图标" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        order
                        <input type="text" name="accountOrder" placeholder="请输入排序值" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md8" >
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>全部账户</legend>
            </fieldset>
            <table class="layui-table" >
                <colgroup>
                    <col width="50">
                    <col width="100">
                    <col width="300">
                    <col width="50">
                    <col width="50">
                    <col width="100">
                </colgroup>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>超链接</th>
                    <th>图标</th>
                    <th>order</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${accountList}" var="c">

                    <tr>
                        <td >${c.accountId}</td>
                        <td>${c.accountName}</td>
                        <td>
                            <a href="${c.accountUrl}" target="_blank">${c.accountUrl}</a>
                        </td>
                        <td>${c.accountIcon}</td>
                        <td>${c.accountOrder}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/account/edit?accountid=${c.accountId}" class="layui-btn layui-btn-mini">编辑</a>
                            <a href="${pageContext.request.contextPath}/admin/account/delete?accountid=${c.accountId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>



</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>

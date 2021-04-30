<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 文章标签
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
            <a>文章</a>
            <a><cite>文章标签</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4" >
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>编辑标签</legend>
            </fieldset>
            <form class="layui-form" method="post" id="myForm" action="${pageContext.request.contextPath}/admin/tag/editSubmit">
                <div class="layui-form-item">
                    <input type="hidden" name="tagId" value="${tag.tagId}">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="tagName" value="${tag.tagName}" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" value="${tag.tagDescription}" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md8" >
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>修改标签</legend>
            </fieldset>
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="50">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tagList}" var="c">

                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/tag?tagid=${c.tagId}" target="_blank">${c.tagName}</a>
                        </td>
                        <td >${c.articleCount}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/tag/edit?tagid=${c.tagId}" class="layui-btn layui-btn-mini">编辑</a>
                            <c:if test="${c.articleCount==0}">
                                <a href="${pageContext.request.contextPath}/admin/tag/delete?tagid=${c.tagId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                            </c:if>

                        </td>
                        <td >${c.tagId}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>如果该标签包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>






</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>

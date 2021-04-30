<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 全部评论
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-table {
            margin-top: 0;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/admin">首页</a>
              <a><cite>全部评论</cite></a>
        </span>
    </blockquote>
    <div class="layui-tab layui-tab-card">
        <table class="layui-table" lay-even lay-skin="nob">
            <colgroup>
                <col width="100">
                <col width="100">
                <col width="300">
                <col width=200">
                <col width="150">
                <col width="50">
            </colgroup>
            <thead>
            <tr>
                <th>作者</th>
                <th>邮箱</th>
                <th>评论内容</th>
                <th>回复至</th>
                <th>提交于</th>
                <th>ID</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="c">
                <tr>
                    <td>
                        <img src="${c.commentAvatar}" alt="" width="64px">
                        <strong>${c.commentName}</strong>
                        <br>
                            ${c.commentIp}/${c.commentOs}
                    </td>
                    <td>${c.commentEmail}</td>
                    <td class="dashboard-comment-wrap">
                            ${c.commentContent}
                        <div class="row-actions">
                                     <span class="">
                                        <a href="${pageContext.request.contextPath}/admin/comment/reply?commentid=${c.commentId}">
                                            回复
                                        </a>
                                     </span>

                                    <span class=""> |
                                        <a href="javascript:void(0)" onclick="deleteComment(${c.commentId})">删除</a>
                                     </span>
                        </div>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/article/${c.article.articleId}"
                           target="_blank">${c.article.articleTitle}</a>
                    </td>
                    <td>
                        <fmt:formatDate value="${c.commentCreateTime}" pattern="yyyy年MM月dd日 HH:dd:ss"/>
                    </td>
                    <td>${c.commentId}</td>

                </tr>
            </c:forEach>
            </tbody>

        </table>

        <div id="nav" style="">
            <%@ include file="../Public/paging.jsp" %>
        </div>
    </div>


</rapid:override>

<%@ include file="../Public/framework.jsp" %>

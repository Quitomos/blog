<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 文章分类
    </rapid:override>
<rapid:override name="header-style">
    <style>
        .layui-input-block {
            margin:0px 10px;
        }
    </style>
</rapid:override>

<rapid:override name="content">


    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="${pageContext.request.contextPath}/admin">首页</a>
              <a>文章</a>
              <a><cite>文章分类</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4" >
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>编辑分类</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/category/editSubmit">
                <div class="layui-form-item">
                    <input type="hidden" name="categoryId" value="${category.categoryId}">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="categoryName" value="${category.categoryName}" placeholder="" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        父节点 <span style="color: #FF5722; ">*</span>
                        <select name="categoryPid" class="layui-input" required>
                            <option value="0" <c:if test="${category.parentCategory == null}">selected</c:if> >无</option>
                            <c:forEach items="${categoryList}" var="c">
                                <c:if test="${c.categoryId != category.categoryId}">
                                    <c:choose>
                                        <c:when test="${category.parentCategory != null and c.categoryId==category.parentCategory.categoryId}">
                                            <option value="${c.categoryId}" selected>${c.categoryName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${c.parentCategory == null}">
                                                <option value="${c.categoryId}">${c.categoryName}</option>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        分类描述
                        <input type="text" name="categoryDescription" value="${category.categoryDescription}"  class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        order
                        <input type="text" name="categoryOrder" value="${category.categoryOrder}" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标样式
                        <input type="text" name="categoryIcon" value="${category.categoryIcon}"class="layui-input" >
                    </div>
                    <br>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
            <br><br>
        </div>
        <div class="layui-col-md8" >
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>全部分类</legend>
            </fieldset>
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="50">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                    <th>父节点ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="c">
                    <c:if test="${c.parentCategory == null}">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/category/${c.categoryId}" target="_blank">${c.categoryName}</a>
                            </td>
                            <td>${c.articleCount}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/category/edit?categoryid=${c.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                <c:if test="${c.articleCount==0}">
                                    <a href="${pageContext.request.contextPath}/admin/category/delete?categoryid=${c.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                </c:if>
                            </td>
                            <td>${c.categoryId}</td>
                            <c:if test="${c.parentCategory != null}">
                                <td>${c.parentCategory.categoryId}</td>
                            </c:if>
                        </tr>
                        <c:forEach items="${categoryList}" var="c2">
                            <c:if test="${c2.parentCategory != null and c2.parentCategory.categoryId==c.categoryId}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category/${c2.categoryId}" target="_blank">——${c2.categoryName}</a>
                                    </td>
                                    <td>${c2.articleCount}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/category/edit?categoryid=${c2.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                        <c:if test="${c2.articleCount==0}">
                                            <a href="${pageContext.request.contextPath}/admin/category/delete?categoryid=${c2.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                        </c:if>
                                    </td>
                                    <td class="cate-parent">${c2.categoryId}</td>
                                    <td>${c2.parentCategory.categoryId}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>


                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                <ul>
                    <li>分类最多只有两级</li>
                    <li>如果该分类包含文章，则不可删除</li>
                </ul>
            </blockquote>
        </div>

    </div>






</rapid:override>
<rapid:override name="footer-script">

</rapid:override>

<%@ include file="../Public/framework.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 导航设置
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
              <a><cite>顶部导航设置</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>添加导航项</legend>
            </fieldset>
            <form class="layui-form"  method="post" id="myForm" action="${pageContext.request.contextPath}/admin/menu/insertSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="menuName" placeholder="请输入名称" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        父节点 <span style="color: #FF5722; ">*</span>
                        <select name="menuPid" class="layui-input" required>
                            <option value="0">无</option>
                            <c:forEach items="${menuList}" var="c">
                                <c:if test="${c.parentMenu==null}">
                                    <option value="${c.menuId}">${c.menuName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        order
                        <input type="text" name="menuOrder" placeholder="请输入优先级, 越大越优先" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        图标样式
                        <input type="text" name="menuIcon" placeholder="请输入图标样式,如 fa fa-coffee" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        超链接
                        <input type="text" name="menuUrl" placeholder="请输入超链接" autocomplete="off" class="layui-input" >
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
                <legend>全部导航项</legend>
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
                    <th>超链接</th>
                    <th>操作</th>
                    <th>ID</th>
                    <th>父节点ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${menuList}" var="c">
                    <c:if test="${c.parentMenu==null}">
                        <tr>
                            <td>${c.menuName}</td>
                            <td>
                                <a href="${c.menuUrl}" target="_blank">${c.menuUrl}</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/menu/edit?menuid=${c.menuId}" class="layui-btn layui-btn-mini">编辑</a>
                                <a href="${pageContext.request.contextPath}/admin/menu/delete?menuid=${c.menuId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                            </td>
                            <td>${c.menuId}</td>
                            <c:if test="${c.parentMenu != null}">
                                <td>${c.parentMenu.menuId}</td>
                            </c:if>
                        </tr>
                        <c:forEach items="${menuList}" var="c2">
                            <c:if test="${c2.parentMenu != null and c2.parentMenu.menuId==c.menuId}">
                                <tr>
                                    <td>——${c2.menuName}</td>
                                    <td>
                                        <a href="${c2.menuUrl}" target="_blank">${c2.menuUrl}</a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/menu/edit?menuid=${c2.menuId}" class="layui-btn layui-btn-mini">编辑</a>
                                        <a href="${pageContext.request.contextPath}/admin/menu/delete?menuid=${c2.menuId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                    </td>
                                    <td class="cate-parent">${c2.menuId}</td>
                                    <td>${c2.parentMenu.menuId}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>


                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                <ul>
                    <li>导航最多只有两级</li>
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

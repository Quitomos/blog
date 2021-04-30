<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

    <rapid:override name="title">
        - 全部公告
    </rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-table {
            margin-top: 0;
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
            <a>公告</a>
            <a><cite>全部公告</cite></a>
        </span>
    </blockquote>


            <table class="layui-table" >
                <colgroup>
                    <col width="400">
                    <col width="50">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>标题</th>
                    <th>Order</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                    <td>ID</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${noticeList}" var="c">

                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/notice?noticeid=${c.noticeId}" target="_blank">${c.noticeTitle}</a>
                        </td>
                        <td>
                                ${c.noticeOrder}
                        </td>
                        <td>
                            <fmt:formatDate value="${c.noticeCreateTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${c.noticeUpdateTime}"
                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/notice/edit?noticeid=${c.noticeId}" class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:void(0)"
                               onclick="deleteNotice(${c.noticeId})"
                               class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                        </td>
                        <td >${c.noticeId}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>


</rapid:override>
<rapid:override name="footer-script">
    <script>
        // 删除公告
        function deleteNotice(id) {
            if(confirmDelete()==true){
                $.ajax({
                    async: false,
                    type: "POST",
                    url: getContextPath() + '/admin/notice/delete?noticeid='+id,
                    contentType : "application/x-www-form-urlencoded; charset=utf-8",
                    dataType: "text",
                    complete:function () {
                        window.location.reload();
                    }
                })
            }
        }
    </script>
</rapid:override>


<%@ include file="../Public/framework.jsp"%>

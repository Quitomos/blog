<%--保留此处 start--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cn.quitomos.blog.enums.ArticleStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%--保留此处 end--%>
<rapid:override name="title">
    - 修改文章
</rapid:override>
<rapid:override name="header-style">

</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a>文章</a>
              <a><cite>修改文章</cite></a>
        </span>
    </blockquote>


    <form class="layui-form" method="post" id="myForm" action="${pageContext.request.contextPath}/admin/article/editSubmit">
        <input type="hidden" name="articleId" value="${article.articleId}">

        <div class="layui-form-item">
            <label class="layui-form-label">文章头图</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <div class="layui-upload-list" style="">
                        <img class="layui-upload-img" src="${article.articleImage}" id="demo1" width="430"
                             height="320">
                        <p id="demoText"></p>
                    </div>
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                </div>
            </div>
            <input type="hidden" name="articleImage" id="articleImage" value="${article.articleImage}">
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">标题 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="articleTitle" lay-verify="title" id="title" value="${article.articleTitle}"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">摘要 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="articleSummary" lay-verify="summary" id="articleSummary" value="${article.articleSummary}"
                       class="layui-input">
            </div>
        </div>
        <br>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <textarea name="articleContent" class="layui-textarea">${article.articleContent}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">临时图片 </label>
            <div class="layui-input-inline">
                <input name="contentImage" id="contentImage" type="text" value="" placeholder="图片链接" autocomplete="off" class="layui-input" disabled>
                <p id="demoText2"></p>
                <button type="button" class="layui-btn" id="test2">上传图片</button>
            </div>
        </div>
        <br>

        <div class="layui-form-item">
            <label class="layui-form-label">分类 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-inline">
                <select name="articleParentCategoryId" id="articleParentCategoryId"
                        lay-filter="articleParentCategoryId">
                    <option value="">一级分类</option>
                    <c:forEach items="${categoryList}" var="c">
                        <c:if test="${c.parentCategory == null}">
                            <option value="${c.categoryId}"
                                    <c:forEach items="${article.categoryList}" var="ca">
                                        <c:if test="${ca.categoryId == c.categoryId}">
                                            selected
                                        </c:if>
                                    </c:forEach>
                            >${c.categoryName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="articleChildCategoryId" id="articleChildCategoryId" lay-filter="articleChildCategoryId">
                    <c:forEach items="${categoryList}" var="c">
                        <c:forEach items="${article.categoryList}" var="ca">
                            <c:if test="${ca.categoryId == c.parentCategory.categoryId}">
                                <option value="${c.categoryId}"
                                    <c:forEach items="${article.categoryList}" var="cat">
                                        <c:if test="${cat.categoryId == c.categoryId}">
                                            selected
                                        </c:if>
                                    </c:forEach>
                                >${c.categoryName}</option>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">标签</label>
            <div class="layui-input-block">
                <c:forEach items="${tagList}" var="t">
                    <input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName}"
                           value="${t.tagId}"
                    <c:forEach items="${article.tagList}" var="t2">
                           <c:if test="${t.tagId == t2.tagId}">checked</c:if>
                    </c:forEach>>
                </c:forEach>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">order</label>
            <div class="layui-input-inline">
                <input type="number" name="articleOrder" value="${article.articleOrder}" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">输入1-10的数字,order越大排序越前</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="articleStatus" value="1" title="发布"
                       <c:if test="${article.articleStatus==ArticleStatus.PUBLISHED}">checked</c:if>>
                <input type="radio" name="articleStatus" value="0" title="草稿"
                       <c:if test="${article.articleStatus==ArticleStatus.DRAFT}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</rapid:override>


<rapid:override name="footer-script">

    <script>



        // 上传文章头图
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '${pageContext.request.contextPath}/admin/article/titleimg',
                before: function (obj) {
                    console.log(obj);
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#articleImage").attr("value", res.data.src);
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

        //上传内容图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test2',
                url: '${pageContext.request.contextPath}/admin/article/contentimg',
                before: function (obj) {
                    console.log(obj);
                },
                done: function (res) {
                    $("#contentImage").attr("value", res.data.src);
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

        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

            //自定义验证规则
            form.verify({
                title: function (value) {
                    if (value.length < 1) {
                        return '标题至少得1个字符啊';
                    }
                }
                , summary: function (value) {
                    if (value.length < 1) {
                        return '摘要至少得1个字符阿'
                    }
                }
                , pass: [/(.+){6,12}$/, '密码必须6到12位']
                , content: function (value) {
                    layedit.sync(editIndex);
                }
            });

            //二级联动
            form.on("select(articleParentCategoryId)",function () {
                var optionstring = "";
                var articleParentCategoryId = $("#articleParentCategoryId").val();
                <c:forEach items="${categoryList}" var="c">
                <c:if test="${c.parentCategory!=null}">
                if (articleParentCategoryId==${c.parentCategory.categoryId}) {
                    optionstring += "<option name='childCategory' value='${c.categoryId}'>${c.categoryName}</option>";
                }
                </c:if>
                </c:forEach>
                $("#articleChildCategoryId").html("<option value=''selected>二级分类</option><a>"+optionstring);
                form.render('select'); //这个很重要
            })

        });
        //end


    </script>

</rapid:override>


<%--此句必须放在最后--%>
<%@ include file="../Public/framework.jsp" %>


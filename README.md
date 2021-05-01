&emsp;应付秋招搭建的博客，前端盗用了[LIlGG大佬](https://github.com/LIlGG/halo-theme-sakura)的主题，后端参考了[ForestBlog](https://github.com/saysky/ForestBlog)，搞了个适配器来亡羊补牢适配主题。<br>
<br>
<br>
#### Tips
* 首页聚焦页可自定义多个，由于自己的服务器加载图片太慢了，因此把图片资源备份了一份用cdn加速。<br>
  如果添加额外的页面，要么手动cdn备份，要么修改 [feature.ftl](https://github.com/Quitomos/blog/blob/master/src/main/webapp/WEB-INF/view/templates/themes/sakura/layouts/feature.ftl#L19) 的地址关闭cdn。<br>
  
  

* 可以自定义多个SNS账户。<br>


* 虽然有个用户表，但和前台没啥关系。<br>


* 文章草稿状态没有用起来。<br>


* 公告功能暂未使用。
<head>
    <meta charset="utf-8">
    <title>大数据后台</title>

    <#--引入本地bootstarp-->
    <#assign path="${springMacroRequestContext.getContextPath()}">
    <script src="${path}/js/jquery_min.js"></script>
    <link rel="stylesheet" href="${path}/css/bootstrap_min.css">
    <#--<script src="${path}/js/jquery_min.js"></script>-->
    <script src="${path}/js/bootstrap_min.js"></script>
    <link rel="stylesheet" href="${path}/css/global.css">


    <!--这个叫做引用js文件（所谓的js就是操作页面动态的语言，可以通过这个sdk的js调到腾讯的接口，以便于完成对腾讯的对接）-->
    <script src="${path}/js/cos-js-sdk-v5.js" type="text/javascript" charset="utf-8"></script>


    <#--引入网络bootstrap,最好用上面的本地类库-->
    <#-- <!-- 新 Bootstrap4 核心 CSS 文件 &ndash;&gt;
     <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
     <!-- jQuery文件。务必在bootstrap.min.js 之前引入 &ndash;&gt;
     <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
     <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js &ndash;&gt;
     <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
     <!-- 最新的 Bootstrap4 核心 JavaScript 文件 &ndash;&gt;
     <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>-->

</head>
</head>
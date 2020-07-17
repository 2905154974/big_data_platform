<html>
<#include "../zujian/top.ftlh">
<body>
<div id="wrapper" class="toggled">
    <#--内容区域头部样式-->
    <h2 class="bg-info text-white p-3  text-center">关于指南</h2>
    <#--左边栏-->
    <#include "../zujian/left.ftlh">

    <#--主要内容content-->
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-8 offset-1 column">
                <h1>注意事项</h1>
                <p>1，手机号管理p>入手机号中，文件以xlsx格式。内容的列必须一一对应。</p>
                <p>2，手机号管理：导出到表格中，未搜索条件下导出所有数据，有条件搜索时导出检索后的手机号数据。</p>
                （导出数据条数和查询的数据条数一致）
                </p>
                <p>3，手机号管理：搜索时数据根据创建时间倒序排序，无搜索条件时默认正序。
                </p>
                <#--<img class="img-responsive img-lg" src="/BigDataPhone/img/shangpin.png"/>-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
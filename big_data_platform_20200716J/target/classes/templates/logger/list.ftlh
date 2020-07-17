<html>
<#include "../zujian/top.ftlh">

<body>
<div id="wrapper" class="toggled">
    <#--内容区域头部样式-->
    <h4 class="bg-info text-white p-1  text-center">日志管理</h4>
    <#--左边栏-->
    <#include "../zujian/left.ftlh">
    <#--主要内容content-->
    <div id="page-content-wrapper" class="container-fluid">
        <#--搜索框根据手机号搜索-->
        <#--<#if foodPage.getTotalElements()??>-->
            <button class="btn btn-info text-white my-4">
                <#--共${foodPage.getTotalElements()}条数据-->
                共${PhoneCountSum}条数据
            </button>
            <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-striped table-bordered table-sm">
                    <thead>
                    <tr class="table-info">
                        <th>id</th>
                        <th>操作者</th>
                        <th>操作内容</th>
                        <th>操作时间</th>
                    </tr>
                    </thead>
                    <tbody>

                    <#list foodPage.content as big_data_phone>
                    <#--<#list foodPage as big_data_phone>-->
                        <tr>
                            <td>${big_data_phone.id}<#--${big_data_phone.id?c}--></td>
                            <td>${big_data_phone.operationId!''}</td>
                            <td>${big_data_phone.remark!''}</td>
                            <td>${big_data_phone.createTime!''}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <#--分页-->
            <div class="col-md-12 column">
                <ul class="pagination">
                    <#if currentPage lte 1>
                        <li class="page-item disabled"><a class="page-link" href="#">上一页</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/BigDataPhone/OuterJoint/logger?page=${currentPage - 1}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                            上一页</a>
                        </li>
                    </#if>

                    <#if foodPage.getTotalPages() lte 20>
                        <#list 1..foodPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="page-item disabled"><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/BigDataPhone/OuterJoint/logger?page=${index}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                                ${index}</a>
                                </li>
                            </#if>
                        </#list>
                    <#else>
                        <#list 1..20 as index>
                            <#if currentPage == index>
                                <li class="page-item disabled"><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/BigDataPhone/OuterJoint/logger?page=${index}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                                ${index}</a>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                    <#--<#list 1..foodPage.getTotalPages() as index>-->
                        <#--<#if currentPage == index>-->
                            <#--<li class="page-item disabled"><a class="page-link" href="#">${index}</a></li>-->
                        <#--<#else>-->
                            <#--<li class="page-item"><a class="page-link" href="/BigDataPhone/big_data_phone/list?page=${index}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">-->
                            <#--${index}</a>-->
                            <#--</li>-->
                        <#--</#if>-->
                    <#--</#list>-->

                    <#if currentPage gt 20>
                        <li class="page-item disabled"><a class="page-link" href="#">${currentPage}</a></li>
                    </#if>
                    <#if currentPage gte foodPage.getTotalPages()>
                        <li class="page-item disabled"><a class="page-link" href="#">下一页</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 href="/BigDataPhone/big_data_phone/list?page=${currentPage + 1}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                            下一页</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>

</div>
</body>
</html>
<html>
<#include "../zujian/top.ftlh">

<body>
<div id="wrapper" class="toggled">
    <#--内容区域头部样式-->
    <h4 class="bg-info text-white p-1  text-center">手机号管理</h4>
    <#--左边栏-->
    <#include "../zujian/left.ftlh">
    <#--主要内容content-->
    <div id="page-content-wrapper" class="container-fluid">
        <#--添加菜品-->
        <button class="btn btn-warning text-white my-4" onclick="window.parent.location.href='/BigDataPhone/big_data_phone/index';">
            添加单个手机号
        </button>
        <#--批量导入-->
        <button class="btn btn-info text-white my-4" onclick="window.parent.location.href='/BigDataPhone/big_data_phone/excel';">
            批量导入手机号
        </button>
        <#--批量导出-->
        <button class="btn btn-info text-white my-4" onclick="window.parent.location.href='/BigDataPhone/big_data_phone/export<#if txtSearch??>?txtSearch=${txtSearch}</#if>';">
            导出手机号到excel
        </button>
        <#--搜索框根据手机号搜索-->
        <#--<#if foodPage.getTotalElements()??>-->
            <button class="btn btn-info text-white my-4">
                <#--共${foodPage.getTotalElements()}条数据-->
                共${PhoneCountSum}条数据
            </button>
        <#--</#if>-->
        <div class="input-group btn form-inline row my-3 float-right" style="width: 300px;height:;margin-right: 20%">
            <form role="form" method="get" action="/BigDataPhone/big_data_phone/list">
                <#--searchPhone-->
                <#--<#if txtSearch??>${txtSearch}</#if>-->
            <input type="text" value="<#if txtSearch??>${txtSearch}</#if>" name="txtSearch" class="form-control" style="width: 200px;">
            <span class="input-group-btn">
                 <button class="btn btn-primary" type="submit">搜索</button>
            </span>
            </form>
        </div>

            <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-striped table-bordered table-sm">
                    <thead>
                    <tr class="table-info">
                        <th>id</th>
                        <th>名字</th>
                        <th>手机号</th>
                        <th>性别</th>
                        <th>创建时间</th>
                        <#--<th>修改时间</th>-->
                        <th colspan="3">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <#list foodPage.content as big_data_phone>
                    <#--<#list foodPage as big_data_phone>-->
                        <tr>
                            <td>${big_data_phone.id}<#--${big_data_phone.id?c}--></td>
                            <td>${big_data_phone.name}</td>
                            <td>${big_data_phone.phone}</td>
                            <td>${big_data_phone.sex}</td>
                            <#--<td><img height="50" width="50" src="${food.foodIcon}" alt=""></td>-->
                            <td>${big_data_phone.createTime!''}</td>
                            <#--<td>${big_data_phone.updateTime!''}</td>-->
                            <td><a href="/BigDataPhone/big_data_phone/index?Id=${big_data_phone.id}">修改</a>
                            </td>
                            <#--<td>-->
                                <#--<#if (food.getFoodStatus() ==0)>-->
                                    <#--<a href="/diancan/food/off_sale?foodId=${food.foodId}">下架</a>-->
                                <#--<#else>-->
                                    <#--<a href="/diancan/food/on_sale?foodId=${food.foodId}">上架</a>-->
                                <#--</#if>-->
                            <#--</td>-->
                            <td><a href="/BigDataPhone/big_data_phone/remove?Id=${big_data_phone.id}">删除</a>
                            </td>
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
                        <li class="page-item"><a class="page-link" href="/BigDataPhone/big_data_phone/list?page=${currentPage - 1}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                            上一页</a>
                        </li>
                    </#if>

                    <#if foodPage.getTotalPages() lte 20>
                        <#list 1..foodPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="page-item disabled"><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/BigDataPhone/big_data_phone/list?page=${index}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
                                ${index}</a>
                                </li>
                            </#if>
                        </#list>
                    <#else>
                        <#list 1..20 as index>
                            <#if currentPage == index>
                                <li class="page-item disabled"><a class="page-link" href="#">${index}</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/BigDataPhone/big_data_phone/list?page=${index}&size=${size}&txtSearch=<#if txtSearch??>${txtSearch}</#if>">
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
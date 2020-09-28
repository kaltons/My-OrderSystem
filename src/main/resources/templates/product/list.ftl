<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th style="text-align: center;vertical-align:middle">商品id</th>
                            <th style="text-align: center;vertical-align:middle">名称</th>
                            <th style="text-align: center;vertical-align:middle">图片</th>
                            <th style="text-align: center;vertical-align:middle">单价</th>
                            <th style="text-align: center;vertical-align:middle">库存</th>
                            <th style="text-align: center;vertical-align:middle">描述</th>
                            <th style="text-align: center;vertical-align:middle">类目</th>
                            <th style="text-align: center;vertical-align:middle">创建时间</th>
                            <th style="text-align: center;vertical-align:middle">修改时间</th>
                            <th style="text-align: center;vertical-align:middle" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td align="center" style="vertical-align:middle;">${productInfo.productId}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.productName}</td>
                            <td align="center" style="vertical-align:middle;"><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td align="center" style="vertical-align:middle;">${productInfo.productPrice}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.productStock}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.productDescription}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.categoryType}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.createTime}</td>
                            <td align="center" style="vertical-align:middle;">${productInfo.updateTime}</td>
                            <td align="center" style="vertical-align:middle;">
                                <a href="/sell/seller/product/index?productId=${productInfo.productId}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-pencil"></i> 修改</a>
                            <td align="center" style="vertical-align:middle;">
                                <#if productInfo.getProductStatusEnum().message == "上架">
                                    <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i> 下架</a>
                                <#else>
                                    <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-arrow-up"></i> 上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..productInfoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
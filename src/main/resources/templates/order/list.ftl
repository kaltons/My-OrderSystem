<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏sidebar -->
    <#include "../common/nav.ftl">

    <#-- 主要内容 -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clear fix">
                <#-- 表格 -->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="success">
                            <th style="text-align: center;vertical-align:middle">订单编号</th>
                            <th style="text-align: center;vertical-align:middle">姓名</th>
                            <th style="text-align: center;vertical-align:middle">手机号</th>
                            <th style="text-align: center;vertical-align:middle">地址</th>
                            <th style="text-align: center;vertical-align:middle">金额</th>
                            <th style="text-align: center;vertical-align:middle">订单状态</th>
                            <th style="text-align: center;vertical-align:middle">支付状态</th>
                            <th style="text-align: center;vertical-align:middle">创建时间</th>
                            <th style="text-align: center;vertical-align:middle" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td align="center" style="vertical-align:middle;">${orderDTO.orderId}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.buyerName}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.buyerPhone}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.buyerAddress}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.orderAmount}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.getOrderStatusEnum().message}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.getPayStatusEnum().message}</td>
                                <td align="center" style="vertical-align:middle;">${orderDTO.createTime}</td>
                                <td align="center" style="vertical-align:middle;">
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-eye-open"></i> 详情</a>
                                </td>
                                <td align="center" style="vertical-align:middle;">
                                    <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i> 取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
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
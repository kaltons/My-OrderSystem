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
                            <th style="text-align: center;vertical-align:middle">类目编号</th>
                            <th style="text-align: center;vertical-align:middle">名称</th>
                            <th style="text-align: center;vertical-align:middle">类型</th>
                            <th style="text-align: center;vertical-align:middle">创建时间</th>
                            <th style="text-align: center;vertical-align:middle">修改时间</th>
                            <th style="text-align: center;vertical-align:middle">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categoryList as category>
                            <tr>
                                <td align="center" style="vertical-align:middle;">${category.categoryId}</td>
                                <td align="center" style="vertical-align:middle;">${category.categoryName}</td>
                                <td align="center" style="vertical-align:middle;">${category.categoryType}</td>
                                <td align="center" style="vertical-align:middle;">${category.createTime}</td>
                                <td align="center" style="vertical-align:middle;">${category.updateTime}</td>
                                <td align="center" style="vertical-align:middle;">
                                    <a href="/sell/seller/category/index?categoryId=${category.categoryId}" class="btn btn-info btn-xs">
                                        <i class="glyphicon glyphicon-pencil"></i> 修改</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
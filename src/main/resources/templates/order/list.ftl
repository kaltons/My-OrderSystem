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

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                新订单来了，Kalton已为您自动接单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://kalton.natapp1.cc/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        //弹窗提醒, 播放音乐
        $('#myModal').modal('show');
        var play = document.getElementById('notice');
        console.log(play)
        play.play()
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>
</body>
</html>
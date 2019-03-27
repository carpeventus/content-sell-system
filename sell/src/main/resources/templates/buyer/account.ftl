<html>
<head>
    <meta charset="utf-8">
    <title>已购买的内容</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <#if user??>
                <#if user.isSeller == 1>
                卖家你好，<span class="name">${user.username!}</span>！<a href="/logout">[退出]</a>
                <#elseif user.isSeller == 0>
                买家你好，<span class="name">${user.username!}</span>！<a href="/logout">[退出]</a>
                </#if>
            <#else>
                请<a href="/login">[登录]</a>
            </#if >
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if user??>
                <#if user.isSeller == 1>
                <li><a href="/public">发布</a></li>
                <#elseif user.isSeller == 0>
                <li><a href="/account">财务</a></li>
                <li><a href="/settleAccount">购物车</a></li>
                </#if>
            </#if>
        </ul>
    </div>
</div>
<#if (orderDetailList?size == 0)>
    <div class="g-doc">
        <div class="n-result">
            <h3>您还没有购买过内容~</h3>
            <p><a href="/">[前往购物]</a><a href="/">[返回首页]</a></p>
        </div>
    </div>
<#else>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup>
            <col class="img">
            <col>
            <col class="time">
            <col>
            <col class="num">
            <col>
            <col class="price">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>内容图片</th>
            <th>内容名称</th>
            <th>购买时间</th>
            <th>购买数量</th>
            <th>购买价格</th>
        </tr>
        </thead>
        <tbody>
        <#list orderDetailList as orderDetail>
        <tr>
            <td><a href="/show/${orderDetail.contentId}"><img src="${orderDetail.contentIcon}" alt=""></a></td>
            <td><h4><a href="/show/${orderDetail.contentId}">${orderDetail.contentTitle}</a></h4></td>
            <td><span class="v-time">${(orderDetail.createTime)?datetime}</span></td>
            <td><span class="v-num">${orderDetail.quantity}</span></td>
            <td><span class="v-unit">¥</span><span class="value">${orderDetail.contentPrice?c}</span></td>
        </tr>
        </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="total">总计：</div>
            </td>
            <td>
                <span class="v-unit">¥</span>
                <span class="value">${amount?c}</span>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
</#if>

<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>
</body>
</html>
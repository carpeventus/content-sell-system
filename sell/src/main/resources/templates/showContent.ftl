<html>
<head>
    <meta charset="utf-8">
    <title>内容详情</title>
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
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

<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img">
            <img src="${content.contentIcon!}" alt="">
        </div>
        <div class="cnt">
            <h2>${content.contentTitle!}</h2>
            <p class="summary">${content.contentSummary!}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${content.contentPrice?c}</span>
            </div>
            <div>
                <span class="v-unit">库存：${content.contentStock?c}</span>
            </div>
            <#if user??>
                <#if user.isSeller == 1>
                    <div class="oprt f-cb">
                        <a href="/edit/${content.contentId!}" class="u-btn u-btn-primary">编 辑</a>
                    </div>
                <#else>
                <#--如果要求购买过的内容不可重复购买，取消以下注释-->
                    <#--<#if orderDetail??>-->
                        <#--<div class="oprt f-cb">-->
                            <#--<span class="u-btn u-btn-primary z-dis">已购买</span>-->
                            <#--<span class="buyprice">当时购买价格：¥${orderDetail.contentPrice?c}</span>-->
                        <#--</div>-->
                    <#--<#else >-->
                    <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span>
                        <span class="totalNum" id="allNum">1</span>
                        <span id="addNum" class="moreNum"><a>+</a></span>
                    </div>
                        <div class="oprt f-cb">
                            <button class="u-btn u-btn-primary" id="add" data-user="${user.userId}" data-id="${content.contentId!}">
                                加入购物车
                            </button>
                        </div>
                    <#--</#if>-->
                </#if>
            </#if>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
    ${content.contentDescription!}
    </div>
</div>
<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>

</body>
</html>
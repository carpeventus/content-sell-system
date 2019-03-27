<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
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
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if type == 0>class="z-sel"</#if>><a href="/">所有内容</a></li>
                <#if user?? && user.isSeller ==0>
                    <li <#if type == 1>class="z-sel"</#if>><a href="/?type=1">未购买的内容</a></li>
                </#if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <#list contentInfoList as content>
                <li id="${content.contentId}">
                    <a href="/show/${content.contentId}" class="link">
                        <div class="img"><img src="${content.contentIcon!}" alt="${content.contentTitle!}"></div>
                        <h3>${content.contentTitle!}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${content.contentPrice?c}</span></div>
                        <#if user?? && user.isSeller == 0>
                            <#if type==0 && contentIdSet?seq_contains(content.contentId)>
                                <span class="had"><b>购买过</b></span>
                            </#if>
                        </#if>
                    </a>
                    <#if user?? && user.isSeller == 1>
                        <#if soldNumMap[content.contentId] == 0>
                            <span class="u-btn u-btn-normal u-btn-xs del" data-del="${content.contentId!}">删除</span>
                        <#else>
                            <span class="del"><b>售出${soldNumMap[content.contentId]}</b></span>
                        </#if>
                    </#if>
                </li>
            </#list>
        </ul>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>
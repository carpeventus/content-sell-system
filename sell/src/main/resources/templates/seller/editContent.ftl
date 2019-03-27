<html>
<head>
    <meta charset="utf-8">
    <title>内容编辑</title>
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
        <h2>内容编辑</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="/editSubmit/${content.contentId!}" onsubmit="return false;"
              autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="contentTitle" value="${content.contentTitle}" autofocus="" placeholder="2-80字符">
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="contentSummary" value="${content.contentSummary}" placeholder="2-140字符">
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked="checked"> 图片地址
                    <input name="pic" type="radio" value="file"> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt" name="contentIcon" value="${content.contentIcon}" placeholder="图片地址">
                </div>
                <div class="fmipt" id="fileUpload" style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp" accept="image/jpeg,image/png,image/gif">
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="contentDescription" rows="10" placeholder="2-1000个字符">${content.contentDescription!}</textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="contentPrice" value="${content.contentPrice?c}">元
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">库存：</label>
                <div class="fmipt">
                    <input class="u-ipt stock" name="contentStock" value="${content.contentStock?c}">件
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button id="btn-public" type="submit" class="u-btn u-btn-primary u-btn-lg">保存</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="${content.contentIcon}" alt="" id="imgpre"></span>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/public.js"></script>

</body>
</html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/jquery/jquery.min.js"></script>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            请<a href="/login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab"><i class="layui-icon layui-icon-username"></i></label>
        <div class="fmipt">
            <input class="u-ipt" id="username" name="username" autofocus="" placeholder="请输入用户名">
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab"><i class="layui-icon layui-icon-password"></i></label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="password" name="password" placeholder="请输入密码">
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block" id="login-btn">登 录</button>
        </div>
    </div>

    <div class="fmitem fmitem-nolab">
        <div class="fmipt">
            <span style="float: right"><i class="layui-icon layui-icon-right" style="color: #bc1e40"></i><a style="color: #bc1e40" href="/register">立即注册</a></span>
        </div>
    </div>

</form>
<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>

<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/pageLogin.js"></script>
</body>
</html>

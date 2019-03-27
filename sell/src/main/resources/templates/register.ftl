<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
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
        <label class="fmlab">用户名：</i></label>
        <div class="fmipt">
            <input class="u-ipt" id="username" name="username" autofocus="" placeholder="不超过12个字符">
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="password" name="password" placeholder="4~16位字符">
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">再次输入密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="repassword" name="repassword" placeholder="4~16位字符">
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">角色：</label>
        <div class="fmipt">
            <label>
                <input name="role" type="radio" value="buyer" checked="checked" style="vertical-align: middle;">
            </label> 买家&nbsp;
            <label>
                <input name="role" type="radio" value="seller" style="vertical-align: middle">
            </label> 卖家
        </div>
    </div>


    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block" id="register-btn">注 册</button>
        </div>
    </div>

</form>
<div class="n-foot">
    <p>版权所有：<a href="http://www.163.com/">网易</a>Java开发工程师大作业</p>
</div>

<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/pageRegister.js"></script>
</body>
</html>

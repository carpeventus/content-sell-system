$(document).ready(function () {
    $("#login-btn").click(function () {

        var username = $("#username").val();
        var password = $("#password").val();
        if ($.trim(username) === ""||$.trim(password) === "") {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('用户名或密码不能为空',{anim: 6, time: 2000});
            });
        } else {
            var value1 = username;
            var value2 = password;
            $.ajax({
                url:'/checkLogin',
                type:'post',
                data:{'username':value1, 'password':value2},
            success:function (data) {
                var code = data.code;
                var msg = data.msg;
                if (code == 200) {
                    location.href='/';
                } else {
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.msg(msg,{anim: 6, time: 2000});
                    });
                }
            }, error:function () {
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('登录失败，请重试',{time:1500});
                });
            }
        });
        }
    });

});
$(document).ready(function () {
    $("#register-btn").click(function () {

        var username = $("#username").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();

        var roleCode = $("input[name='role']:checked").val() == 'buyer' ? 0 : 1;
        var passwordReg = /^[a-zA-Z0-9~!@#$%^&*()_+-=;':",./<>?`]{4,16}$/;
        if (username.length < 1 || username.length > 12) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('用户名不符合规范', {anim: 6, time: 2000});
            });
        } else if (!passwordReg.test(password) || !passwordReg.test(repassword)) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('密码不符合规范', {anim: 6, time: 2000});
            });
        } else if (password != repassword) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('两次输入的密码不一致', {anim: 6, time: 2000});
            });
        } else {
            var value1 = username;
            var value2 = password;
            var value3 = roleCode;
            $.ajax({
                url: '/registerSubmit',
                type: 'post',
                data: {'username': value1, 'password': value2,'isSeller': value3},
                success: function (data) {
                    var code = data.code;
                    var msg = data.msg;
                    if (code == 200) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(msg, {time: 2000});
                        });
                        setTimeout('location.href="/login"', 3000);
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(msg, {anim: 6, time: 2000});
                        });
                    }
                }
            });
        }
    });

});
$(document).ready(function () {
    var loading = new Loading();
    var layer = new Layer();

    var isCheckAll = false;
	$('#selectAll').click(function () {
        if (isCheckAll) {
            $(":checkbox").each(function() {
                this.checked = false;
            });
            isCheckAll = false;
        } else {
            $(":checkbox").each(function() {
                this.checked = true;
            });
            isCheckAll = true;
        }
    });
    $('#newTable').click(function(e){
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        // var quantity;
        if(target.nodeName == "A" && target.parentElement.className == "moreNum"){
            var num = target.parentElement.parentElement.children[1].children[0].value;
            num ++;
            target.parentElement.parentElement.children[1].children[0].value = num;
        }else if(target.nodeName == "A" && target.parentElement.className == "lessNum"){
            var num = target.parentElement.parentElement.children[1].children[0].value;
            if (num > 1) {
                num --;
                target.parentElement.parentElement.children[1].children[0].value = num;
            }
        } else if(target.nodeName =="A" && target.parentElement.className =="save") {
            var ele = target;
            var id = ele && ele.dataset.id;
            var userid = ele && ele.dataset.userid;
            var quantity = target.parentElement.parentElement.parentElement.children[3].children[1].children[0].value;
            $.ajax({
                url:'/saveCartDetail',
                type:'post',
                data:{'userId':userid,'contentId':id,'quantity':quantity},
                success:function (data) {
                    var code = data.code;
                    var msg = data.msg;
                    if (code == 200) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(msg, {time:1500});
                        });
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(msg,{time: 1500});
                        });
                    }
                }
            });

        } else if (target.nodeName =="A" && target.parentElement.className =="delete") {
            var ele = target;
            var id = ele && ele.dataset.id;
            var userid = ele && ele.dataset.userid;
            layer.reset({
                content:'确认从购物车删除该内容吗？',
                onconfirm:function () {
                    $.ajax({
                        url:'/deleteCartDetail',
                        type:'post',
                        data:{'userId':userid,'contentId':id},
                        success:function (data) {
                            layer.hide();
                            var code = data.code;
                            var msg = data.msg;
                            if (code == 200) {
                                deleteNode(id);
                            }
                        }
                    });
                }.bind(this)
            }).show();
        }
    });

    $('#buy').click(function () {
    	var contents = [];
    	var userid = $('#user').val();
        $('input[name="cartDetail"]').each(function () {
            if (this.checked) {
            	var ele = this;
            	var id = ele && ele.dataset.id;
                var quantity = ele.parentElement.parentElement.children[3].children[1].children[0].value;
				var detail = {'contentId':id, 'quantity':quantity};
				contents.push(detail);
			}
        });

		if (contents.length === 0) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('您没有选择任何内容！', {time:1500});
                return false;
            });
		} else {
		    layer.reset({
                content:"确认购买吗？",
                onconfirm:function () {
                    layer.hide();
                    loading.show();
                    $.ajax({
                        url:'/buy',
                        type:'post',
                        // 不能直接传对象，需要将object转换成string，故使用JSON.stringify
                        data:{'userId':userid, 'items': JSON.stringify(contents)},
                        success:function (data) {
                            var code = data.code;
                            var msg = data.msg;
                            if (code == 200) {
                                loading.result(msg);
                                setTimeout('location.href="/account"',3000);
                            } else {
                                loading.result(msg);
                            }
                        }
                    });

                }.bind(this)
            }).show();
		}
    });

});

deleteNode = function (id) {
    var item = util.get(id);
    if (item && item.parentNode) {
        item.parentNode.removeChild(item);
    }
};

util.get('plusNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = util.get('allNum').textContent;
    if (num > 1) {
        num--;
        util.get('allNum').innerHTML = num;
    }
};

util.get('addNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = util.get('allNum').textContent;
    num++;
    util.get('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();


util.get('add').onclick = function (e) {
    var ele = e.target;
    var id = ele && ele.dataset.id;
    var userId = ele && ele.dataset.user;
    var num = util.get('allNum').innerHTML;
    var productDetail = {'userId':userId,'contentId': id, 'quantity': num};
    layer.reset({
        content: '确认加入购物车吗？',
        onconfirm: function () {
            layer.hide();
            loading.show();
            $.ajax({
                url:'/addToCart',
                type:'post',
                data:productDetail,
                success:function (data) {
                    var code = data.code;
                    var msg = data.msg;
                    loading.result(msg);
                }
            });
        }.bind(this)
    }).show();

};





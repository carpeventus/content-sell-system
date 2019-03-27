(function (w, d, u) {
    var form = util.get('form');
    if (!form) {
        return;
    }
    var title = form['contentTitle'];
    var summary = form['contentSummary'];
    var image = form['contentIcon'];
    var stock = form['contentStock'];
    var detail = form['contentDescription'];
    var price = form['contentPrice'];
    var uploadInput = form['file'];
    var isSubmiting = false;
    var imgpre = util.get('imgpre');
    var imageUrl;
    var imageMode = "urlUpload";


    var page = {
        init: function () {
            $('#uploadType').click(function (e) {
                e = window.event || e;
                o = e.srcElement || e.target;
                if (o.nodeName === "INPUT") {
                    var s, h;
                    o.value === 'url' ? (s = 'urlUpload', h = 'fileUpload') : (s = 'fileUpload', h = 'urlUpload');
                    imageMode = o.value === 'url' ? "urlUpload" : "fileUpload";
                    image.classList.remove("z-err");
                    uploadInput.classList.remove("z-err");
                    util.get(s).style.display = 'block';
                    util.get(h).style.display = 'none';
                }
            });

            util.get('upload').addEventListener('click', function () {

                uploadInput.addEventListener('change', function () {
                    console.log(uploadInput.files) // File listing!
                });
                for (var i = 0, fileCount = uploadInput.files.length; i < fileCount; i++) {
                    console.log(uploadInput.files[i]);
                }
                var maxAllowedSize = 1048576;
                var file = uploadInput.files[0];
                if (file.size > maxAllowedSize) {
                    alert("超过文件上传大小限制");
                } else {
                    var form = new FormData();
                    form.append('file', file, file.name);
                    // 上传图片指定表单的enctype
                    form.enctype ="multipart/form-data";
                    $.ajax({
                        url: '/upload',
                        type: 'post',
                        data: form,
                        dataType: 'json',
                        // 不将data处理为字符串以配合"application/x-www-form-urlencoded"
                        processData: false,
                        // 不让默认的"application/x-www-form-urlencoded"影响到上面的form-data设置
                        contentType: false,
                        success: function (data) {
                            var code = data.code;
                            var msg = data.msg;
                            imageUrl = data.data;
                            if (code == 200) {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.msg(msg, {time:1500});
                                });
                                image.value = imageUrl;
                                imgpre.src = imageUrl;
                            } else {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.msg(msg, {time:1500});
                                    return false;
                                });
                            }
                        }
                    });
                }
            });

            util.get('btn-public').addEventListener('click', function (e) {
                if (!isSubmiting && this.check()) {
                    price.value = Number(price.value);
                    isSubmiting = true;
                    form.submit();
                }
            }.bind(this), false);
            [title, summary, image, detail, price].forEach(function (item) {
                item.addEventListener('input', function (e) {
                    item.classList.remove('z-err');
                }.bind(this), false);
            }.bind(this));
            image.addEventListener('input', function (e) {
                var value = image.value.trim();
                if (value != '') {
                    imgpre.src = value;
                }
            }.bind(this), false);
        },
        check: function () {
            var result = true;
            [
                [title, function (value) {
                    return value.length < 2 || value.length > 80
                }],
                [summary, function (value) {
                    return value.length < 2 || value.length > 140
                }],
                [image, function (value) {
                    return imageMode == "urlUpload" && value == ''
                }],
                [detail, function (value) {
                    return value.length < 2 || value.length > 1000
                }],
                [price, function (value) {
                    return value == '' || !Number(value) || Number(value) <=0
                }],
                [stock, function (value) {
                    return !/^[0-9]+$/.test(value)
                }]
            ].forEach(function (item) {
                var value = item[0].value.trim();
                if (item[1](value)) {
                    item[0].classList.add('z-err');
                    result = false;
                }
                item[0].value = value;
            });
            console.log(imageUrl);
            console.log("GGG");
            if (imageMode == "fileUpload" && !imageUrl) {
                uploadInput.classList.add('z-err');
                result = false;
            }
            return result;
        }
    };
    page.init();
})(window, document);
function get() {
    var J;
    $.ajax({
        type: "POST", //请求方式
        url: "/JsonServlet",//请求路径
        cache: false,
        data: "",//传参
        dataType: 'json',//返回值类型
        success: function (json) {
            // alert(json);
            // alert(json[0].account + " " + json[0].password);//弹出返回过来的List对象
            J = json;
        }
    });


}

window.showJson();

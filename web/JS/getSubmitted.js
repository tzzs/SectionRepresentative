function getSubmitted(hno) {
    $.ajax({
        type: "POST", //请求方式
        url: "/submittedServlet",//请求路径
        cache: false,
        data: "myServlet",//传参
        dataType: 'json',//返回值类型
        success: function (json) {
            var attention = json[0].attention;
            var attentions = attention.split(";");
            for (a in attentions) {
                document.write(attentions[a].account, attentions[a].name, "<br>");
            }
        }
    })
}
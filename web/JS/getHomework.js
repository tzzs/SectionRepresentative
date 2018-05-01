function get() {
    console.log("123123");
    var J;
    $.ajax({
        type: "POST", //请求方式
        url: "/JsonServlet",//请求路径/myHomeworkServlet
        cache: false,
        data: "",//传参
        dataType: 'json',//返回值类型
        success: function (json) {
            // alert(json);
            // alert(json[0].account + " " + json[0].password);//弹出返回过来的List对象
            J = json;
        }
    });
    console.log(J);

    alert(J)
    var html = "";
    for (x in J) {
        html = "<div class=\"col-md-4\">\n" +
            "          <div class=\"card mb-4 box-shadow\">\n" +
            "            <img class=\"card-img-top\" data-src=\"holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail\"\n" +
            "                 alt=\"Card image cap\">\n" +
            "            <div class=\"card-body\">\n" +
            "              <p class=\"card-text\">" + J[x] + "</p>\n" +
            "              <div class=\"d-flex justify-content-between align-items-center\">\n" +
            "                <div class=\"btn-group\">\n" +
            "                  <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">View</button>\n" +
            "                  <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">push</button>\n" +
            "                </div>\n" +
            "                <small class=\"text-muted\">11 mins</small>\n" +
            "              </div>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "        </div>";
        var ele = document.createElement("scrpt");
        ele.innerHTML = html;
        document.body.appendChild(ele);
        // document.getElementById("contain").innerHTML = html;
    }
}

window.onload = get();

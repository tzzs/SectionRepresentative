var j;

function show() {
    $.ajax({
        type: "GET", //请求方式
        url: "/myHomeworkServlet",//请求路径
        cache: false,

        //传参
        dataType: 'json',//返回值类型
        success: function (json) {
            var s = "";
            for (var i in json) {
                var url = "/uploadServlet?hno=" + json[i].hno;
                s = s + "<tr><th>" + json[i].hno + "</th>" +
                    "<th>" + json[i].hcontent + "</th>" +
                    "<th>" + json[i].hfile + "</th>" +
                    "<th>" + DateFormat(json[i].beginTime) + "</th>" +
                    "<th>" + DateFormat(json[i].endTime) + "</th>" +
                    // "<th>" + json[i].beginTime + "</th>" +
                    // "<th>" + json[i].endTime + "</th>" +
                    "<th><input type=\"button\" onclick=\"update('" + json[i].hno + "')\" value=\"修改\"></th>" +//修改
                    "<th><input type=\"button\" onclick=\"download('" + json[i].hno + "')\" value=\"下载\"></th>" +//下载
                    "<th><input type=\"button\" onclick=\"delet('" + json[i].hno + "')\" value=\"删除\"></th>" +//删除
                    "</tr>";
            }
            document.getElementById("homework").insertAdjacentHTML("beforeend", s);// = s
        },
        error: function () {
            alert("error,未登录状态，确认后跳转登录...");//弹出返回过来的List对象
            self.location = "index.html";
        }
    });
}

function update(hno) {
    window.location.href = "update.html?hno=" + hno;
}

function download(hno) {
    window.location.href = "/downloadZipServlet?hno=" + hno;
}

function delet(hno) {
    window.location.href = "/deleteServlet?hno=" + hno;
}

/**
 * @return {string}
 */
function DateFormat(d) {
    if (d == null) {
        return;
    }
    //将时间戳转为int类型，构造Date类型
    var date = new Date(parseInt(d.time, 10));

    //月份得+1，且只有个位数时在前面+0
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;

    //日期为个位数时在前面+0
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    //时间
    var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();

    //getFullYear得到4位数的年份 ，返回一串字符串
    return date.getFullYear() + "-" + month + "-" + currentDate + " " + hour + ":" + minute;
}
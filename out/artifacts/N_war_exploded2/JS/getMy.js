var J;

function test() {
    $.ajax({
        type: "POST", //请求方式
        url: "/myServlet",//请求路径
        cache: false,
        data: "myServlet",//传参
        dataType: 'json',//返回值类型
        success: function (json) {
            J = json;
            document.getElementById("myAccount").innerText = J[0].account;
            document.getElementById("myName").innerText = J[0].name;
            document.getElementById("myEmail").innerText = J[0].email;
            document.getElementById("myAttention").innerText = J[0].attention;
            document.getElementById("myIdentity").innerText = J[0].identity;
        }
    });

}

window.onload = test;
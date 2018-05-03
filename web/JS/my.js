$(function () {  
	var tbody=window.document.getElementById("tbody-result");
        $.ajax({  
            type: "POST",  
            dataType: "json",  
            url: "../myHomeworkServlet", 
            cache: false,
            data:"",  
            success: function (json) { 
                    var str = "";  
                    var data = json;  
                    for (i in data) {  
                        str += "<tr>" +  
                        "<th>" + data[i].account + "</th>" +  
                        "<th>" + data[i].attention + "</th>" +  
                        "<th>" + data[i].email + "</th>" +  
                        "<th>" + data[i].identity + "</th>" +  
                        "<th>" + data[i].password + "</th>" +  
                        "<th>" + data[i].school + "</th>" +  
                        "</tr>";  
                    }  
                    tbody.innerHTML = str;  
            },  
            error: function () {  
                alert("查询失败")  
            }  
        }); 
});  
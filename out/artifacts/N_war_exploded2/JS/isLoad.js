$(function () {  
	var oList_ul = document.getElementById("list_li_ul");
	var oSpan = document.getElementById("span_con");
        $.ajax({  
            type: "POST",  
            dataType: "json",  
            url: "../myServlet", 
            cache: false,
            data:"",  
            success: function (json) { 
                oList_ul.hide();
                oWelcome.show();
                oSpan.innerText=json[0].account;
            },  
            error: function () { 
            	oList_ul.show();
                oWelcome.hide();
                $("#welcome").hide();
            }  
        }); 
});  
$(function () {
	 $('#input_search').click(function () {
        $.ajax({  
            type: "POST",  
            dataType: "json",  
            url: "../searchWorkServlet", 
            contentType : 'application/json;charset=utf-8',
            cache: false,
            data:{  
                "hno":"34" 
            },  
            success: function (json) { 
            	$("#form-con1").val(json[0].hno);
            },  
            error: function () { 
            	alert("查询失败!");
            }  
        }); 
	 });
	  console.log();
});  
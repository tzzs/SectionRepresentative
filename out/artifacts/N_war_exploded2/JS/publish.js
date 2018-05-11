$(function () {  
	 $('#submit').click(function () {
        $.ajax({  
            type: "POST",  
            dataType: "json",  
            url: "../attentionservlet", 
            cache: false,
            data:"",  
            success: function () { 
                   alert("提交成功!")
            },  
            error: function () {  
            	alert(json)
            }  
        }); 
	 });
});  
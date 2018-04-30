function get() {
    function showJson() {
        var test;
        if (window.XMLHttpRequest) {
            test = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            test = new window.ActiveXObject();
        } else {
            alert("请升级至最新版本的浏览器");
        }
        if (test != null) {
            test.open("GET", "json", true);
            test.send(null);
            test.onreadystatechange = function () {
                if (test.readyState == 4 && test.status == 200) {
                    var obj = JSON.parse(test.responseText);
                    for (var name in obj) {
                        alert(obj[name].key);
                    }
                }
            };

        }
    }

    var xhr;
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            var json = JSON.parse(xhr.response);
            if (json.success) {
                document.getElementById("is")
            }
        }
    }
}
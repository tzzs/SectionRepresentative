function leftBar() {
    var h = "<div class=\"lift-nav\">\n" +
        "        <ul class=\"lift\" id=\"list_ul\">\n" +
        "          <li class=\"on\">\n" +
        "            <a id=\"a1\" href=\"publish.html\">\n" +
        "              <img src=\"../img/publish1.png\">\n" +
        "              <span>发布公告</span>\n" +
        "            </a>\n" +
        "          </li>\n" +
        "          <li>\n" +
        "            <a id=\"a2\" href=\"publish1.html\">\n" +
        "              <img src=\"../img/amend.png\">\n" +
        "              <span>修改公告</span>\n" +
        "            </a>\n" +
        "          </li>\n" +
        "          <li>\n" +
        "            <a id=\"a3\" href=\"publish2.html\">\n" +
        "              <img src=\"../img/query.png\">\n" +
        "              <span>查询公告</span>\n" +
        "            </a>\n" +
        "          </li>\n" +
        "          <li>\n" +
        "            <a id=\"a4\" href=\"publish3.html\">\n" +
        "              <img src=\"../img/delete.png\">\n" +
        "              <span>删除公告</span>\n" +
        "            </a>\n" +
        "          </li>\n" +
        "          <li>\n" +
        "            <a id=\"a5\" href=\"publish4.html\">\n" +
        "              <img src=\"../img/delete.png\">\n" +
        "              <span>内容下载</span>\n" +
        "            </a>\n" +
        "          </li>\n" +
        "        </ul>\n" +
        "      </div>";
    var ele = document.getElementById("leftbar");
    ele.innerHTML(h);
    ele.innerText("123")
}

window.onload = leftBar;
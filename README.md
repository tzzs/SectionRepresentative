# SectionRepresentative
课代表作业系统


![continuousphp](https://img.shields.io/continuousphp/git-hub/doctrine/dbal/master.svg)
![GitHub](https://img.shields.io/github/license/mashape/apistatus.svg)


## 实现功能



- 普通用户

    > 登录

    > 注册

    > 作业搜索

    > 文件上传

    > 信息修改

    > 添加关注管理员

- 管理员
    > 作业发布
    
    > 作业修改
    
    > 作业删除

    >发布时允许添加附件

    > 作业文件压缩打包下载

## 项目文件关联

![FA9naT.jpg](https://s1.ax1x.com/2018/11/26/FA9naT.jpg)
 
## 文件树
```
│  README.md
│  tree.txt
│      
├─src
│  ├─com
│  │  └─zt
│  │      └─sr
│  │          ├─controller
│  │          │      loginCheckServlet.java
│  │          │      logoutServlet.java
│  │          │      MyAttention.java
│  │          │      myHomeworkServlet.java
│  │          │      myPublishServlet.java
│  │          │      myServlet.java
│  │          │      publishServlet.java
│  │          │      registerServlet.java
│  │          │      
│  │          ├─dao
│  │          │      fileDao.java
│  │          │      homeworkDao.java
│  │          │      jdbc.java
│  │          │      userDao.java
│  │          │      
│  │          ├─pojo
│  │          │      Homework.java
│  │          │      myFile.java
│  │          │      User.java
│  │          │      
│  │          ├─service
│  │          │      addAttentionServlet.java
│  │          │      delelteAttentionServlet.java
│  │          │      deleteHomeworkServlet.java
│  │          │      downloadZipServlet.java
│  │          │      hfileDownloadServlet.java
│  │          │      oneFileDownServlet.java
│  │          │      searchServlet.java
│  │          │      updateHomeworkServlet.java
│  │          │      updateUserServlet.java
│  │          │      uploadServlet.java
│  │          │      
│  │          └─utils
│  │                  getHno.java
│  │                  
│  ├─password
│  │      secure.java
│  │      
│  └─test
│          creatDatabase.java
│          database.java
│          JsonServlet.java
│          loginFilter.java
│          submittedServlet.java
│          test.java
│          
└─web
    │  index.jsp
    │  
    ├─a
    │      signin.css
    │      
    ├─CSS
    │      add.css
    │      album.css
    │      all.css
    │      bootstrap.min.css
    │      footer.css
    │      holder.min.js
    │      index.css
    │      jquery-slim.min.js
    │      load.css
    │      load_title.css
    │      main.css
    │      main_ipad.css
    │      manage.css
    │      manage_ipad.css
    │      manage_min.css
    │      my.css
    │      popper.min.js
    │      publish.css
    │      publish_ipad.css
    │      publish_min.css
    │      register.css
    │      serve.css
    │      signin.css
    │      title.css
    │      title_ipad.css
    │      title_min.css
    │      
    ├─html
    │  │  add.html
    │  │  homeworkList.html
    │  │  index.html
    │  │  main.html
    │  │  manage.html
    │  │  my.html
    │  │  myPublish.html
    │  │  publish.html
    │  │  publish3.html
    │  │  register.html
    │  │  searchno.html
    │  │  serve.html
    │  │  serve2.html
    │  │  test.html
    │  │  title.html
    │  │  update.html
    │  │  
    │  └─htmlTest
    │          content.html
    │          head.html
    │          leftbar.html
    │          searchno.html
    │          sub.html
    │          
    ├─img
    │      amend.png
    │      amend1.png
    │      banner01.jpg
    │      banner02.jpg
    │      delete.png
    │      delete1.png
    │      error.png
    │      list.png
    │      list1.png
    │      load.png
    │      LOGO.png
    │      publish.png
    │      publish1.png
    │      query.png
    │      query1.png
    │      register.png
    │      search.png
    │      
    ├─JS
    │      bootstrap.min.js
    │      getHomework.js
    │      getMy.js
    │      getSubmitted.js
    │      holder.min.js
    │      isLoad.js
    │      jquery-1.4.1.min.js
    │      jquery-3.2.1.js
    │      jquery-slim.min.js
    │      jquery.min.js
    │      leftBar.js
    │      LiftEffect.js
    │      my.js
    │      myPublish.js
    │      popper.min.js
    │      publish.js
    │      search.js
    │      searchWork.js
    │      
    ├─JSP
    │      attention.jsp
    │      downDialog.jsp
    │      listShow.jsp
    │      message.jsp
    │      showJSON.jsp
    │      testJson.jsp
    │      testUpload.jsp
    │      
    ├─lib
    │      commons-beanutils-1.8.0.jar
    │      commons-collections-3.2.1.jar
    │      commons-fileupload-1.3.3.jar
    │      commons-io-2.6.jar
    │      commons-lang-2.5.jar
    │      commons-logging-1.1.1.jar
    │      ezmorph-1.0.6.jar
    │      json-lib-2.4-jdk15.jar
    │      mybatis-3.0.5-sources.jar
    │      mysql-connector-java-5.1.45-bin.jar
    │      
    ├─META-INF
    │      MANIFEST.MF
    │      
    ├─sql
    │      sectionRepresentative.sql
    │      
    └─WEB-INF
        │  web.xml
        │  
        ├─css
        │      bootstrap.min.css
        │      signin.css
        │      
        ├─html
        │      login.html
        │      upload.html
        │      
        ├─JSP
        │      upload.jsp
        │      
        ├─lib
        │      mybatis-3.0.5-sources.jar
        │      mysql-connector-java-5.1.45-bin.jar
        │      
        └─sql
                initDatabase
                

```


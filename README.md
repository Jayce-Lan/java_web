# Java web应用

> 使用Java web技术完成增删改查功能



## 项目目录

- myweb
  - src
    - jdbc.properties(数据库配置文件)
    - com.learn
      - util(工具包)
        - **JDBCTools类**：封装了JDBC的连接池创建以及释放的方法
      - filter
        - **CharacterFilter类**：解决在可视图层传入数据乱码的问题
      - repository(存放负责操作数据库语句的类)
        - **StudentRepository类**：处理数据库语句，与数据库连接
      - entity(存放用于映射数据库表的类)
        - **Student类**：存储数据库信息的类
      - servlet(完成视图的渲染)
        - **StudentRepository类**：调用repository中的类的方法处理数据库，完成增删改查并渲染到视图
  - web
    - **index.jsp**：用于学生表的查询视图
    - **add.jsp**：用于学生表的添加视图
    - WEB-INF
      - lib(存放项目所需的jar包依赖)
        - jstl.jar
        - mysql.jar
        - standard.jar
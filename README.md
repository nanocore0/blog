# blog
这是一个博客系统(我真的不会写简介(ಥ_ಥ))

**blog-static**是静态页面  
**blog-prototype**是前端  
**blog**是后台  

静态页面是用bootstrap做的纯html页面，用到了以下js插件  
font-awesome  
bootstrap-tagsinput(原来是jquery-tagsinput，但和vue不兼容)  
cropper  
thinker-md  

后台主要是使用spring boot完成，用到了mysql数据库，mybatis持久层框架(含分页插件)，spring security认证与授权(含spring security oauth实现授权服务)，elasticsearch全文检索  
后台项目结构:  
blog  
|-- blog-core 实体类，mapper，公共类  
|-- blog-auth 权限控制，登录
|-- blog-search 搜索  
|-- blog-manage service，controller，文件上传  
前端~~还没做完~~(~~就快做完了~~(算了)) 

## 项目部署
部署后台项仅目有2个地方需要配置和修改  
一个是数据库，首先创建一个名叫blog数据库，修改blog-core里面的application-core.properties连接数据库的地址，并且第一次运行时打开里面自动执行创建表的配置，第二次运行时再注释掉  
第二是文件上传保存目录，修改在blog-manage里面的application.properties配置的文件(图片)上传保存的目录，如果是用idea运行项目也可以不配置，文件会默认保存到classpath:static/images/  
运行blog-manage里面的Application启动类即可启动

## 参考


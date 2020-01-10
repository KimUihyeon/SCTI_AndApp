# SCTI_AndApp

> Android Barcode App

바코드 단말기에 들어갈 Android Application. 


----


### Git 폴더구조

```
git
ㄴDoc                   (문서)
ㄴAndApp                (Application)
ㄴPrototype_AndApp      (ProtoType Application)
ㄴPrototype_NodeServer  (WebApp Javascript Connection 확인용 NodeServer)
```



---

### 프로젝트 설명

////// 사에서 사용 할 기기 입고 출고 수리에 관련한 웹앱 프로젝트.

`어플리케이션`

* Language  -> Java 8
* DEV SDK Version -> 넥서스x6  Android 8.0  API 24+
* Application 호환 버전 -> Android 4.0 ~  8.0  (API 14~28)
* Relese Mobile OS Version -> Android 8.0  API 28 


`웹 Test Server`

Native App과 Javascript 통신이 잘 되는지 , 확인하기 위한 Test Server

* BackEnd ->  NodeServer
* FronEnd -> Vue Js 2.1
* TemplateEngine -> nunjucks
* WebServer ->  node v8 engine
* 형상관리툴 -> git

`웹 Relese`

실제 운영서버 

* BackEnd ->  dotnet MVC ASP
* FronEnd -> Vue Js 2.1
* TemplateEngine -> nunjucks
* WebServer ->  IIS Expreess
* Server ->  Window Server 2012
* Database -> MSSQL SERVER
* 형상관리툴 -> TFS
 
----
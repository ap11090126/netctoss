<%@page pageEncoding="utf-8"%>
<img src="images/logo.png" alt="logo" class="left"/>
<!-- EL默认的取值范围是：
page->request->session->application
EL也可以从cookie中取值，其语法为：cookie.key.value -->
<%-- 
<span>${cookie.adminCode.value }</span>
 --%>
 <span>${adminCode }</span>
<a href="#">[退出]</a> 
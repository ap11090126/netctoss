<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--Logo区域开始-->
<div id="header">
	<img src="../images/logo.png" alt="logo" class="left"/>
	<span>用户：${session.admin.name}</span><a href="../out_login">[退出]</a>            
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
  <ul id="menu">
		<li><a href="../index" class="index_off"></a></li>
		<s:if test="'role_list' in #session.urls">
        <li><a href="../role/role_list" class="role_off"></a></li>
        </s:if>
        <s:if test="'admin_list' in #session.urls">
        <li><a href="../admin/admin_list" class="admin_off"></a></li>
        </s:if>
        <s:if test="'fee_list' in #session.urls">
        <li><a href="../fee/fee_list" class="fee_off"></a></li>
        </s:if>
        <s:if test="'account_list' in #session.urls">
        <li><a href="../account/account_list" class="account_off"></a></li>
        </s:if>
        <s:if test="'service_list' in #session.urls">
        <li><a href="../service/service_list" class="service_off"></a></li>
        </s:if>
        <s:if test="'bill_list' in #session.urls">
        <li><a href="../bill/bill_list" class="bill_off"></a></li>
        </s:if>
        <s:if test="'report_list' in #session.urls">
        <li><a href="../report/report_list" class="report_off"></a></li>
        </s:if>
		<li><a href="../user/user_info" class="information_off"></a></li>
		<li><a href="../user/user_modi_pwd" class="password_off"></a></li>
  </ul>
</div>
<!--导航区域结束-->
<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        
		<script type="text/javascript" src="scripts/login.js"></script>
    </head>
    <body class="index">
        <div class="login_box">
        <form action="login.do" method="post">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input id="adminCode" name="adminCode" value="${parm.adminCode }" type="text" class="width150" onblur="onblur1();"/></td>
                    <td class="login_error_info"><span id="adminCode_span"  class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input id="password" name="password" value="${parm.password }" type="password" class="width150" onblur="onblur2();"/></td>
                    <td><span id="password_span" class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="code" type="text" class="width70" /></td>
                    <td id="img_yz"><img src="createImg.do" onclick="this.setAttribute('src','createImg.do?'+Math.random());" alt="验证码" title="点击更换" /></td>  
                    <td><span class="required">${error1 }</span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                    <input type="submit" value="登录" class="btn_save"  />
                     
                    </td>    
                    <td><span class="required">${error }</span></td>                
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<%=path %>/js/jquery-3.2.1.min.js"></script>

<title>用户注册</title>
</head>
<body>
<form id="myForm">
    <table width="300px">
        <tr>
            <td>用户名</td>
            <td><input type="text" id="username" name="userName"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" id="password" name="password"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" id="email" name="email"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="btn_submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    $(function() {
    	$('#btn_submit').click(function() {
    		$.ajax({
    			type : "post",
                url : "user/register",
                data : $('#myForm').serialize(),
                dataType : 'JSON',
                //contentType : "application/json",
                success : function(result) {
                    if (result.result > 0) {
                        alert(result.message);
                    } else {
                    	alert(result.message);
                    }
                }
    		});
    	});
    });
    
    
</script>
</html>
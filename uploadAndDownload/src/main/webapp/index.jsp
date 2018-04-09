<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script src="<%=path%>/js/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
</body>
</html>

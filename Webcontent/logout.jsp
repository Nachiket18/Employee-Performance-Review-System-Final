<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		HttpSession hs = request.getSession(false);
		//hs.invalidate();
		/* RequestDispatcher rd = request.getRequestDispatcher("sign_in.jsp");
		rd.forward(request, response); */
		out.print(hs.getAttribute((String)hs.getAttribute("manager_name")));
	%>

</body>
</html>
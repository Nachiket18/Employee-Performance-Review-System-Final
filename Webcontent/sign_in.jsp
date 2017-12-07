<html>

<head>
	<meta charset="utf-8">
	<title>Sign In</title>
	<link rel="stylesheet" href="./css/sign_in.css">
</head>

<body>
	<div class="container">
	<form id="signin" method="post" action="signinser">
		<div class="top">
		<h1 id="title" class="hidden">Managers Login</h1>
		</div>
		
		<div class="login-box animated fadeInUp">
			<div class="box-header">
			<h2>Log In</h2>
			</div>
		
			<label for="username"><font face="Comic Sans MS">Username</font></label>
			<br/>
			<input type="text" id="username" required name="manager_user_name">
			<br/>
		
			<label for="password"><font face="Comic Sans MS">Password</font></label>
			<br/>
			<input type="password" id="password" required name="manager_password">
			<br/>
			
			<input type="submit" name="submit" value="Submit" id="submitbutton">
			
			<p class="small">
				<%
				if(request.getAttribute("flag") == null)
	  			{
	  				request.setAttribute("flag", 0);
	  			}
	  			if((int)request.getAttribute("flag") == 1)
	  			{	
	  				out.println("<font color = 'red'>Wrong credentials.</font>");
	  				out.println("<font color = 'red'>  Check your username and password.</font>");
	  				request.setAttribute("flag", 0);
	  			}
				%>
			</p>

			<p class="small">
			<a href="sign_up.jsp">New Manager? Sign Up!</a>
			</p>
		</div>
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
</body>
</html>
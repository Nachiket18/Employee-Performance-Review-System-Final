
<html>
<head>
	<meta charset="utf-8">
	<title>Sign Up</title>
	<link rel="stylesheet" href="../css/sign_up.css">
</head>

<body>
<div class="container">
 
    <form id="signup" method="post" action="signupser">
	<div class="header">
    	<h3>Sign Up</h3>            
	</div>
        
        
	<div class="inputs">
        
            <input type="text" placeholder="Manager Name" autofocus required name="manager_name"/>
        
            <input type="text" placeholder="Manager Username (firstname+surname@npav.com)" required name="manager_user_name"/>
            
            <input type="password" placeholder="Password" required name="manager_password"/>
            
            <input type="text" placeholder="Manager Type" required name="manager_type"/>
            
            <br>
            <div id="buttonzone">
			<input type="submit" name="submit" value="Submit" id="submitbutton">
			</div>
	</div>
    </form>
    </div>
</body>
</html>
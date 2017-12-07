<%@page import="java.sql.ResultSet"%>
<%@page import="connection_manager.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.DaoInterfaceImplementation"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>CloudRetrievalSDM</title>
    <link rel="stylesheet" href="./css/im.css">  

</head>

<%

 			HttpSession hs = request.getSession(false);
			String manager_name = (String)hs.getAttribute("manager_name");
  	   		int employee_code = 0;
        	String employee_name = null;
        	String employee_module = null;
        	String employee_band = null;
        	ResultSet rs;
        	Connection conn = ConnectionManager.getConn();
        	try 
    		{
        		PreparedStatement ps = conn.prepareStatement("select * from employee where employee_code = ?");
        		ps.setInt(1, Integer.parseInt(request.getParameter("employee_code")));
        		rs = ps.executeQuery();
        		while(rs.next())
        		{
        			employee_code = rs.getInt(1);
        			employee_name = rs.getString(2);
        			employee_module = rs.getString(3);
        			employee_band = rs.getString(4);
        		}
        		request.setAttribute("employee_code", employee_code);
        		        		
    		}catch(Exception e)
        	{
    			
        	}
%>
  
  

<body>
<div class="container">
  <div class="row header">
  <font color="Magenta" face="Times New Roman" size="20pt"><div align="left"><u>Cloud Data Retrieval<u></div></font>&nbsp;
  <table border="1" height="20%" width="35%" cellpadding="10pt" color="black">
  <tr>
     <td><b><font face="Times New Roman" size="4pt">Manager Name</font></b></td>
  </tr>
  <tr>
     <td><%=manager_name %></td>
</tr>
</table>

 </div>
  <div class="row body">
  
   <form method = "post" action = "service_delivery_manager_cloud_retrieval.jsp">        
      	<p class="left">
            	<center><label for="employee_name"><b>Employee Code:</b></label></center>
            	<center><input type="text" name="employee_code" value = <%=employee_code %> id="employee" /></center>
        </p>
          
          <center><input class="btn btn-submit" type = "submit" value = "Retrieve Employee Information"/></center>
          
     </form>         
  
  <form method = "post" action = "sdmcrser">
 		 <input type = "hidden" name = "employee_code" value = <%=employee_code %>>
  
  		  <table align = "center">
  		  <tr>
  		  <td>
  		  	<p class="left">
            		<label for="module">Employee Name:</label>
            		<input type="text" name="employee_code" value = <%=employee_name %> readonly="readonly" />
         	</p>
         	</td>
         	<td>
         	<p class="left">
            		<label for="module">Employee Module:</label>
            		<input type="text" name="module" value = <%=employee_module %> readonly="readonly" />
         	</p>
         	</td>
         	<td>
         	<p class="left">
            		<label for="band">Employee Band:</label>
            		<input type="text" name="band" value = <%=employee_band %> readonly="readonly" />
         	</p>
         	</td>
         	</tr>
         </table>
         <br>

          
  	<ul>
        <li>
          <input class="btn btn-submit" type="submit" value="Retrieve Cloud Data" />
          &nbsp;
          &nbsp;          
        </li>        
	</ul>
  </form>  
  </div>
</div>  
</body>
</html>

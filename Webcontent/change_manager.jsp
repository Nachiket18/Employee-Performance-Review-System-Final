<%@page import="dao.DaoInterface"%>
<%@page import="beans.ChangeManagementBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="connection_manager.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.DaoInterfaceImplementation"%>
<html >
<head>
    <meta charset="UTF-8">
    <title>CM</title>
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
  <font color="Magenta" face="Times New Roman" size="20pt"><div align="left"><u>Change Manager<u></div></font>&nbsp;
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
 
  <form method = "post" action = "change_manager.jsp">        
      	<p class="left">
            	<center><label for="employee_name"><b>Employee Code:</b></label></center>
            	<center><input type="text" name="employee_code" value = <%=employee_code %> id="employee" /></center>
        </p>
          
          <center><input class="btn btn-submit" type = "submit" value = "Retrieve Employee Information"/></center>
          
     </form>         
  
  <form method = "post" action = "cmser">
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
         <br>
         <hr>
         <br>
  
  		<%
  			ChangeManagementBean cmb = (ChangeManagementBean)request.getAttribute("change_object");
  			DaoInterface dao = new DaoInterfaceImplementation();
  			if(request.getAttribute("flag") == null)
  			{
  				request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 1)
  			{	
  		  		out.print("<div align='center'><h1><font color='Blue' size = '6' face='Times New Roman'>");
  		  		out.print(dao.retrieveEmployeeName(cmb.getEmployee_code()));
  		  		out.print("  has been rated successsfully</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 2)
  			{	
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  		  		out.print("Employee not found</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 3)
  			{	
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  		  		out.print(dao.retrieveEmployeeName(cmb.getEmployee_code()));
  		  		out.print("  has been rated previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  		 %>	
    	  <h3><font color="Blue" face="Times New Roman">PFC Submission In Time:</font></h3>
          <select name="pfc_submission_in_time" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
          
          	
          <h3><font color="Blue" face="Times New Roman">Schedule Variance:</font></h3>
          <select name="schedule_variance" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "2">2</option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
           
          <h3><font color="Blue" face="Times New Roman">OnTime Delivery Of Documentation:</font></h3>
          <select name="ontime_delivery_of_documentation" style="width: 150px;">
          <option> </option>         
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
         
          <h3><font color="Blue" face="Times New Roman">UAT Defects:</font></h3>
          <select name="uat_defects" style="width: 150px;">
          <option> </option>          
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
		  <h3><font color="Blue" face="Times New Roman">Effort Variance:</font></h3>
          <select name="effort_variance" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
         
	<br>
          <input class="btn btn-submit" type="submit" value="Submit" />&nbsp;
		<a href = "logoutser">
	  <input class="btn btn-submit" type="button" value="Logout" />
        
	</a>
	
  </form>  
  </div>
</div>  
</body>
</html>
 
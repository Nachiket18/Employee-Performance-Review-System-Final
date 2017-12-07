<%@page import="dao.DaoInterface"%>
<%@page import="beans.DomainManagementBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="connection_manager.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.DaoInterfaceImplementation"%>
<html >
<head>
    <meta charset="UTF-8">
    <title>DM</title>
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
  <font color="Magenta" face="Times New Roman" size="20pt"><div align="left"><u>Domain Manager<u></div></font>&nbsp;
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
  
   <form method = "post" action = "domain_manager.jsp">        
      	<p class="left">
            	<center><label for="employee_name"><b>Employee Code:</b></label></center>
            	<center><input type="text" name="employee_code" value = <%=employee_code %> id="employee" /></center>
        </p>
          
          <center><input class="btn btn-submit" type = "submit" value = "Retrieve Employee Information"/></center>
          
     </form>         
  
  <form method = "post" action = "dmser">
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
         <hr>
         <br>
      
      	<%
  			DomainManagementBean dmb = (DomainManagementBean)request.getAttribute("domain_object");
  			DaoInterface dao = new DaoInterfaceImplementation();
  			if(request.getAttribute("flag") == null)
  			{
  				request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 1)
  			{	
  		  		out.print("<div align='center'><h1><font color='Blue' size = '6' face='Times New Roman'>");
  		  		out.print(dao.retrieveEmployeeName(dmb.getEmployee_code()));
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
  		  		out.print(dao.retrieveEmployeeName(dmb.getEmployee_code()));
  		  		out.print("  has been rated previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  		 %>	
        
         <h3><font color="Blue" face="Times New Roman">Adherence To Shift Timing:</font></h3>
          <select name="adherence_to_shift_timing" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
         
          
          <h3><font color="Blue" face="Times New Roman">Leave Planning:</font></h3>
          <select name="leave_planning" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
  
          <h3><font color="Blue" face="Times New Roman">Following Meeting Etiquettes:</font></h3>
          <select name="following_meeting_etiquettes" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          

           <h3><font color="Blue" face="Times New Roman">Proactive:</font></h3>
          <select name="proactive" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "4">4</option>
          </select>
          
          
          <h3><font color="Blue" face="Times New Roman">Responsible To Deliverables:</font></h3>
          <select name="responsible_to_deliverables" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
          
          <h2><font face="Times New Roman"><b>Self Development:</b></font></h2>
          
          <h3><font color="Blue" face="Times New Roman">Training Hours:</font></h3>
          <select name="training_hours" style="width: 150px;">
          <option> </option>
          <option value = "2">2</option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
          <h3><font color="Blue" face="Times New Roman">Skill Improvement:</font></h3>
          <select name="skill_improvement" style="width: 150px;">
          <option> </option>
          <option value = "2">2</option>
          <option value = "4">4</option>
          </select>
          
          <h3><font color="Blue" face="Times New Roman">ITIL Certification:</font></h3>
          <select name="itil_certification" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
          <h3><font color="Blue" face="Times New Roman">Idle Time Management:</font></h3>
          <select name="idle_time_management" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
          
          <h2><font face="Times New Roman"><b>Team Development:</b></font></h2>
          
 
          <h3><font color="Blue" face="Times New Roman">Efforts In Development Of Team Members:</font></h3>
          <select name="efforts_in_development_of_team_members" style="width: 150px;">
          <option> </option>
          <option value = "2">2</option>
          <option value = "4">4</option>
          </select>
          
          <h3><font color="Blue" face="Times New Roman">Participation In Organizational Initiatives:</font></h3>
          <select name="participation_in_organizational_initiatives" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "2">2</option>
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
 
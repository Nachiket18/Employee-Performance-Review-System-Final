<%@page import="dao.DaoInterface"%>
<%@page import="beans.ServiceDeliveryManagementBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="connection_manager.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.DaoInterfaceImplementation"%>
<html >
<head>
    <meta charset="UTF-8">
    <title>SDM</title>
    <link rel="stylesheet" href="./css/sdm.css">    
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
  <font color="Magenta" face="Times New Roman" size="20pt"><div align="left"><u>Service Delivery Manager<u></div></font>&nbsp;
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
  
 <form method = "post" action = "service_delivery_manager.jsp">        
      	<p class="left">
            	<center><label for="employee_name"><b>Employee Code:</b></label></center>
            	<center><input type="text" name="employee_code" value = <%=employee_code %> id="employee" /></center>
        </p>
          
          <center><input class="btn btn-submit" type = "submit" value = "Retrieve Employee Information"/></center>
          
     </form>         
  
  <form method = "post" action = "sdmser">
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
  			ServiceDeliveryManagementBean sdmb = (ServiceDeliveryManagementBean)request.getAttribute("service_object");
  			DaoInterface dao = new DaoInterfaceImplementation();
  			if(request.getAttribute("flag") == null)
  			{
  				request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 1)
  			{	
  		  		out.print("<div align='center'><h1><font color='Blue' size = '6'  face='Times New Roman'>");
  		  		out.print(dao.retrieveEmployeeName(sdmb.getEmployee_code()));
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
  		  		out.print(dao.retrieveEmployeeName(sdmb.getEmployee_code()));
  		  		out.print("  has been rated previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 4)
  			{
  				out.print("<div align='center'><h1><font color='Blue' size = '6' face='Times New Roman'>");
  		  		out.print("Employee data backed up</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 5)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  		  		out.print("Cloud connection problem. Try again later</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 6)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  		  		out.print("Employee data has been backed up previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 7)
  			{
  				out.print("<div align='center'><h1><font color='Blue' size = '6' face='Times New Roman'>");
  		  		out.print("Employee data has been retrieved</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 8)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  		  		out.print("Employee data has been retrieved previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 9)
  			{
  				out.print("<div align='center'><h1><font color='blue' size = '6' face='Times New Roman'>");
  				out.print("Final Ratings for  ");
  		  		out.print(dao.retrieveEmployeeName(sdmb.getEmployee_code()));
  		  		out.print("  have been generated</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 10)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  				out.print("Final Ratings for  ");
  		  		out.print(dao.retrieveEmployeeName(sdmb.getEmployee_code()));
  		  		out.print("  have been generated previously</font></h1></div>");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 11)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  				out.print("Please rate all employees before generating bell curve </font></h1></div> ");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 12)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  				out.print("No records to retrieve from cloud</font></h1></div> ");
  		  		request.setAttribute("flag", 0);
  			}
  			if((int)request.getAttribute("flag") == 13)
  			{
  				out.print("<div align='center'><h1><font color='red' size = '6' face='Times New Roman'>");
  				out.print("No records to backup</font></h1></div> ");
  		  		request.setAttribute("flag", 0);
  			}
  		 %>	      
        
        <table width=100%">
        
        <tr>
        <td>
         <h3><font color="Blue" face="Times New Roman">Understanding Customer Requirement:</font></h3>
          <select name="understanding_customer_requirement" style="width: 150px;">
          <option> </option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
       </td>
       
       <td align="center" border="1">
		<input class="btn btn-submit" type="submit" value="Band Performance Chart" />
       </td>
       </tr> 

<tr>
<td>
          <h3><font color="Blue" face="Times New Roman">AR and Service Improvement Suggestions:</font></h3>
          <select name="ar_and_service_improvement_suggestions" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "2">2</option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
</td>
	 <td align="center">
		<input class="btn btn-submit" type="submit" value="Average Band Chart" />
       </td>
</tr>

<tr>       
<td>
          <h3><font color="Blue" face="Times New Roman">Number Of A3:</font></h3>
          <select name="number_of_a3" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "2">2</option>
          <option value = "3">3</option>
          <option value = "4">4</option>
          </select>
</td>
 <td align="center">
		<input class="btn btn-submit" type="submit" value="Band Data Analysis" />
       </td>
</tr>

<tr>
<td>      
          <h3><font color="Blue" face="Times New Roman">Define Business Process KPIs:</font></h3>
          <select name="define_business_process_kpis" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "3">3</option>
          </select>
</td>
 <td align="center">
		<input class="btn btn-submit" type="submit" value="Module Data Analysis" />
       </td>
</tr>

<tr> 
<td>     
          <h3><font color="Blue" face="Times New Roman">Influence To Increase Higher Level Work:</font></h3>
          <select name="influence_to_increase_higher_level_work" style="width: 150px;">
          <option> </option>
          <option value = "1">1</option>
          <option value = "3">3</option>
          </select>
          
</td>
</tr>
</table>                   
  <br>
          <input class="btn btn-submit" type="submit" value="Submit" />
       
	
 </form>
 <table>
 <tr>
 <td>
 	<form method = "post" action = "service_delivery_manager_cloud_backup.jsp">
		
         	 	<input class="btn btn-submit" type="submit" value="Backup Employee Data" />
        	
	</form>
</td>
<td>	
	<form method = "post" action = "service_delivery_manager_rating.jsp">
	
         	 	<input class="btn btn-submit" type="submit" value="Generate Employee Ratings" />
     	
	</form>
</td>
<td>	
	<form method = "post" action = "service_delivery_manager_cloud_retrieval.jsp">
	
         	 	<input class="btn btn-submit" type="submit" value="Retrieve Employee Ratings" />
 </form>
 </td>
 </tr>
 
 </table>
 <br>
         <a href = "logoutser">
          <input class="btn btn-submit" type="button" value="Logout" />
        
	</a>
        	
	</form>  
  </div>
</div>  
</body>
</html>
 
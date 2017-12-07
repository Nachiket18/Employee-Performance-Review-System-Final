package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.EmployeeDataAlreadyBackedUpException;
import exceptions.EmployeeNotFoundException;
import exceptions.NoRecordsToBackupException;

/**
 * Servlet implementation class ServiceDeliveryManagerCloudBackupServlet
 */
@WebServlet("/ServiceDeliveryManagerCloudBackupServlet")
public class ServiceDeliveryManagerCloudBackupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDeliveryManagerCloudBackupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int result = 0;
		DaoInterface dao = new DaoInterfaceImplementation();
		int employee_code = Integer.parseInt(request.getParameter("employee_code"));
		String band = request.getParameter("band");
		
		try 
		{
			if(band.equals("p3"))
			{
				result = dao.createEmployeeRatingP3ForCloud(employee_code);
				if(result == 1)
				{
					request.setAttribute("flag", 4);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			}
				
			if(band.equals("p4"))
			{
				result = dao.createEmployeeRatingP4ForCloud(employee_code);
				if(result == 1)
				{
					request.setAttribute("flag", 4);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}	
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			}
				
			if(band.equals("p5"))
			{
				result = dao.createEmployeeRatingP5ForCloud(employee_code);
				if(result == 1)
				{
					request.setAttribute("flag", 4);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			}
		} catch (EmployeeDataAlreadyBackedUpException e) 
		{
			request.setAttribute("flag", 6);
			RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
			rd.forward(request, response);
		} catch (EmployeeNotFoundException e)
		{
			request.setAttribute("flag", 2);
			RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
			rd.forward(request, response);
		} catch (NoRecordsToBackupException e) 
		{
			request.setAttribute("flag", 13);
			RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
			rd.forward(request, response);
		}		
	}
}

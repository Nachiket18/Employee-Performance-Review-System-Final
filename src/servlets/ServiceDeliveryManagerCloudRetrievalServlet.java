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
import exceptions.NoRecordsToRetrieveException;
import exceptions.RatedEmployeeAlreadyRetrievedException;

/**
 * Servlet implementation class ServiceDeliveryManagerCloudRetrievalServlet
 */
@WebServlet("/ServiceDeliveryManagerCloudRetrievalServlet")
public class ServiceDeliveryManagerCloudRetrievalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDeliveryManagerCloudRetrievalServlet() {
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
		try 
		{
			result = dao.retrieveCloudData(employee_code);
			if(result == 1)
			{
				request.setAttribute("flag", 7);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
			if(result == 0)
			{
				request.setAttribute("flag", 5);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
		} catch (RatedEmployeeAlreadyRetrievedException e)
		{
			request.setAttribute("flag", 8);
			RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
			rd.forward(request, response);
		} catch (NoRecordsToRetrieveException e) 
		{
			request.setAttribute("flag", 12);
			RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
			rd.forward(request, response);
		}
	}

}

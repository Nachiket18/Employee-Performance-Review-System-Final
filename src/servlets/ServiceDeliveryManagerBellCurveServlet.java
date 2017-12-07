package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bellcurve.BellCurve;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.AllEmployeesNotRatedException;

/**
 * Servlet implementation class ServiceDeliveryManagerBellCurveServlet
 */
@WebServlet("/ServiceDeliveryManagerBellCurveServlet")
public class ServiceDeliveryManagerBellCurveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDeliveryManagerBellCurveServlet() {
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
		DaoInterface dao = new DaoInterfaceImplementation();
		int result = dao.checkEmployeeRatings();
		if(result == 0)
		{
			try 
			{
				throw new AllEmployeesNotRatedException();
			} catch (AllEmployeesNotRatedException e) 
			{
				request.setAttribute("flag", 11);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
		}
		else if(result == 1)
		{
			try 
			{
				BellCurve.main(null);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			} catch (SQLException e) 
			{
		
			}
		}
	}

}

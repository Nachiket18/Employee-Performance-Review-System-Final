package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.IncidentManagementBean;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.EmployeeAlreadyRatedException;
import exceptions.EmployeeNotFoundException;

/**
 * Servlet implementation class IncidentManagementServlet
 */
@WebServlet("/IncidentManagementServlet")
public class IncidentManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncidentManagementServlet() {
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
		HttpSession hs = request.getSession(false);
		DaoInterface dao = new DaoInterfaceImplementation();
		IncidentManagementBean imb = (IncidentManagementBean) request.getAttribute("incident_object");
		try 
		{
			dao.createIncidentManagementEntry(imb);request.setAttribute("flag", 1);
			RequestDispatcher rd = request.getRequestDispatcher("incident_manager.jsp");
			rd.forward(request, response);			
		} catch (EmployeeNotFoundException e) 
		{
			request.setAttribute("flag", 2);
			RequestDispatcher rd = request.getRequestDispatcher("incident_manager.jsp");
			rd.forward(request, response);
		} catch (EmployeeAlreadyRatedException e) 
		{
			request.setAttribute("flag", 3);
			RequestDispatcher rd = request.getRequestDispatcher("incident_manager.jsp");
			rd.forward(request, response);
		}
	}
}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ChangeManagementBean;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.EmployeeAlreadyRatedException;
import exceptions.EmployeeNotFoundException;

/**
 * Servlet implementation class ChangeManagementServlet
 */
@WebServlet("/ChangeManagementServlet")
public class ChangeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeManagementServlet() {
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
		ChangeManagementBean cmb = (ChangeManagementBean) request.getAttribute("change_object");
		DaoInterface dao = new DaoInterfaceImplementation();
		try 
		{
			dao.createChangeManagementEntry(cmb);
			request.setAttribute("flag", 1);
			RequestDispatcher rd = request.getRequestDispatcher("change_manager.jsp");
			rd.forward(request, response);			
		} catch (EmployeeNotFoundException e) 
		{
			request.setAttribute("flag", 2);
			RequestDispatcher rd = request.getRequestDispatcher("change_manager.jsp");
			rd.forward(request, response);
		} catch (EmployeeAlreadyRatedException e) 
		{
			request.setAttribute("flag", 3);
			RequestDispatcher rd = request.getRequestDispatcher("change_manager.jsp");
			rd.forward(request, response);
		}
	}

}

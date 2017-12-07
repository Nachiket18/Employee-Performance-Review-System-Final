package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ManagerBean;
import connection_manager.ConnectionManager;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.WrongManagerCredentialsException;
import security.Decryption;
import security.Sha288;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection conn = ConnectionManager.getConn();
	static PreparedStatement ps;
	static ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
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
		HttpSession hs = request.getSession(true);
		
		DaoInterface dao = new DaoInterfaceImplementation();
		RequestDispatcher rd;
		Sha288 sha = new Sha288();
		Decryption de = new Decryption();
		String manager_name = null;
		String manager_type = null;
		String otp = null;
		String manager_user_name = (String) request.getAttribute("manager_user_name");
		String manager_password = request.getParameter("manager_password");
		manager_password = sha.main(manager_password).trim();
		
		ManagerBean mb;
		try 
		{
			mb = dao.getManager(manager_user_name,manager_password);
			manager_type = mb.getManager_type();
			manager_name = mb.getManager_name();
			otp = mb.getOtp();
			
			manager_type = de.decrypt(manager_type, otp);
			manager_name = de.decrypt(manager_name, otp);
			
			hs.setAttribute("manager_name", manager_name);
			
			if(manager_type.equals("change_manager"))
			{
				rd = request.getRequestDispatcher("change_manager.jsp");
				rd.forward(request, response);
			}
			if(manager_type.equals("domain_manager"))
			{
				rd = request.getRequestDispatcher("domain_manager.jsp");
				rd.forward(request, response);
			}
			if(manager_type.equals("incident_manager"))
			{
				rd = request.getRequestDispatcher("incident_manager.jsp");
				rd.forward(request, response);
			}
			if(manager_type.equals("service_delivery_manager"))
			{
				rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}		
		} catch (WrongManagerCredentialsException e) 
		{
			request.setAttribute("flag", 1);
			rd = request.getRequestDispatcher("sign_in.jsp");
			rd.forward(request, response);
		}
	}
}
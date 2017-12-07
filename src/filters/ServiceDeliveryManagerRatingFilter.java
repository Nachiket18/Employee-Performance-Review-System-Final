package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.ChangeManagementBean;
import beans.DomainManagementBean;
import beans.IncidentManagementBean;
import beans.ServiceDeliveryManagementBean;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.EmployeeNotFoundException;

/**
 * Servlet Filter implementation class ServiceDeliveryManagerRating
 */
@WebFilter("/ServiceDeliveryManagerRating")
public class ServiceDeliveryManagerRatingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServiceDeliveryManagerRatingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		DaoInterface dao = new DaoInterfaceImplementation();
		
		int employee_code = Integer.parseInt(request.getParameter("employee_code"));
		if(employee_code == 0)
		{
			try 
			{
				throw new EmployeeNotFoundException();
			} catch (EmployeeNotFoundException e) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("employee_not_found_exception.jsp");
				rd.forward(request, response);
			}
		}
		ChangeManagementBean cmb = dao.getChangeMangementEntry(employee_code);
		DomainManagementBean dmb = dao.getDomainMangementEntry(employee_code);
		IncidentManagementBean imb = dao.getIncidentMangementEntry(employee_code);
		ServiceDeliveryManagementBean sdmb = dao.getServiceDeliveryMangementEntry(employee_code);
		
		request.setAttribute("change_object", cmb);
		request.setAttribute("domain_object", dmb);
		request.setAttribute("incident_object", imb);
		request.setAttribute("service_object", sdmb);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

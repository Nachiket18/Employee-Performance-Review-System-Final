package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import beans.ManagerBean;
import dao.DaoInterfaceImplementation;

/**
 * Servlet Filter implementation class SignInFilter
 */
@WebFilter("/SignInFilter")
public class SignInFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SignInFilter() {
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
		String manager_user_name = request.getParameter("manager_user_name");
		
		if(manager_user_name.contains("@npav.com") == false)
		{
			manager_user_name += "@npav.com";
		}
		request.setAttribute("manager_user_name", manager_user_name);
				
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

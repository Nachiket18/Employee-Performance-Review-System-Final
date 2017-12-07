package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.ManagerBean;
import security.Encryption;
import security.Sha288;

/**
 * Servlet Filter implementation class SignupFilter
 */
@WebFilter("/SignupFilter")
public class SignUpFilter implements Filter 
{

    /**
     * Default constructor. 
     */
    public SignUpFilter() {
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
		Encryption en = new Encryption();
		ManagerBean mb = new ManagerBean();
		int manager_number = mb.getManager_number();
		mb.setManager_number((manager_number));
		
		/*mb.setManager_name(request.getParameter("manager_name"));
		mb.setManager_user_name(request.getParameter("manager_user_name"));
		mb.setManager_password(request.getParameter("manager_password"));
		mb.setManager_type(request.getParameter("manager_type"));
		mb.setOtp("otp");*/
		
		Encryption.randString();
		String otp = en.getOtp().trim().replaceAll("\0", "").toString();
		mb.setOtp(otp);
		
		Encryption.encrypt(request.getParameter("manager_name"), request.getParameter("manager_name").length());
		String manager_name = en.getCiphertext().trim().replaceAll("\0", "").toString();
		mb.setManager_name(manager_name);
		
		mb.setManager_user_name(request.getParameter("manager_user_name"));
		
		String password = Sha288.main(request.getParameter("manager_password").trim());
		mb.setManager_password(password);
		
		Encryption.encrypt(request.getParameter("manager_type"), request.getParameter("manager_type").length());
		String manager_type = en.getCiphertext().trim().replaceAll("\0", "").toString();
		mb.setManager_type(manager_type);
		
		request.setAttribute("manager_object", mb);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

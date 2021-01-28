package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.ChangeManagementBean;
import security.Encryption;

/**
 * Servlet Filter implementation class ChangeManager
 */
@WebFilter("/ChangeManager")
public class ChangeManagementFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ChangeManagementFilter() {
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
		ChangeManagementBean cmb = new ChangeManagementBean();
		Encryption en = new Encryption();
		
		cmb.setEmployee_code(Integer.parseInt(request.getParameter("employee_code")));
		
		Encryption.randString();
		String otp1 = en.getOtp().trim().replaceAll("\0", "").toString();		
		cmb.setOtp1(otp1);
		
		Encryption.encrypt(request.getParameter("pfc_submission_in_time"), request.getParameter("pfc_submission_in_time").length());
		String pfc_submission_in_time = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		cmb.setPfc_submission_in_time((pfc_submission_in_time));
		
		Encryption.encrypt(request.getParameter("schedule_variance"), request.getParameter("schedule_variance").length());
		String schedule_variance = en.getCiphertext().trim().replaceAll("\0", "").toString();	
		cmb.setSchedule_variance(schedule_variance);
		
		Encryption.encrypt(request.getParameter("ontime_delivery_of_documentation"), request.getParameter("ontime_delivery_of_documentation").length());
		String ontime_delivery_of_documentation = en.getCiphertext().trim().replaceAll("\0", "").toString();	
		cmb.setOntime_delivery_of_documentation(ontime_delivery_of_documentation);
		
		Encryption.randString();
		String otp2 = en.getOtp().trim().replaceAll("\0", "").toString();		
		cmb.setOtp2(otp2);
		
		Encryption.encrypt(request.getParameter("uat_defects"), request.getParameter("uat_defects").length());
		String uat_defects = en.getCiphertext().trim().replaceAll("\0", "").toString();	
		cmb.setUat_defects(uat_defects);
		
		Encryption.encrypt(request.getParameter("effort_variance"), request.getParameter("effort_variance").length());
		String effort_variance = en.getCiphertext().trim().replaceAll("\0", "").toString();	
		cmb.setEffort_variance(effort_variance);
		
		request.setAttribute("change_object", cmb);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

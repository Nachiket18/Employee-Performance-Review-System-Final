package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.ServiceDeliveryManagementBean;
import security.Encryption;

/**
 * Servlet Filter implementation class ServiceDeliveryManager
 */
@WebFilter("/ServiceDeliveryManager")
public class ServiceDeliveryManagementFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServiceDeliveryManagementFilter() {
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
		ServiceDeliveryManagementBean sdmb = new ServiceDeliveryManagementBean();
		Encryption en = new Encryption();
		
		sdmb.setEmployee_code(Integer.parseInt(request.getParameter("employee_code")));
		
		Encryption.randString();
		String otp = en.getOtp().trim().replaceAll("\0", "").toString();		
		sdmb.setOtp(otp);
		
		Encryption.encrypt(request.getParameter("understanding_customer_requirement"), request.getParameter("understanding_customer_requirement").length());
		String understanding_customer_requirement = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		sdmb.setUnderstanding_customer_requirement((understanding_customer_requirement));
		
		Encryption.encrypt(request.getParameter("ar_and_service_improvement_suggestions"), request.getParameter("ar_and_service_improvement_suggestions").length());
		String ar_and_service_improvement_suggestions = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		sdmb.setAr_and_service_improvement_suggestions((ar_and_service_improvement_suggestions));
		
		Encryption.encrypt(request.getParameter("number_of_a3"), request.getParameter("number_of_a3").length());
		String number_of_a3 = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		sdmb.setNumber_of_a3((number_of_a3));
		
		Encryption.encrypt(request.getParameter("influence_to_increase_higher_level_work"), request.getParameter("influence_to_increase_higher_level_work").length());
		String influence_to_increase_higher_level_work = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		sdmb.setInfluence_to_increase_higher_level_work((influence_to_increase_higher_level_work));
		
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

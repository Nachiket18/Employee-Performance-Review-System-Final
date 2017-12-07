package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.IncidentManagementBean;
import security.Encryption;

/**
 * Servlet Filter implementation class IncidentManager
 */
@WebFilter("/IncidentManager")
public class IncidentManagementFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IncidentManagementFilter() {
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
		IncidentManagementBean imb = new IncidentManagementBean();
		Encryption en = new Encryption();
		
		imb.setEmployee_code(Integer.parseInt(request.getParameter("employee_code")));
		
		Encryption.randString();
		String otp1 = en.getOtp().trim().replaceAll("\0", "").toString();		
		imb.setOtp1(otp1);
		
		
		Encryption.encrypt(request.getParameter("backlog"), request.getParameter("backlog").length());
		String backlog = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setBacklog(backlog);
		
		Encryption.encrypt(request.getParameter("no_of_breaches"), request.getParameter("no_of_breaches").length());
		String no_of_breaches = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setNo_of_breaches((no_of_breaches));
		
		Encryption.encrypt(request.getParameter("defects"), request.getParameter("defects").length());
		String defects = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setDefects(defects);
		
		Encryption.encrypt(request.getParameter("kdb_doc_prep"), request.getParameter("kdb_doc_prep").length());
		String kdb_doc_prep = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setKdb_doc_prep(kdb_doc_prep);
		
		Encryption.randString();
		String otp2 = en.getOtp().trim().replaceAll("\0", "").toString();
		imb.setOtp2(otp2);
		
		Encryption.encrypt(request.getParameter("response_breach"), request.getParameter("response_breach").length());
		String response_breach = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setResponse_breach((response_breach));
		
		Encryption.randString();
		String otp3 = en.getOtp().trim().replaceAll("\0", "").toString();
		imb.setOtp3(otp3);
		
		Encryption.encrypt(request.getParameter("status_update"), request.getParameter("status_update").length());
		String status_update = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setStatus_update((status_update));
		
		Encryption.encrypt(request.getParameter("review_doc"), request.getParameter("review_doc").length());
		String review_doc = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		imb.setReview_doc((review_doc));
		
		request.setAttribute("incident_object", imb);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

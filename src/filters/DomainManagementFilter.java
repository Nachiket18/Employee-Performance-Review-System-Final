package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.DomainManagementBean;
import security.Encryption;

/**
 * Servlet Filter implementation class DomainManager
 */
@WebFilter("/DomainManager")
public class DomainManagementFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DomainManagementFilter() {
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
		DomainManagementBean dmb = new DomainManagementBean();
		Encryption en = new Encryption();
		
		dmb.setEmployee_code(Integer.parseInt(request.getParameter("employee_code")));
		
		Encryption.randString();
		String otp1 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp1(otp1);
		
		Encryption.encrypt(request.getParameter("adherence_to_shift_timing"), request.getParameter("adherence_to_shift_timing").length());
		String adherence_to_shift_timing = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setAdherence_to_shift_timing(adherence_to_shift_timing);
		
		Encryption.encrypt(request.getParameter("leave_planning"), request.getParameter("leave_planning").length());
		String leave_planning = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setLeave_planning((leave_planning));
		
		Encryption.encrypt(request.getParameter("following_meeting_etiquettes"), request.getParameter("following_meeting_etiquettes").length());
		String following_meeting_etiquettes = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setFollowing_meeting_etiquettes((following_meeting_etiquettes));
		
		Encryption.randString();
		String otp2 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp2(otp2);
		
		Encryption.encrypt(request.getParameter("proactive"), request.getParameter("proactive").length());
		String proactive = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setProactive(proactive);
		
		Encryption.randString();
		String otp3 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp3(otp3);
		
		Encryption.encrypt(request.getParameter("responsible_to_deliverables"), request.getParameter("responsible_to_deliverables").length());
		String responsible_to_deliverables = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setResponsible_to_deliverables(responsible_to_deliverables);
		
		Encryption.randString();
		String otp4 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp4(otp4);
		
		Encryption.encrypt(request.getParameter("training_hours"), request.getParameter("training_hours").length());
		String training_hours = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setTraining_hours(training_hours);
		
		Encryption.encrypt(request.getParameter("skill_improvement"), request.getParameter("skill_improvement").length());
		String skill_improvement = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setSkill_improvement(skill_improvement);
		
		Encryption.encrypt(request.getParameter("itil_certification"), request.getParameter("itil_certification").length());
		String itil_certification = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setItil_certification(itil_certification);
		
		Encryption.randString();
		String otp5 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp5(otp5);
		
		Encryption.encrypt(request.getParameter("idle_time_management"), request.getParameter("idle_time_management").length());
		String idle_time_management = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setIdle_time_management(idle_time_management);
		
		Encryption.randString();
		String otp6 = en.getOtp().trim().replaceAll("\0", "").toString();		
		dmb.setOtp6(otp6);
		
		Encryption.encrypt(request.getParameter("efforts_in_development_of_team_members"), request.getParameter("efforts_in_development_of_team_members").length());
		String efforts_in_development_of_team_members = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setEfforts_in_development_of_team_members(efforts_in_development_of_team_members);
		
		Encryption.encrypt(request.getParameter("participation_in_organizational_initiatives"), request.getParameter("participation_in_organizational_initiatives").length());
		String participation_in_organizational_initiatives = en.getCiphertext().trim().replaceAll("\0", "").toString();		
		dmb.setParticipation_in_organizational_initiatives(participation_in_organizational_initiatives);
		
		request.setAttribute("domain_object", dmb);

		chain.doFilter(request, response);		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

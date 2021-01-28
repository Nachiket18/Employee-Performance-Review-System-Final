package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ChangeManagementBean;
import beans.DomainManagementBean;
import beans.IncidentManagementBean;
import beans.ServiceDeliveryManagementBean;
import dao.DaoInterface;
import dao.DaoInterfaceImplementation;
import exceptions.CloudConnectionException;
import exceptions.EmployeeAlreadyRatedException;
import security.Encryption;

/**
 * Servlet implementation class ServiceDeliveryManagerRating
 */
@WebServlet("/ServiceDeliveryManagerRating")
public class ServiceDeliveryManagerRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDeliveryManagerRatingServlet() {
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
		DomainManagementBean dmb = (DomainManagementBean) request.getAttribute("domain_object");		
		IncidentManagementBean imb = (IncidentManagementBean) request.getAttribute("incident_object");
		ServiceDeliveryManagementBean sdmb = (ServiceDeliveryManagementBean) request.getAttribute("service_object");
		
		Encryption en = new Encryption();	
		Encryption.randString();
		String otp = en.getOtp().trim().replaceAll("\0", "").toString();	
		
		DaoInterface dao = new DaoInterfaceImplementation();
		int employee_code = Integer.parseInt(request.getParameter("employee_code"));
		int result = 0;
		
		String finalRating = null;
		String productivity = null;
		String onTimeDelivery = null;
		String selfDevelopment = null;
		String utilization = null;
		String teamDevelopment = null;
		String customerSatisfaction = null;
		String complianceToProcess = null;
		String band = request.getParameter("band");
		
		if(band.equals("p3"))
		{		
			productivity = String.valueOf((Double.parseDouble(cmb.getEffort_variance())*(0.20)+Double.parseDouble(cmb.getUat_defects())*(0.20)+Double.parseDouble(imb.getDefects())*(0.20)+Double.parseDouble(imb.getBacklog())*(0.10)+Double.parseDouble(imb.getKdb_doc_prep())*(0.10)+Double.parseDouble(imb.getNo_of_breaches())*(0.20))*0.4);
			onTimeDelivery = String.valueOf((Double.parseDouble(imb.getResponse_breach())*(0.30)+Double.parseDouble(cmb.getPfc_submission_in_time())*(0.30)+Double.parseDouble(cmb.getSchedule_variance())*(0.30)+Double.parseDouble(cmb.getOntime_delivery_of_documentation())*(0.10)+Double.parseDouble(dmb.getResponsible_to_deliverables())*(0.20))*0.15);
			selfDevelopment = String.valueOf((Double.parseDouble(dmb.getProactive())*(0.25)+Double.parseDouble(dmb.getTraining_hours())*(0.20)+Double.parseDouble(dmb.getSkill_improvement())*(0.40)+Double.parseDouble(dmb.getItil_certification())*(0.15))*0.1);
			utilization = String.valueOf(Double.parseDouble(dmb.getIdle_time_management())*0.2);
			complianceToProcess = String.valueOf((Double.parseDouble(dmb.getAdherence_to_shift_timing())*(0.20)+Double.parseDouble(dmb.getLeave_planning())*(0.20)+Double.parseDouble(dmb.getFollowing_meeting_etiquettes())*(0.10)+Double.parseDouble(imb.getReview_doc())*(0.25)+Double.parseDouble(imb.getStatus_update())*(0.25))*0.15);
			finalRating = String.valueOf(Double.parseDouble(productivity)+Double.parseDouble(onTimeDelivery)+Double.parseDouble(utilization)+Double.parseDouble(selfDevelopment)+Double.parseDouble(complianceToProcess));
			
			Encryption.encrypt(productivity, productivity.length());
			productivity = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(onTimeDelivery,onTimeDelivery.length());
			onTimeDelivery = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(selfDevelopment, selfDevelopment.length());
			selfDevelopment = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(utilization, utilization.length());
			utilization = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(complianceToProcess, complianceToProcess.length());
			complianceToProcess = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(finalRating, finalRating.length());
			finalRating = en.getCiphertext().trim().replaceAll("\0", "").toString();
			
			try 
			{
				result = dao.createEmployeeRatingP3(employee_code, productivity, onTimeDelivery, utilization, complianceToProcess, selfDevelopment, finalRating, otp);
				if(result == 1)
				{
					request.setAttribute("flag", 9);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			} catch (EmployeeAlreadyRatedException e) 
			{
				request.setAttribute("flag", 10);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
		}
		
		if(band.equals("p4"))
		{			
			productivity = String.valueOf((Double.parseDouble(cmb.getEffort_variance())*(0.20)+Double.parseDouble(cmb.getUat_defects())*(0.20)+Double.parseDouble(imb.getDefects())*(0.20)+Double.parseDouble(imb.getBacklog())*(0.10)+Double.parseDouble(imb.getKdb_doc_prep())*(0.10)+Double.parseDouble(imb.getNo_of_breaches())*(0.20))*0.4);
			onTimeDelivery = String.valueOf((Double.parseDouble(imb.getResponse_breach())*(0.30)+Double.parseDouble(cmb.getPfc_submission_in_time())*(0.30)+Double.parseDouble(cmb.getSchedule_variance())*(0.30)+Double.parseDouble(cmb.getOntime_delivery_of_documentation())*(0.10)+Double.parseDouble(dmb.getResponsible_to_deliverables())*(0.20))*0.10);
			selfDevelopment = String.valueOf((Double.parseDouble(dmb.getProactive())*(0.25)+Double.parseDouble(dmb.getTraining_hours())*(0.20)+Double.parseDouble(dmb.getSkill_improvement())*(0.40)+Double.parseDouble(dmb.getItil_certification())*(0.15))*0.1);
			utilization = String.valueOf(Double.parseDouble(dmb.getIdle_time_management())*0.2);
			complianceToProcess = String.valueOf((Double.parseDouble(dmb.getAdherence_to_shift_timing())*(0.20)+Double.parseDouble(dmb.getLeave_planning())*(0.20)+Double.parseDouble(dmb.getFollowing_meeting_etiquettes())*(0.10)+Double.parseDouble(imb.getReview_doc())*(0.25)+Double.parseDouble(imb.getStatus_update())*(0.25))*0.10);
			teamDevelopment = String.valueOf(Double.parseDouble(dmb.getEfforts_in_development_of_team_members())+Double.parseDouble(dmb.getParticipation_in_organizational_initiatives())*0.05);
            customerSatisfaction = String.valueOf((Double.parseDouble(sdmb.getAr_and_service_improvement_suggestions())*(0.25)+Double.parseDouble(sdmb.getInfluence_to_increase_higher_level_work())*(0.25)+Double.parseDouble(sdmb.getNumber_of_a3())*(0.20)+Double.parseDouble(sdmb.getUnderstanding_customer_requirement())*(0.20))*0.05);
			finalRating = String.valueOf(Double.parseDouble(productivity)+Double.parseDouble(onTimeDelivery)+Double.parseDouble(utilization)+Double.parseDouble(teamDevelopment)+Double.parseDouble(selfDevelopment)+Double.parseDouble(customerSatisfaction)+Double.parseDouble(complianceToProcess));
			
			Encryption.encrypt(productivity, productivity.length());
			productivity = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(onTimeDelivery,onTimeDelivery.length());
			onTimeDelivery = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(selfDevelopment, selfDevelopment.length());
			selfDevelopment = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(utilization, utilization.length());
			utilization = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(teamDevelopment, teamDevelopment.length());
			teamDevelopment = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(customerSatisfaction, customerSatisfaction.length());
			customerSatisfaction = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(complianceToProcess, complianceToProcess.length());
			complianceToProcess = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(finalRating, finalRating.length());
			finalRating = en.getCiphertext().trim().replaceAll("\0", "").toString();
			
			try 
			{
				result = dao.createEmployeeRatingP4(employee_code, productivity, onTimeDelivery, utilization, complianceToProcess, selfDevelopment, teamDevelopment, customerSatisfaction, finalRating, otp);
				if(result == 1)
				{
					request.setAttribute("flag", 9);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			} catch (EmployeeAlreadyRatedException e) 
			{
				request.setAttribute("flag", 10);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
		}
		
		if(band.equals("p5"))
		{			
			productivity = String.valueOf((Double.parseDouble(cmb.getEffort_variance())*(0.20)+Double.parseDouble(cmb.getUat_defects())*(0.20)+Double.parseDouble(imb.getDefects())*(0.20)+Double.parseDouble(imb.getBacklog())*(0.10)+Double.parseDouble(imb.getKdb_doc_prep())*(0.10)+Double.parseDouble(imb.getNo_of_breaches())*(0.20))*0.5);
			onTimeDelivery = String.valueOf((Double.parseDouble(imb.getResponse_breach())*(0.30)+Double.parseDouble(cmb.getPfc_submission_in_time())*(0.30)+Double.parseDouble(cmb.getSchedule_variance())*(0.30)+Double.parseDouble(cmb.getOntime_delivery_of_documentation())*(0.10)+Double.parseDouble(dmb.getResponsible_to_deliverables())*(0.20))*0.15);
			selfDevelopment = String.valueOf((Double.parseDouble(dmb.getProactive())*(0.25)+Double.parseDouble(dmb.getTraining_hours())*(0.20)+Double.parseDouble(dmb.getSkill_improvement())*(0.40)+Double.parseDouble(dmb.getItil_certification())*(0.15))*0.15);
			teamDevelopment = String.valueOf(Double.parseDouble(dmb.getEfforts_in_development_of_team_members())+Double.parseDouble(dmb.getParticipation_in_organizational_initiatives())*0.10);
			customerSatisfaction = String.valueOf((Double.parseDouble(sdmb.getAr_and_service_improvement_suggestions())*(0.25)+Double.parseDouble(sdmb.getInfluence_to_increase_higher_level_work())*(0.25)+Double.parseDouble(sdmb.getNumber_of_a3())*(0.20)+Double.parseDouble(sdmb.getUnderstanding_customer_requirement())*(0.20))*0.10);
			finalRating = String.valueOf(Double.parseDouble(productivity)+Double.parseDouble(onTimeDelivery)+Double.parseDouble(teamDevelopment)+Double.parseDouble(selfDevelopment)+Double.parseDouble(customerSatisfaction));
			
			Encryption.encrypt(productivity, productivity.length());
			productivity = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(onTimeDelivery,onTimeDelivery.length());
			onTimeDelivery = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(selfDevelopment, selfDevelopment.length());
			selfDevelopment = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(teamDevelopment, teamDevelopment.length());
			teamDevelopment = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(customerSatisfaction, customerSatisfaction.length());
			customerSatisfaction = en.getCiphertext().trim().replaceAll("\0", "").toString();
			 
			Encryption.encrypt(finalRating, finalRating.length());
			finalRating = en.getCiphertext().trim().replaceAll("\0", "").toString();
			
			try 
			{
				result = dao.createEmployeeRatingP5(employee_code, productivity, onTimeDelivery, selfDevelopment, teamDevelopment, customerSatisfaction, finalRating, otp);
				if(result == 1)
				{
					request.setAttribute("flag", 9);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
				if(result == 0)
				{
					request.setAttribute("flag", 5);
					RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
					rd.forward(request, response);
				}
			} catch (EmployeeAlreadyRatedException e) 
			{
				request.setAttribute("flag", 10);
				RequestDispatcher rd = request.getRequestDispatcher("service_delivery_manager.jsp");
				rd.forward(request, response);
			}
		}
	}
}
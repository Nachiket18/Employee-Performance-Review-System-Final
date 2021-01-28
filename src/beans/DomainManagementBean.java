package beans;

/**
 * Java Bean class for Domain Management entries
 * @author GreyHood
 *
 */
public class DomainManagementBean 
{
	private int employee_code;
	private String otp1;
	private String otp2;
	private String otp3;
	private String otp4;
	private String otp5;
	private String otp6;
	
	private String adherence_to_shift_timing;
	private String leave_planning;
	private String following_meeting_etiquettes;
	private String proactive;
	private String responsible_to_deliverables;
	
	private String training_hours;
	private String skill_improvement;
	private String itil_certification;
	private String idle_time_management;
	
	private String efforts_in_development_of_team_members;
	private String participation_in_organizational_initiatives;
	
	/**
	 * Default constructor for DomainManagementBean class
	 */
	public DomainManagementBean() 
	{
		
	}

	/**
	 * @return Employee code 
	 */
	public int getEmployee_code() {
		return employee_code;
	}

	/**
	 * sets Employee code
	 * @param employee_code Employee code
	 */
	public void setEmployee_code(int employee_code) {
		this.employee_code = employee_code;
	}

	/**
	 * @return cryptographic key for internal_compliance_1 table 
	 */
	public String getOtp1() {
		return otp1;
	}

	/**
	 * sets cryptographic key for internal_compliance_1 table
	 * @param otp1 cryptographic key for internal_compliance_1 table
	 */
	public void setOtp1(String otp1) {
		this.otp1 = otp1;
	}

	/**
	 * @return cryptographic key for internal_compliance_2 table 
	 */
	public String getOtp2() {
		return otp2;
	}

	/** sets cryptographic key for internal_compliance_2 table
	 * @param otp2 cryptographic key for internal_compliance_2 table
	 */
	public void setOtp2(String otp2) {
		this.otp2 = otp2;
	}

	/**
	 * @return cryptographic key for internal_compliance_3 table 
	 */
	public String getOtp3() {
		return otp3;
	}

	/**
	 * sets cryptographic key for internal_compliance_3 table
	 * @param otp3 cryptographic key for internal_compliance_3 table
	 */
	public void setOtp3(String otp3) {
		this.otp3 = otp3;
	}

	/**
	 * @return cryptographic key for self_development_1 table 
	 */
	public String getOtp4() {
		return otp4;
	}

	/**
	 * sets cryptographic key for self_development_1 table
	 * @param otp4 cryptographic key for self_development_1 table
	 */
	public void setOtp4(String otp4) {
		this.otp4 = otp4;
	}

	/**
	 * @return  cryptographic key for self_development_2 table 
	 */
	public String getOtp5() {
		return otp5;
	}

	/**
	 * sets cryptographic key for self_development_2 table
	 * @param otp5 cryptographic key for self_development_2 table 
	 */
	public void setOtp5(String otp5) {
		this.otp5 = otp5;
	}

	/**
	 * @return cryptographic key for team_development table 
	 */
	public String getOtp6() {
		return otp6;
	}

	/**
	 * sets cryptographic key for team_development table
	 * @param otp6 cryptographic key for team_development table
	 */
	public void setOtp6(String otp6) {
		this.otp6 = otp6;
	}

	/**
	 * @return Adherence to shift timing
	 */
	public String getAdherence_to_shift_timing() {
		return adherence_to_shift_timing;
	}

	/** 
	 * sets Adherence to shift timing
	 * @param adherence_to_shift_timing Adherence to shift timing
	 */
	public void setAdherence_to_shift_timing(String adherence_to_shift_timing) {
		this.adherence_to_shift_timing = adherence_to_shift_timing;
	}

	/**
	 * @return Leave planning
	 */
	public String getLeave_planning() {
		return leave_planning;
	}

	/**
	 * sets Leave planning
	 * @param leave_planning Leave planning
	 */
	public void setLeave_planning(String leave_planning) {
		this.leave_planning = leave_planning;
	}

	/**
	 * sets Following meeting etiquettes
	 * @return Following meeting etiquettes
	 */
	public String getFollowing_meeting_etiquettes() {
		return following_meeting_etiquettes;
	}

	/** sets Following meeting etiquettes
	 * @param following_meeting_etiquettes Following meeting etiquettes
	 */
	public void setFollowing_meeting_etiquettes(String following_meeting_etiquettes) {
		this.following_meeting_etiquettes = following_meeting_etiquettes;
	}

	/**
	 * @return Proactive
	 */
	public String getProactive() {
		return proactive;
	}

	/**
	 * sets Proactive
	 * @param proactive Proactive
	 */
	public void setProactive(String proactive) {
		this.proactive = proactive;
	}

	/**
	 * @return Responsible to deliverables
	 */
	public String getResponsible_to_deliverables() {
		return responsible_to_deliverables;
	}

	/**
	 * sets Responsible to deliverables
	 * @param responsible_to_deliverables Responsible to deliverables
	 */
	public void setResponsible_to_deliverables(String responsible_to_deliverables) {
		this.responsible_to_deliverables = responsible_to_deliverables;
	}

	/**
	 * @return Training hours
	 */
	public String getTraining_hours() {
		return training_hours;
	}

	/**
	 * sets Training hours
	 * @param training_hours Training hours
	 */
	public void setTraining_hours(String training_hours) {
		this.training_hours = training_hours;
	}

	/**
	 * @return Skill improvement
	 */
	public String getSkill_improvement() {
		return skill_improvement;
	}

	/**
	 * sets Skill improvement
	 * @param skill_improvement Skill improvement
	 */
	public void setSkill_improvement(String skill_improvement) {
		this.skill_improvement = skill_improvement;
	}

	/**
	 * @return ITIL certification
	 */
	public String getItil_certification() {
		return itil_certification;
	}

	/**
	 * sets ITIL certification
	 * @param itil_certification ITIL certification
	 */
	public void setItil_certification(String itil_certification) {
		this.itil_certification = itil_certification;
	}

	/**
	 * @return Idle time management
	 */
	public String getIdle_time_management() {
		return idle_time_management;
	}

	/**
	 * sets Idle time management
	 * @param idle_time_management Idle time management
	 */
	public void setIdle_time_management(String idle_time_management) {
		this.idle_time_management = idle_time_management;
	}

	/**
	 * @return Efforts in development of team members
	 */
	public String getEfforts_in_development_of_team_members() {
		return efforts_in_development_of_team_members;
	}

	/**
	 * sets Efforts in development of team members
	 * @param efforts_in_development_of_team_members Efforts in development of team members
	 */
	public void setEfforts_in_development_of_team_members(String efforts_in_development_of_team_members) {
		this.efforts_in_development_of_team_members = efforts_in_development_of_team_members;
	}

	/** 
	 * @return Participation in organizational initiatives
	 */
	public String getParticipation_in_organizational_initiatives() {
		return participation_in_organizational_initiatives; 
	}

	/**
	 * sets Participation in organizational initiatives
	 * @param participation_in_organizational_initiatives Participation in organizational initiatives
	 */
	public void setParticipation_in_organizational_initiatives(String participation_in_organizational_initiatives) {
		this.participation_in_organizational_initiatives = participation_in_organizational_initiatives;
	}
}

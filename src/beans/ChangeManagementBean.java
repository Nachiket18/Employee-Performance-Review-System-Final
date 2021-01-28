package beans;

/**
 * Java Bean class for Change Management entries
 * @author GreyHood
 *
 */
public class ChangeManagementBean
{
	private int employee_code;
	private String otp1;
	private String otp2;
	private String pfc_submission_in_time;
	private String schedule_variance;
	private String ontime_delivery_of_documentation;
	private String uat_defects;
	private String effort_variance;
	
	/**
	 * Default constructor for ChangeManagementBean class
	 * 
	 */
	public ChangeManagementBean() 
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
	 * @param employee_code Employee Code
	 */
	public void setEmployee_code(int employee_code) {
		this.employee_code = employee_code;
	}

	/**
	 * @return cryptographic key for change_management_1 table 
	 */
	public String getOtp1() {
		return otp1;
	}

	/**
	 * sets first cryptographic key table
	 * @param otp1 cryptographic key for change_management_1 table
	 */
	public void setOtp1(String otp1) {
		this.otp1 = otp1;
	}

	/**
	 * @return cryptographic key for change_management_2 table 
	 */
	public String getOtp2() {
		return otp2;
	}

	/**
	 * sets second cryptographic key table
	 * @param otp2 cryptographic key for change_management_2 table
	 */
	public void setOtp2(String otp2) {
		this.otp2 = otp2;
	}

	/**
	 * @return PFC submission in time 
	 */
	public String getPfc_submission_in_time() {
		return pfc_submission_in_time;
	}

	/**
	 * sets PFC submission in time
	 * @param pfc_submission_in_time PFC submission in time
	 */
	public void setPfc_submission_in_time(String pfc_submission_in_time) {
		this.pfc_submission_in_time = pfc_submission_in_time;
	}

	/**
	 * @return Schedule variance 
	 */
	public String getSchedule_variance() {
		return schedule_variance;
	}

	/**
	 * sets Schedule variance
	 * @param schedule_variance Schedule variance
	 */
	public void setSchedule_variance(String schedule_variance) {
		this.schedule_variance = schedule_variance;
	}

	/**
	 * @return On time delivery of documentation 
	 */
	public String getOntime_delivery_of_documentation() {
		return ontime_delivery_of_documentation;
	}

	/**
	 * sets On time delivery of documentation
	 * @param ontime_delivery_of_documentation On time delivery of documentation
	 */
	public void setOntime_delivery_of_documentation(String ontime_delivery_of_documentation) {
		this.ontime_delivery_of_documentation = ontime_delivery_of_documentation;
	}

	/**
	 * @return UAT defects 
	 */
	public String getUat_defects() {
		return uat_defects;
	}

	/**
	 * sets UAT defects
	 * @param uat_defects UAT defects
	 */
	public void setUat_defects(String uat_defects) {
		this.uat_defects = uat_defects;
	}

	/**
	 * @return Effort variance 
	 */
	public String getEffort_variance() {
		return effort_variance;
	}

	/**
	 * sets Effort variance
	 * @param effort_variance Effort variance
	 */
	public void setEffort_variance(String effort_variance) {
		this.effort_variance = effort_variance;
	}
}
package beans;

/**
 * Java Bean class for Service Delivery Management entries
 * @author GreyHood
 *
 */
public class ServiceDeliveryManagementBean 
{
	private int employee_code;
	private String otp;
	private String understanding_customer_requirement;
	private String ar_and_service_improvement_suggestions;
	private String number_of_a3;
	private String influence_to_increase_higher_level_work;
	
	/**
	 * Default constructor for ServiceDeliveryManagementBean class
	 */
	public ServiceDeliveryManagementBean() 
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
	 * @return cryptographic key for customer_focus table
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * sets cryptographic key for customer_focus table
	 * @param otp cryptographic key for customer_focus table
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return Understanding customer requirement
	 */
	public String getUnderstanding_customer_requirement() {
		return understanding_customer_requirement;
	}

	/**
	 * sets Understanding customer requirement
	 * @param understanding_customer_requirement Understanding customer requirement
	 */
	public void setUnderstanding_customer_requirement(String understanding_customer_requirement) {
		this.understanding_customer_requirement = understanding_customer_requirement;
	}

	/**
	 * @return Ar and service improvement suggestions
	 */
	public String getAr_and_service_improvement_suggestions() {
		return ar_and_service_improvement_suggestions;
	}

	/**
	 * sets Ar and service improvement suggestions
	 * @param ar_and_service_improvement_suggestions Ar and service improvement suggestions
	 */
	public void setAr_and_service_improvement_suggestions(String ar_and_service_improvement_suggestions) {
		this.ar_and_service_improvement_suggestions = ar_and_service_improvement_suggestions;
	}

	/**
	 * @return Number of A3
	 */
	public String getNumber_of_a3() {
		return number_of_a3;
	}

	/**
	 * sets Number of A3
	 * @param number_of_a3 Number of A3
	 */
	public void setNumber_of_a3(String number_of_a3) {
		this.number_of_a3 = number_of_a3;
	}

	/**
	 * @return Influence to increase higher level work
	 */
	public String getInfluence_to_increase_higher_level_work() {
		return influence_to_increase_higher_level_work;
	}

	/**
	 * sets Influence to increase higher level work
	 * @param influence_to_increase_higher_level_work Influence to increase higher level work
	 */
	public void setInfluence_to_increase_higher_level_work(String influence_to_increase_higher_level_work) {
		this.influence_to_increase_higher_level_work = influence_to_increase_higher_level_work;
	}
}

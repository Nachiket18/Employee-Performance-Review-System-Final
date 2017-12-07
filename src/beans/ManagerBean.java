package beans;

import dao.DaoInterfaceImplementation;

/**
 * Java Bean class for Manager information entries
 * @author GreyHood
 *
 */
public class ManagerBean 
{ 
	private int manager_number;
	private String manager_name;
	private String manager_user_name;
	private String manager_password;
	private String manager_type;
	private String otp;
	private String quotient;
	
	/**
	 * Default constructor for ManagerBean class
	 */
	public ManagerBean() 
	{
		
	}
	
	/**
	 * Parameterized constructor for ManagerBean class
	 * 
	 * @param manager_number Unique manager number
	 * @param manager_name Manager name
	 * @param manager_user_name Manager user name for logging in
	 * @param manager_password Manager password
	 * @param manager_type Manager type
	 * @param otp cryptographic key for managers table
	 */
	public ManagerBean(int manager_number, String manager_name, String manager_user_name, String manager_password,String manager_type, String otp) 
	{
		this.manager_number = manager_number;
		this.manager_name = manager_name;
		this.manager_user_name = manager_user_name;
		this.manager_password = manager_password;
		this.manager_type = manager_type;
		this.otp = otp;
	}

	/**
	 * @return cryptographic key for managers table
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * sets cryptographic key for managers table
	 * @param otp cryptographic key for managers table
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return Quotient for cryptography purpose 
	 */
	public String getQuotient() {
		return quotient;
	}

	/**
	 * sets Quotient for cryptography purpose
	 * @param quotient Quotient for cryptography purpose
	 */
	public void setQuotient(String quotient) {
		this.quotient = quotient;
	}

	/**
	 * @return Manager number
	 */
	public int getManager_number() 
	{
		DaoInterfaceImplementation dao = new DaoInterfaceImplementation();
		manager_number = dao.getManagerNumber();
		return manager_number;
	}
	
	/**
	 * sets manager number
	 * @param manager_number Manager number
	 */
	public void setManager_number(int manager_number) {
		this.manager_number = manager_number;
	}
	
	/**
	 * @return Manager name
	 */
	public String getManager_name() {
		return manager_name;
	}
	
	/**
	 * sets Manager name
	 * @param manager_name Manager name
	 */
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	
	/**
	 * @return Manager user name
	 */
	public String getManager_user_name() {
		return manager_user_name;
	}
	
	/**
	 * sets Manager user name
	 * @param manager_user_name Manager user name
	 */
	public void setManager_user_name(String manager_user_name) 
	{
		if(manager_user_name.contains("@npav.com"))
		{
			this.manager_user_name = manager_user_name;
		}
		else
		{
			manager_user_name += "@npav.com";
			this.manager_user_name = manager_user_name;
		}
	}
	
	/**
	 * @return Manager password
	 */
	public String getManager_password() {
		return manager_password;
	}
	
	/**
	 * sets Manager password
	 * @param manager_password Manager password
	 */
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	
	/**
	 * @return Manager type
	 */
	public String getManager_type() {
		return manager_type;
	}
	
	/**
	 * sets Manager type
	 * @param manager_type Manager type
	 */
	public void setManager_type(String manager_type) {
		this.manager_type = manager_type;
	}
}

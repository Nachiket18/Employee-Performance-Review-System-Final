package beans;

/**
 * Java Bean class for Incident Management entries
 * @author GreyHood
 *
 */
public class IncidentManagementBean 
{
	private int employee_code;
	private String backlog;
	private String no_of_breaches;
	private String defects;
	private String kdb_doc_prep;
	private String response_breach;
	private String status_update;
	private String review_doc;
	private String otp1;
	private String otp2;
	private String otp3;
	
	/**
	 * Default constructor for IncidentManagementBean class
	 */
	public IncidentManagementBean() 
	{
		
	}
	

	/**
	 * @return cryptographic key for incident_management_1 table
	 */
	public String getOtp1() {
		return otp1;
	}


	/**
	 * sets cryptographic key for incident_management_1 table
	 * @param otp1 cryptographic key for incident_management_1 table
	 */
	public void setOtp1(String otp1) {
		this.otp1 = otp1;
	}


	/**
	 * @return cryptographic key for incident_management_2 table
	 */
	public String getOtp2() {
		return otp2;
	}


	/**
	 * sets cryptographic key for incident_management_2 table
	 * @param otp2 cryptographic key for incident_management_2 table
	 */
	public void setOtp2(String otp2) {
		this.otp2 = otp2;
	}


	/**
	 * @return sets cryptographic key for incident_management_3 table
	 */
	public String getOtp3() {
		return otp3;
	}


	/**
	 * sets sets cryptographic key for incident_management_3 table
	 * @param otp3 sets cryptographic key for incident_management_3 table
	 */
	public void setOtp3(String otp3) {
		this.otp3 = otp3;
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
	 * @return Backlog
	 */
	public String getBacklog() {
		return backlog;
	}

	/**
	 * sets Backlog
	 * @param backlog Backlog
	 */
	public void setBacklog(String backlog) {
		this.backlog = backlog;
	}

	/**
	 * @return Number of breaches
	 */
	public String getNo_of_breaches() {
		return no_of_breaches;
	}

	/**
	 * sets Number of breaches
	 * @param no_of_breaches Number of breaches
	 */
	public void setNo_of_breaches(String no_of_breaches) {
		this.no_of_breaches = no_of_breaches;
	}

	/**
	 * @return Defects
	 */
	public String getDefects() {
		return defects;
	}

	/**
	 * sets Defects
	 * @param defects Defects
	 */
	public void setDefects(String defects) {
		this.defects = defects;
	}

	/**
	 * @return Kdb doc prep
	 */
	public String getKdb_doc_prep() {
		return kdb_doc_prep;  
	}

	/**
	 * sets Kdb doc prep
	 * @param kdb_doc_prep Kdb doc prep
	 */
	public void setKdb_doc_prep(String kdb_doc_prep) {
		this.kdb_doc_prep = kdb_doc_prep;
	}

	/**
	 * @return Response breach
	 */
	public String getResponse_breach() {
		return response_breach;
	}

	/**
	 * sets Response breach
	 * @param response_breach Response breach
	 */
	public void setResponse_breach(String response_breach) {
		this.response_breach = response_breach;
	}

	/**
	 * @return Status update
	 */
	public String getStatus_update() {
		return status_update;
	}

	/**
	 * sets Status update
	 * @param status_update Status update
	 */
	public void setStatus_update(String status_update) {
		this.status_update = status_update;
	}

	/**
	 * @return Review doc
	 */
	public String getReview_doc() {
		return review_doc;
	}

	/**
	 * sets Review doc
	 * @param review_doc Review doc
	 */
	public void setReview_doc(String review_doc) {
		this.review_doc = review_doc;
	}
}

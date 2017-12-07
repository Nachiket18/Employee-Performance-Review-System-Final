package dao;

import beans.ChangeManagementBean;
import beans.DomainManagementBean;
import beans.IncidentManagementBean;
import beans.ManagerBean;
import beans.ServiceDeliveryManagementBean;
import exceptions.CloudConnectionException;
import exceptions.EmployeeAlreadyRatedException;
import exceptions.EmployeeDataAlreadyBackedUpException;
import exceptions.EmployeeNotFoundException;
import exceptions.NoRecordsToBackupException;
import exceptions.NoRecordsToRetrieveException;
import exceptions.RatedEmployeeAlreadyRetrievedException;
import exceptions.WrongManagerCredentialsException;

/**
 * Interface for all database operations
 * @author GreyHood
 *
 */
public interface DaoInterface 
{
	public int getManagerNumber();
	public ManagerBean getManager(String manager_user_name, String manager_password) throws WrongManagerCredentialsException;
	public void createManager(ManagerBean mb);
	
	public void createIncidentManagementEntry(IncidentManagementBean imb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException;
	public IncidentManagementBean getIncidentMangementEntry(int employee_code);
	
	public void createChangeManagementEntry(ChangeManagementBean cmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException;
	public ChangeManagementBean getChangeMangementEntry(int employee_code);
	
	public void createDomainManagementEntry(DomainManagementBean dmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException;
	public DomainManagementBean getDomainMangementEntry(int employee_code);
	
	public void createServiceDeliveryManagementEntry(ServiceDeliveryManagementBean sdmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException;
	public ServiceDeliveryManagementBean getServiceDeliveryMangementEntry(int employee_code);
	
	public int createEmployeeRatingP3(int employee_code, String productivity, String onTimeDelivery, String utilization, String complianceToProcess, String selfDevelopment,  String finalRating, String otp) throws EmployeeAlreadyRatedException;
	public int createEmployeeRatingP4(int employee_code, String productivity, String onTimeDelivery, String utilization, String complianceToProcess, String selfDevelopment, String teamDevelopment, String customerSatisfaction,  String finalRating, String otp) throws EmployeeAlreadyRatedException;
	public int createEmployeeRatingP5(int employee_code, String productivity, String onTimeDelivery, String selfDevelopment, String teamDevelopment, String customerSatisfaction, String finalRating, String otp) throws EmployeeAlreadyRatedException;
	
	public int createEmployeeRatingP3ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException;
	public int createEmployeeRatingP4ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException;
	public int createEmployeeRatingP5ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException;
	public int retrieveCloudData(int employee_code) throws RatedEmployeeAlreadyRetrievedException, NoRecordsToRetrieveException;
	public int checkCloudRatingForNoRecords();
	
	public String retrieveEmployeeName(int employee_code);
	public int checkEmployeeInRatings(int employee_code);
	public int checkEmployee(int employee_code);
	public int checkEmployeeInCloudRatingsTable(int employee_code);
	public int checkEmployeeRatings();
	public int checkRatingsForNoRecords();
	
	public int checkEmployeeInIncidentManagement(int employee_code);
	public int checkEmployeeInChangeManagement(int employee_code);
	public int checkEmployeeInDomainManagement(int employee_code);
	public int checkEmployeeInServiceDeliveryManagement(int employee_code);
}
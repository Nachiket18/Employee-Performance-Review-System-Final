package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Session;

import beans.ChangeManagementBean;
import beans.DomainManagementBean;
import beans.IncidentManagementBean;
import beans.ManagerBean;
import beans.ServiceDeliveryManagementBean;
import connection_manager.ConnectionManager;
import exceptions.CloudConnectionException;
import exceptions.EmployeeAlreadyRatedException;
import exceptions.EmployeeDataAlreadyBackedUpException;
import exceptions.EmployeeNotFoundException;
import exceptions.NoRecordsToBackupException;
import exceptions.NoRecordsToRetrieveException;
import exceptions.RatedEmployeeAlreadyRetrievedException;
import exceptions.WrongManagerCredentialsException;
import security.Decryption;

/**
 * Concrete class implementing DaoInterface to provide definitions for the functions
 * @author GreyHood
 *
 */
public class DaoInterfaceImplementation implements DaoInterface
{
	Connection conn = ConnectionManager.getConn();
	PreparedStatement ps;
	ResultSet rs;
	int result;
	String otp;
	
	Decryption de = null;
		
	ServiceDeliveryManagementBean sdmb = new ServiceDeliveryManagementBean();
	ChangeManagementBean cmb = new ChangeManagementBean();
	IncidentManagementBean imb = new IncidentManagementBean();
	DomainManagementBean dmb = new DomainManagementBean();
	
	int lport=50005;
    String host="remotepcip";
    String rhost="127.0.0.1";
    int rport=50000;
    String user="remotepcusername";
    String password="remotepcpwd";        
    Session session= null;        
    JSch jsch = new JSch();
	
	@Override
	public ManagerBean getManager(String manager_user_name, String manager_password) throws WrongManagerCredentialsException 
	{
		int manager_number = 0;
		String manager_name = null;
		String manager_type = null;
		String otp = null;
		try 
		{
			ps = conn.prepareStatement("select * from managers where manager_user_name = ? and manager_password = ?");
			ps.setString(1, manager_user_name);
			ps.setString(2, manager_password);
			rs = ps.executeQuery();
			while(rs.next())
			{
				manager_number = rs.getInt(1);
				manager_name = rs.getString(2);
				manager_type = rs.getString(5);
				otp = rs.getString(6);
			}
			if(manager_number != 0)
			{
				ManagerBean mb = new ManagerBean(manager_number, manager_name, manager_user_name, manager_password, manager_type, otp);
				return mb;
			}
			else
			{
				throw new WrongManagerCredentialsException();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public int getManagerNumber() 
	{
		int manager_number = 0;
		try 
		{
			ps = conn.prepareStatement("select * from managers");
			rs = ps.executeQuery();
			while(rs.next())
			{
				manager_number = rs.getInt(1);
			}
			if(manager_number == 0)
			{
				return 1;
			}
			else
			{
				return (manager_number+1);
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void createManager(ManagerBean mb)
	{
		try 
		{
			ps = conn.prepareStatement("insert into managers values(?,?,?,?,?,?)");
			ps.setInt(1, mb.getManager_number());
			ps.setString(2, mb.getManager_name().toString());
			ps.setString(3, mb.getManager_user_name().toString());
			ps.setString(4, mb.getManager_password().toString());
			ps.setString(5, mb.getManager_type().toString());
			ps.setString(6, mb.getOtp().toString());
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void createIncidentManagementEntry(IncidentManagementBean imb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException 
	{
		if(imb.getEmployee_code() == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkEmployeeInIncidentManagement(imb.getEmployee_code());
		if(result == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try
		{
			ps = conn.prepareStatement("insert into incident_management_1 values(?,?,?,?,?,?)");
			ps.setInt(1, imb.getEmployee_code());
			ps.setString(2, imb.getBacklog());
			ps.setString(3, imb.getNo_of_breaches());
			ps.setString(4, imb.getDefects());
			ps.setString(5, imb.getKdb_doc_prep());
			ps.setString(6, imb.getOtp1());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into incident_management_2 values(?,?,?)");
			ps.setInt(1, imb.getEmployee_code());
			ps.setString(2, imb.getResponse_breach());
			ps.setString(3, imb.getOtp2());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into incident_management_3 values(?,?,?,?)");
			ps.setInt(1, imb.getEmployee_code());
			ps.setString(2, imb.getStatus_update());
			ps.setString(3, imb.getReview_doc());
			ps.setString(4, imb.getOtp3());
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createChangeManagementEntry(ChangeManagementBean cmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException
	{
		if(cmb.getEmployee_code() == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkEmployeeInChangeManagement(cmb.getEmployee_code());
		if(result == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try
		{
			ps = conn.prepareStatement("insert into change_management_1 values(?,?,?,?,?)");
			ps.setInt(1, cmb.getEmployee_code());
			ps.setString(2, cmb.getPfc_submission_in_time());
			ps.setString(3, cmb.getSchedule_variance());
			ps.setString(4, cmb.getOntime_delivery_of_documentation());
			ps.setString(5, cmb.getOtp1());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into change_management_2 values(?,?,?,?)");
			ps.setInt(1, cmb.getEmployee_code());
			ps.setString(2, cmb.getUat_defects());
			ps.setString(3, cmb.getEffort_variance());
			ps.setString(4, cmb.getOtp2());
			ps.executeUpdate();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createDomainManagementEntry(DomainManagementBean dmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException
	{
		if(dmb.getEmployee_code() == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkEmployeeInDomainManagement(dmb.getEmployee_code());
		if(result == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try
		{
			ps = conn.prepareStatement("insert into internal_compliance_1 values(?,?,?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getAdherence_to_shift_timing());
			ps.setString(3, dmb.getLeave_planning());
			ps.setString(4, dmb.getFollowing_meeting_etiquettes());
			ps.setString(5, dmb.getOtp1());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into internal_compliance_2 values(?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getProactive());
			ps.setString(3, dmb.getOtp2());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into internal_compliance_3 values(?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getResponsible_to_deliverables());
			ps.setString(3, dmb.getOtp3());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into self_development_1 values(?,?,?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getTraining_hours());
			ps.setString(3, dmb.getSkill_improvement());
			ps.setString(4, dmb.getItil_certification());
			ps.setString(5, dmb.getOtp4());			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into self_development_2 values(?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getIdle_time_management());
			ps.setString(3, dmb.getOtp5());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("insert into team_development values(?,?,?,?)");
			ps.setInt(1, dmb.getEmployee_code());
			ps.setString(2, dmb.getEfforts_in_development_of_team_members());
			ps.setString(3, dmb.getParticipation_in_organizational_initiatives());
			ps.setString(4, dmb.getOtp6());
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createServiceDeliveryManagementEntry(ServiceDeliveryManagementBean sdmb) throws EmployeeNotFoundException, EmployeeAlreadyRatedException
	{
		if(sdmb.getEmployee_code() == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkEmployeeInServiceDeliveryManagement(sdmb.getEmployee_code());
		if(result == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try
		{
			ps = conn.prepareStatement("insert into customer_focus values(?,?,?,?,?,?)");
			ps.setInt(1, sdmb.getEmployee_code());
			ps.setString(2, sdmb.getUnderstanding_customer_requirement());
			ps.setString(3, sdmb.getAr_and_service_improvement_suggestions());
			ps.setString(4, sdmb.getNumber_of_a3());
			ps.setString(5, sdmb.getInfluence_to_increase_higher_level_work());
			ps.setString(6, sdmb.getOtp());
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ChangeManagementBean getChangeMangementEntry(int employee_code)
	{
		try
		{
			ps = conn.prepareStatement("select * from change_management_1 JOIN change_management_2 ON(change_management_1.employee_code=change_management_2.employee_code) WHERE change_management_1.employee_code= ?");
			ps.setInt(1,employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				otp = rs.getString(5);
				cmb.setEmployee_code(employee_code);
				cmb.setPfc_submission_in_time((de.decrypt(rs.getString(2), otp)));
				cmb.setSchedule_variance(de.decrypt((rs.getString(3)),otp));
				cmb.setOntime_delivery_of_documentation(de.decrypt(rs.getString(4), otp));
				cmb.setOtp1(otp);
				
				otp = rs.getString(9);
				cmb.setUat_defects(de.decrypt(rs.getString(7), otp));
				cmb.setEffort_variance(de.decrypt(rs.getString(8), otp));
				cmb.setOtp2(otp);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cmb;
	}
	
	public IncidentManagementBean getIncidentMangementEntry(int employee_code)
	{
		try
		{
			ps = conn.prepareStatement("select * from incident_management_1 JOIN incident_management_2 ON(incident_management_1.employee_code=incident_management_2.employee_code) JOIN incident_management_3 ON(incident_management_2.employee_code=incident_management_3.employee_code) WHERE incident_management_1.employee_code =? ");
			ps.setInt(1,employee_code);
			rs = ps.executeQuery();			
			while(rs.next())
			{
				otp = rs.getString(6);
				imb.setEmployee_code(employee_code);
				imb.setBacklog(de.decrypt(rs.getString(2), otp));
				imb.setNo_of_breaches(de.decrypt(rs.getString(3), otp));
				imb.setDefects(de.decrypt(rs.getString(4), otp));
				imb.setKdb_doc_prep(de.decrypt(rs.getString(5), otp));
				imb.setOtp1(otp);
				
				otp = rs.getString(9);
				imb.setResponse_breach(de.decrypt(rs.getString(8), otp));
				imb.setOtp2(otp);
				
				otp = rs.getString(13);
				imb.setStatus_update(de.decrypt(rs.getString(11), otp));
				imb.setReview_doc(de.decrypt(rs.getString(12), otp));
				imb.setOtp3(otp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return imb;
	}
	public DomainManagementBean getDomainMangementEntry(int employee_code)
	{
		try
		{
			ps = conn.prepareStatement("select * from internal_compliance_1 JOIN internal_compliance_2 ON(internal_compliance_1.employee_code=internal_compliance_2.employee_code) JOIN internal_compliance_3 ON(internal_compliance_2.employee_code=internal_compliance_3.employee_code) JOIN self_development_1 ON(internal_compliance_3.employee_code=self_development_1.employee_code) JOIN self_development_2 ON(self_development_1.employee_Code=self_development_2.employee_code) JOIN team_development ON(self_development_2.employee_code=team_development.employee_code) where internal_compliance_1.employee_code = ?");
			ps.setInt(1,employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				otp = rs.getString(5);
				dmb.setEmployee_code(employee_code);
				dmb.setAdherence_to_shift_timing(de.decrypt(rs.getString(2), otp));
				dmb.setLeave_planning(de.decrypt(rs.getString(3), otp));
				dmb.setFollowing_meeting_etiquettes(de.decrypt(rs.getString(4), otp));
				dmb.setOtp1(otp);				

				otp = rs.getString(8);				
				dmb.setProactive(de.decrypt(rs.getString(7), otp));
				dmb.setOtp2(otp);

				otp = rs.getString(11);				
				dmb.setResponsible_to_deliverables(de.decrypt(rs.getString(10), otp));
				dmb.setOtp3(otp);
				
				otp = rs.getString(16);				
				dmb.setTraining_hours(de.decrypt(rs.getString(13), otp));
				dmb.setSkill_improvement(de.decrypt(rs.getString(14), otp));
				dmb.setItil_certification(de.decrypt(rs.getString(15), otp));
				dmb.setOtp4(otp);
				 				
				otp = rs.getString(19);		
				dmb.setIdle_time_management(de.decrypt(rs.getString(18), otp));
				dmb.setOtp5(otp);
 
				otp = rs.getString(23);				
				dmb.setEfforts_in_development_of_team_members(de.decrypt(rs.getString(21), otp));
				dmb.setParticipation_in_organizational_initiatives(de.decrypt(rs.getString(22), otp));
				dmb.setOtp6(otp);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return dmb;
	}
	public ServiceDeliveryManagementBean getServiceDeliveryMangementEntry(int employee_code)
	{
		try
		{
			ps = conn.prepareStatement("select * from customer_focus where employee_code = ?");
			ps.setInt(1,employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				otp = rs.getString(6);
				sdmb.setEmployee_code(employee_code);
				sdmb.setUnderstanding_customer_requirement(de.decrypt(rs.getString(2), otp));
				sdmb.setAr_and_service_improvement_suggestions(de.decrypt(rs.getString(3), otp));
				sdmb.setNumber_of_a3(de.decrypt(rs.getString(4), otp));
				sdmb.setInfluence_to_increase_higher_level_work(de.decrypt(rs.getString(5), otp));
				sdmb.setOtp(otp);
			}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return sdmb;
	}
	
	@Override
	public int createEmployeeRatingP3(int employee_code, String productivity, String onTimeDelivery, String utilization, String complianceToProcess, String selfDevelopment, String finalRating, String otp) throws EmployeeAlreadyRatedException
	{
		if(checkEmployeeInRatings(employee_code) == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try 
		{
			ps = conn.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(5, utilization);
			ps.setString(8, complianceToProcess);
			ps.setString(4, selfDevelopment);			
			ps.setString(6, "0");
			ps.setString(7, "0");			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}	

	@Override
	public int createEmployeeRatingP4(int employee_code, String productivity, String onTimeDelivery, String utilization, String complianceToProcess, String selfDevelopment, String teamDevelopment, String customerSatisfaction, String finalRating, String otp) throws EmployeeAlreadyRatedException 
	{
		if(checkEmployeeInRatings(employee_code) == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try 
		{
			ps = conn.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(5, utilization);
			ps.setString(8, complianceToProcess);
			ps.setString(4, selfDevelopment);			
			ps.setString(6, teamDevelopment);
			ps.setString(7, customerSatisfaction);			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createEmployeeRatingP5(int employee_code, String productivity, String onTimeDelivery, String selfDevelopment, String teamDevelopment, String customerSatisfaction, String finalRating, String otp) throws EmployeeAlreadyRatedException 
	{
		if(checkEmployeeInRatings(employee_code) == 1)
		{
			throw new EmployeeAlreadyRatedException();
		}
		
		try 
		{
			ps = conn.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(5, "0");
			ps.setString(8, "0");
			ps.setString(4, selfDevelopment);			
			ps.setString(6, teamDevelopment);
			ps.setString(7, customerSatisfaction);			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createEmployeeRatingP3ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException
	{		
		if(checkEmployee(employee_code) == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkRatingsForNoRecords();
		if(result == 1)
		{
			throw new NoRecordsToBackupException();
		}
		
		result = checkEmployeeInCloudRatingsTable(employee_code);
		if(result == 1)
		{
			throw new EmployeeDataAlreadyBackedUpException();
		}
		
		
		String productivity = null; String onTimeDelivery = null; String utilization = null; String complianceToProcess = null;
		String selfDevelopment = null; 	String teamDevelopment = null; String customerSatisfaction = null; String finalRating = null; 
		String otp = null;
		
		try 
		{
			ps = conn.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				productivity = rs.getString(2);
				onTimeDelivery = rs.getString(3);
				utilization = rs.getString(4);
				complianceToProcess = rs.getString(5);
				selfDevelopment = rs.getString(6);
				teamDevelopment = rs.getString(7);
				customerSatisfaction = rs.getString(8);
				finalRating = rs.getString(9);
				otp = rs.getString(10);
			}			
			
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        //int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(50005).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			
			ps = con.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(4, utilization);
			ps.setString(5, complianceToProcess);
			ps.setString(6, selfDevelopment);			
			ps.setString(7, teamDevelopment);
			ps.setString(8, customerSatisfaction);			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			result = ps.executeUpdate();
			
			session.delPortForwardingR(rhost, rport);
			session.disconnect();
			
			return 1;
		}
		catch (JSchException e)
		{
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int createEmployeeRatingP4ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException 
	{
		if(checkEmployee(employee_code) == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkRatingsForNoRecords();
		if(result == 1)
		{
			throw new NoRecordsToBackupException();
		}
		
		result = checkEmployeeInCloudRatingsTable(employee_code);
		if(result == 1)
		{
			throw new EmployeeDataAlreadyBackedUpException();
		}
		
		String productivity = null; String onTimeDelivery = null; String utilization = null; String complianceToProcess = null;
		String selfDevelopment = null; 	String teamDevelopment = null; String customerSatisfaction = null; String finalRating = null; 
		String otp = null;
		
		try 
		{
			ps = conn.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				productivity = rs.getString(2);
				onTimeDelivery = rs.getString(3);
				utilization = rs.getString(4);
				complianceToProcess = rs.getString(5);
				selfDevelopment = rs.getString(6);
				teamDevelopment = rs.getString(7);
				customerSatisfaction = rs.getString(8);
				finalRating = rs.getString(9);
				otp = rs.getString(10);
			}
			
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        //int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(50005).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			
			ps = con.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(4, utilization);
			ps.setString(5, complianceToProcess);
			ps.setString(6, selfDevelopment);			
			ps.setString(7, teamDevelopment);
			ps.setString(8, customerSatisfaction);			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			ps.executeUpdate();
			
			session.delPortForwardingR(rhost, rport);
			session.disconnect();
			
			return 1;
		}
		catch (JSchException e)
		{
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int createEmployeeRatingP5ForCloud(int employee_code) throws EmployeeDataAlreadyBackedUpException, EmployeeNotFoundException, NoRecordsToBackupException 
	{
		if(checkEmployee(employee_code) == 0)
		{
			throw new EmployeeNotFoundException();
		}
		
		result = checkRatingsForNoRecords();
		if(result == 1)
		{
			throw new NoRecordsToBackupException();
		}
		
		result = checkEmployeeInCloudRatingsTable(employee_code);
		if(result == 1)
		{
			throw new EmployeeDataAlreadyBackedUpException();
		}
		
		String productivity = null; String onTimeDelivery = null; String utilization = null; String complianceToProcess = null;
		String selfDevelopment = null; 	String teamDevelopment = null; String customerSatisfaction = null; String finalRating = null; 
		String otp = null;
		
		try 
		{
			ps = conn.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				productivity = rs.getString(2);
				onTimeDelivery = rs.getString(3);
				utilization = rs.getString(4);
				complianceToProcess = rs.getString(5);
				selfDevelopment = rs.getString(6);
				teamDevelopment = rs.getString(7);
				customerSatisfaction = rs.getString(8);
				finalRating = rs.getString(9);
				otp = rs.getString(10);
			}
				
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        //int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(50005).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			
			ps = con.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(4, utilization);
			ps.setString(5, complianceToProcess);
			ps.setString(6, selfDevelopment);			
			ps.setString(7, teamDevelopment);
			ps.setString(8, customerSatisfaction);			
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			ps.executeUpdate();
			
			session.delPortForwardingR(rhost, rport);
			session.disconnect();
						
			return 1;
		}
		catch (JSchException e)
		{
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int retrieveCloudData(int employee_code) throws RatedEmployeeAlreadyRetrievedException, NoRecordsToRetrieveException
	{	
		result = checkCloudRatingForNoRecords();
		if(result == 1)
		{
			throw new NoRecordsToRetrieveException();
		}
		
		result = checkEmployeeInRatings(employee_code);
		if(result == 1)
		{
			throw new RatedEmployeeAlreadyRetrievedException();
		}
		
		String productivity = null;	String onTimeDelivery = null; String utilization = null; String complianceToProcess = null;
		String selfDevelopment = null; String teamDevelopment = null; String customerSatisfaction = null; String finalRating = null;
		
		try 
		{
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        //int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(50005).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			
			ps = con.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				productivity = rs.getString(2);
				onTimeDelivery = rs.getString(3);
				utilization = rs.getString(4);
				complianceToProcess = rs.getString(5);
				selfDevelopment = rs.getString(6);
				teamDevelopment = rs.getString(7);				
				customerSatisfaction = rs.getString(8);
				finalRating = rs.getString(9);
				otp = rs.getString(10);
			}
			
			session.delPortForwardingR(rhost, rport);
			session.disconnect();
			
			ps = conn.prepareStatement("insert into ratings values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee_code);
			ps.setString(2, productivity);
			ps.setString(3, onTimeDelivery);
			ps.setString(4, utilization);
			ps.setString(5, complianceToProcess);
			ps.setString(6, selfDevelopment);
			ps.setString(7, teamDevelopment);
			ps.setString(8, customerSatisfaction);
			ps.setString(9, finalRating);
			ps.setString(10, otp);
			result = ps.executeUpdate();
			
			return 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} catch (JSchException e)
		{
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int checkEmployeeInRatings(int employee_code)
	{
		try 
		{
			ps = conn.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployee(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from employee where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployeeInIncidentManagement(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from incident_management_1 where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployeeInChangeManagement(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from change_management_1 where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployeeInDomainManagement(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from internal_compliance_1 where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployeeInServiceDeliveryManagement(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from customer_focus where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}
	
	@Override
	public int checkEmployeeInCloudRatingsTable(int employee_code) 
	{
		try 
		{
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(assinged_port).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			
			ps = con.prepareStatement("select * from ratings where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					session.delPortForwardingR(rhost, rport);
					session.disconnect();
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (JSchException e) 
		{
			
		} catch (ClassNotFoundException e) 
		{
			
		}		
		return 0;
	}

	@Override
	public int checkEmployeeRatings()
	{
		int countOfEmployeesFromRatingsTable = 0;
		int countOfEmployeesFromEmployeeTable = 0;
		
		try 
		{
			ps = conn.prepareStatement("select count(employee_code) from ratings");
			rs = ps.executeQuery();
			while(rs.next())
			{
				countOfEmployeesFromRatingsTable = rs.getInt(1);
			}
			
			ps = conn.prepareStatement("select count(employee_code) from employee");
			rs = ps.executeQuery();
			while(rs.next())
			{
				countOfEmployeesFromEmployeeTable = rs.getInt(1);
			}
			
			if(countOfEmployeesFromRatingsTable == countOfEmployeesFromEmployeeTable)
			{
				return 1;
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}

	@Override
	public String retrieveEmployeeName(int employee_code) 
	{
		try 
		{
			ps = conn.prepareStatement("select * from employee where employee_code = ?");
			ps.setInt(1, employee_code);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == employee_code)
				{
					return rs.getString(2);
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public int checkCloudRatingForNoRecords() 
	{
		try 
		{
			session=jsch.getSession(user, host, 22);
			session.setPassword(password);
	        session.setConfig("StrictHostKeyChecking","no");
	        session.connect();
	        int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        StringBuilder url = new StringBuilder("jdbc:monetdb://localhost:");	        
	        url.append(assinged_port).append ("/").append("mydata").append ("?user=").append("monetdb").append ("&password=").append ("monetdb");	// use assigned_port to establish database connection
	        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection(url.toString());
			ps = con.prepareStatement("select count(employee_code) from ratings");
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == 0)
				{
					session.delPortForwardingR(rhost, rport);
					session.disconnect();
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (JSchException e) 
		{
			
		} catch (ClassNotFoundException e) 
		{
			
		}		
		return 0;
	}

	@Override
	public int checkRatingsForNoRecords() 
	{
		try 
		{
			ps = conn.prepareStatement("select count(employee_code) from ratings");
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == 0)
				{
					return 1;
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return 0;
	}	
}
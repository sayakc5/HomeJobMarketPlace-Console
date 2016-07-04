package com.apostek.HomeJobMarketPlace.dao;

import com.apostek.HomeJobMarketPlace.entity.Application;
import com.apostek.HomeJobMarketPlace.entity.Job;
import com.apostek.HomeJobMarketPlace.entity.Seeker;
import com.apostek.HomeJobMarketPlace.entity.Sitter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataAcessUsingJdbcImpl implements DataAccessUsingJdbc {
	
	Connection conn = null;
    PreparedStatement pstmt = null;
    String query="";
	public static Connection getConnection() throws Exception {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost/db_demo";
	    String username = "root";
	    String password = "admin";
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }
	public int addSeekerDao(Seeker seeker) throws SQLException {
		
		try{
			conn=getConnection();
			query="insert into member(firstName,lastName,phoneNumber,email,address,type) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, seeker.getFristName());
			pstmt.setString(2,seeker.getLastName());
			pstmt.setInt(3, seeker.getPhoneNumber());
			pstmt.setString(4,seeker.getEmail());
			pstmt.setString(5,seeker.getAddress());
			pstmt.setString(6,seeker.getType());
			if(pstmt.executeUpdate()==1)
			{
			query="insert into seeker(id,numberOfChildren,spouseName)values(?,?,?)";
			pstmt=null;conn=null;conn=getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,getIdByEmail(seeker.getEmail()));
			pstmt.setInt(2, seeker.getNumberOfChildren());
			pstmt.setString(3,seeker.getSpouseName());
			return pstmt.executeUpdate();
			}
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return 0;
	}
	public int getIdByEmail(String email) throws SQLException{
		
		Connection connTemp=null;
		PreparedStatement pstmtTemp=null;
		try{
			connTemp=getConnection();
			query="select id from member where email=?";
			pstmtTemp=connTemp.prepareStatement(query);
			pstmtTemp.setString(1, email);
			ResultSet rs=pstmtTemp.executeQuery();
			while(rs.next())
			{
				return rs.getInt("id");
			}
		}
	catch (Exception e) {
	      e.printStackTrace();
    } finally {
      pstmtTemp.close();
      connTemp.close();
    }
	return 0;
		
	}
	
	public int addSitterDao(Sitter sitter) throws SQLException {
		try{
			conn=getConnection();
			query="insert into member(firstName,lastName,phoneNumber,email,address,type) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, sitter.getFristName());
			pstmt.setString(2,sitter.getLastName());
			pstmt.setInt(3, sitter.getPhoneNumber());
			pstmt.setString(4,sitter.getEmail());
			pstmt.setString(5,sitter.getAddress());
			pstmt.setString(6,sitter.getType());
			if(pstmt.executeUpdate()==1)
			{
			query="insert into sitter(id,experience,expectedPay)values(?,?,?)";
			pstmt=null;conn=null;conn=getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,getIdByEmail(sitter.getEmail()));
			pstmt.setInt(2, sitter.getExperience());
			pstmt.setInt(3,sitter.getExpectedPay());
			return pstmt.executeUpdate();
			}
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return 0;
	}
	
	public List<Seeker> getSeekersDao() throws SQLException {
		
		List<Seeker> seekers=new ArrayList<Seeker>();
		Seeker seeker;
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from member m join seeker s on m.id=s.id";
		pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			seeker=new Seeker();
			seeker.setId(rs.getInt("id"));
			seeker.setFristName(rs.getString("firstName"));
			seeker.setLastName(rs.getString("lastName"));
			seeker.setPhoneNumber(rs.getInt("phoneNumber"));
			seeker.setEmail(rs.getString("email"));
			seeker.setAddress(rs.getString("address"));
			seeker.setType(rs.getString("type"));
			seeker.setNumberOfChildren(rs.getInt("numberOfChildren"));
			seeker.setSpouseName(rs.getString("spouseName"));
			seekers.add(seeker);
		}
		return seekers;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return null;
	}
	public List<Sitter> getSittersDao() throws SQLException {
		List<Sitter> sitters=new ArrayList<Sitter>();
		Sitter sitter;
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from member m join sitter s on m.id=s.id";
		pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			sitter=new Sitter();
			sitter.setId(rs.getInt("id"));
			sitter.setFristName(rs.getString("firstName"));
			sitter.setLastName(rs.getString("lastName"));
			sitter.setPhoneNumber(rs.getInt("phoneNumber"));
			sitter.setEmail(rs.getString("email"));
			sitter.setAddress(rs.getString("address"));
			sitter.setType(rs.getString("type"));
			sitter.setExperience(rs.getInt("experience"));
			sitter.setExpectedPay(rs.getInt("expectedPay"));
			sitters.add(sitter);
		}
		return sitters;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return null;
	}
	public boolean isSeekerDao(int mid) throws SQLException{
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select type from member where id=?";
		pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, mid);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			if(rs.getString("type").equals("SEEKER"))
				return true;
			else
				return false;
		}
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return false;

	}
	@Override
	public int updateSeekerDao(Seeker seeker,int mid) throws SQLException {
		try{
			conn=getConnection();
			query="update member set firstName=?,lastName=?,phoneNumber=?,email=?,address=?,type=? where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, seeker.getFristName());
			pstmt.setString(2,seeker.getLastName());
			pstmt.setInt(3, seeker.getPhoneNumber());
			pstmt.setString(4,seeker.getEmail());
			pstmt.setString(5,seeker.getAddress());
			pstmt.setString(6,seeker.getType());
			pstmt.setInt(7, mid);
			if(pstmt.executeUpdate()==1)
			{
			query="update seeker set numberOfChildren=?,spouseName=? where id=?";
			pstmt=null;conn=null;conn=getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,seeker.getNumberOfChildren());
			pstmt.setString(2,seeker.getSpouseName());
			pstmt.setInt(3,mid);
			return pstmt.executeUpdate();
			}
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return 0;
	}
	@Override
	public int updateSitterDao(Sitter sitter,int mid) throws SQLException {
		try{
			conn=getConnection();
			query="update member set firstName=?,lastName=?,phoneNumber=?,email=?,address=?,type=? where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, sitter.getFristName());
			pstmt.setString(2,sitter.getLastName());
			pstmt.setInt(3, sitter.getPhoneNumber());
			pstmt.setString(4,sitter.getEmail());
			pstmt.setString(5,sitter.getAddress());
			pstmt.setString(6,sitter.getType());
			pstmt.setInt(7, mid);
			if(pstmt.executeUpdate()==1)
			{
			query="update sitter set experience=?,expectedPay=? where id=?";
			pstmt=null;conn=null;conn=getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,sitter.getExperience());
			pstmt.setInt(2, sitter.getExpectedPay());
			pstmt.setInt(3,mid);
			return pstmt.executeUpdate();
			}
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return 0;
	}
	@Override
	public Seeker getSeekerByEmailDao(String email) throws SQLException {
		
		Seeker seeker=new Seeker();
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from member m join seeker s on m.id=s.id where email=?";
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1,email);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			
			seeker.setId(rs.getInt("id"));
			seeker.setFristName(rs.getString("firstName"));
			seeker.setLastName(rs.getString("lastName"));
			seeker.setPhoneNumber(rs.getInt("phoneNumber"));
			seeker.setEmail(rs.getString("email"));
			seeker.setAddress(rs.getString("address"));
			seeker.setType(rs.getString("type"));
			seeker.setNumberOfChildren(rs.getInt("numberOfChildren"));
			seeker.setSpouseName(rs.getString("spouseName"));
			
		}
		return seeker;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return null;
	}
	@Override
	public Sitter getSitterByEmailDao(String email) throws SQLException {
		
		Sitter sitter=new Sitter();
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from member m join sitter s on m.id=s.id where email=?";
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1,email);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			sitter=new Sitter();
			sitter.setId(rs.getInt("id"));
			sitter.setFristName(rs.getString("firstName"));
			sitter.setLastName(rs.getString("lastName"));
			sitter.setPhoneNumber(rs.getInt("phoneNumber"));
			sitter.setEmail(rs.getString("email"));
			sitter.setAddress(rs.getString("address"));
			sitter.setType(rs.getString("type"));
			sitter.setExperience(rs.getInt("experience"));
			sitter.setExpectedPay(rs.getInt("expectedPay"));
		}
		return sitter;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return null;
	}
	@Override
	public int addJobDao(Job job) throws SQLException {
		try{
			conn=getConnection();
			query="insert into job(title,memberId,startDate,endDate,startTime,endTime,payPerHour) values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,job.getTitle());
			pstmt.setInt(2,job.getMemberId());
			pstmt.setString(3,job.getStartDate());
			pstmt.setString(4,job.getEndDate() );
			pstmt.setString(5, job.getStartTime());
			pstmt.setString(6,job.getEndTime());
			pstmt.setInt(7,job.getPayPerHour());
			return pstmt.executeUpdate();
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return 0;
	}
	@Override
	public List<Integer> getAllMemberIds() throws SQLException {
		try{
			conn=getConnection();
			List<Integer> memberIds=new ArrayList<Integer>();
			query="select id from member";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				memberIds.add(rs.getInt(1));
			}
			return memberIds;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return null;
	}
	@Override
	public List<Job> getAllJobsDao() throws SQLException {
		List<Job> jobs=new ArrayList<Job>();
		Job job;
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from job";
		pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			job=new Job();
			job.setId(rs.getInt("id"));
			job.setTitle(rs.getString("title"));
			job.setMemberId(rs.getInt("memberId"));
			job.setStartDate(rs.getString("startDate"));
			job.setEndDate(rs.getString("endDate"));
			job.setStartTime(rs.getString("startTime"));
			job.setEndTime(rs.getString("endTime"));
			job.setPayPerHour(rs.getInt("payPerHour"));
			jobs.add(job);
		}
		return jobs;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return null;
	}
	@Override
	public int addApplicationDao(Application application) throws SQLException {
		try{
			conn=getConnection();
			query="insert into application(jobId,memberId,expectedPay) values(?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, application.getJobId());
			pstmt.setInt(2, application.getMemberId());
			pstmt.setInt(3, application.getExpectedPay());
			return pstmt.executeUpdate();
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return 0;
	}
	@Override
	public List<Application> getAllApplicationDao() throws SQLException {
		List<Application> applications=new ArrayList<Application>();
		Application application;
		conn=null;
		pstmt=null;
		try{
		conn=getConnection();
		query="select * from application";
		pstmt=conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next())
		{
			application=new Application();
			application.setId(rs.getInt("id"));
			application.setJobId(rs.getInt("jobId"));
			application.setMemberId(rs.getInt("memberId"));
			application.setExpectedPay(rs.getInt("expectedPay"));
			applications.add(application);
		}
		return applications;
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		return null;
	}
	@Override
	public int deleteMemberByIdDao(int mid) throws SQLException {
		conn=null;
		pstmt=null;
		try{
			conn=getConnection();
			query="delete  from member where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,mid);
			return pstmt.executeUpdate();		
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return 0;
	}
	@Override
	public int getMemberIdByJobIdDao(int jobId) throws SQLException {
		conn=null;
		pstmt=null;
		try{
			conn=getConnection();
			query="select memberId from job where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,jobId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				return rs.getInt("memberId");
			}
			}catch (Exception e) {
			      e.printStackTrace();
		    } finally {
		      pstmt.close();
		      conn.close();
		    }
			
			return 0;
	}
	@Override
	public int deleteJobByIdDao(int jobId) throws SQLException {
		conn=null;
		pstmt=null;
		try{
			conn=getConnection();
			query="delete  from job where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,jobId);
			return pstmt.executeUpdate();		
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return 0;
	}
	@Override
	public int getMemberIdByApplicationIdDao(int applicationId) throws SQLException {
		conn=null;
		pstmt=null;
		try{
			conn=getConnection();
			query="select memberId from application where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,applicationId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				return rs.getInt("memberId");
			}
			}catch (Exception e) {
			      e.printStackTrace();
		    } finally {
		      pstmt.close();
		      conn.close();
		    }
			
			return 0;
	}
	@Override
	public int deleteApplicationByIdDao(int applicationId) throws SQLException {
		
		conn=null;
		pstmt=null;
		try{
			conn=getConnection();
			query="delete  from application where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,applicationId);
			return pstmt.executeUpdate();		
		}catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      pstmt.close();
	      conn.close();
	    }
		
		return 0;
	}
	}
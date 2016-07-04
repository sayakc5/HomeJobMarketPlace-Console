package com.apostek.HomeJobMarketPlace.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apostek.HomeJobMarketPlace.dao.DataAccessUsingJdbc;
import com.apostek.HomeJobMarketPlace.dao.DataAcessUsingJdbcImpl;
import com.apostek.HomeJobMarketPlace.entity.Application;
import com.apostek.HomeJobMarketPlace.entity.Job;
import com.apostek.HomeJobMarketPlace.entity.Seeker;
import com.apostek.HomeJobMarketPlace.entity.Sitter;

public class ServiceLayerImpl implements ServiceLayer {

	DataAccessUsingJdbc dao;
	public int addSeekerService(Seeker seeker) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.addSeekerDao(seeker);
	}
	
	public int addSitterService(Sitter sitter) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.addSitterDao(sitter);
	}

	@Override
	public List<Seeker> getSeekersService() throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getSeekersDao();
	}

	@Override
	public List<Sitter> getSittersService() throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getSittersDao();
	}

	public int updateSitterService(Sitter sitter,int mid)throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.updateSitterDao(sitter,mid);
		
	}

	public int updateSeekerService(Seeker seeker,int mid)throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.updateSeekerDao(seeker,mid);
	}

	public boolean isSeekerService(int mid)throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.isSeekerDao(mid);
	}

	@Override
	public Seeker getSeekerByEmailService(String email) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getSeekerByEmailDao(email);
	}

	@Override
	public Sitter getSitterByEmailService(String email) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getSitterByEmailDao(email);
	}

	@Override
	public int addJobService(Job job) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.addJobDao(job);
	}

	@Override
	public boolean isValidMember(int mid) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		List<Integer> memberIds=dao.getAllMemberIds();
		for(int m:memberIds)
		{
			if(m==mid)
				return true;
		}
		
		return false;
	}

	@Override
	public List<Job> getAllJobsService() throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getAllJobsDao();
	}

	@Override
	public int addApplicationService(Application application) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.addApplicationDao(application);
	}

	@Override
	public List<Application> getAllApplicationService() throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.getAllApplicationDao();
		
	}

	@Override
	public int deleteMemberByIdService(int mid) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.deleteMemberByIdDao(mid);
	}

	@Override
	public boolean isJobPosterService(int mid, int jobId) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		if(dao.getMemberIdByJobIdDao(jobId)==mid)
			return true;
		else return false;
	}

	@Override
	public int deleteJobByIdService(int jobId) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.deleteJobByIdDao(jobId);
	}

	@Override
	public boolean isApplierService(int applicationId, int mid) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		if(dao.getMemberIdByApplicationIdDao(applicationId)==mid)
			return true;
		else return false;
	}

	@Override
	public int deleteApplicationById(int applicationId) throws SQLException {
		dao=new DataAcessUsingJdbcImpl();
		return dao.deleteApplicationByIdDao(applicationId);
	}
	

}

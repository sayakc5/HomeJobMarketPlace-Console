package com.apostek.HomeJobMarketPlace.service;

import java.sql.SQLException;
import java.util.List;

import com.apostek.HomeJobMarketPlace.entity.Application;
import com.apostek.HomeJobMarketPlace.entity.Job;
import com.apostek.HomeJobMarketPlace.entity.Seeker;
import com.apostek.HomeJobMarketPlace.entity.Sitter;

public interface ServiceLayer {
	public int addSeekerService(Seeker seeker) throws SQLException;

	public int addSitterService(Sitter sitter) throws SQLException;
	public List<Seeker> getSeekersService() throws SQLException;
	public List<Sitter> getSittersService() throws SQLException;
	public Seeker getSeekerByEmailService(String email) throws SQLException;
	public Sitter getSitterByEmailService(String email) throws SQLException;
	public int updateSitterService(Sitter sitter,int mid)throws SQLException;
	public int updateSeekerService(Seeker seeker,int mid)throws SQLException;
	public boolean isSeekerService(int mid)throws SQLException;

	public int addJobService(Job job)throws SQLException;

	public boolean isValidMember(int mid)throws SQLException;
	public List<Job> getAllJobsService() throws SQLException;
	public int addApplicationService(Application application)throws SQLException;
	public List<Application> getAllApplicationService()throws SQLException;
	public int deleteMemberByIdService(int mid)throws SQLException;

	public boolean isJobPosterService(int mid, int jobId) throws SQLException;

	public int deleteJobByIdService(int jobId)throws SQLException;

	public boolean isApplierService(int applicationId, int mid)throws SQLException;

	public int deleteApplicationById(int applicationId)throws SQLException;
	
}

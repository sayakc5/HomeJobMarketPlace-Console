package com.apostek.HomeJobMarketPlace.dao;

import java.sql.SQLException;
import java.util.*;

import com.apostek.HomeJobMarketPlace.entity.Application;
import com.apostek.HomeJobMarketPlace.entity.Job;
import com.apostek.HomeJobMarketPlace.entity.Seeker;
import com.apostek.HomeJobMarketPlace.entity.Sitter;

public interface DataAccessUsingJdbc {
	public int addSeekerDao(Seeker seeker) throws SQLException;
	public int  getIdByEmail(String email) throws SQLException;
	public int addSitterDao(Sitter sitter)throws SQLException;
	public List<Seeker> getSeekersDao()throws SQLException;
	public List<Sitter> getSittersDao() throws SQLException;
	public Seeker getSeekerByEmailDao(String email) throws SQLException;
	public Sitter getSitterByEmailDao(String email) throws SQLException;
	public boolean isSeekerDao(int mid) throws SQLException;
	public int updateSeekerDao(Seeker seeker,int mid) throws SQLException;
	public int updateSitterDao(Sitter sitter,int mid) throws SQLException;
	public int addJobDao(Job job) throws SQLException;
	public List<Integer> getAllMemberIds()  throws SQLException;
	public List<Job> getAllJobsDao() throws SQLException;
	public int addApplicationDao(Application application)throws SQLException;
	public List<Application> getAllApplicationDao()throws SQLException;
	public int deleteMemberByIdDao(int mid)throws SQLException;
	public int getMemberIdByJobIdDao(int jobId)throws SQLException;
	public int deleteJobByIdDao(int jobId)throws SQLException;
	public int getMemberIdByApplicationIdDao(int applicationId)throws SQLException;
	public int deleteApplicationByIdDao(int applicationId)throws SQLException;
	
	
}

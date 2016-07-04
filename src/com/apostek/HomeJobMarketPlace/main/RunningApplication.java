package com.apostek.HomeJobMarketPlace.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

import com.apostek.HomeJobMarketPlace.entity.Application;
import com.apostek.HomeJobMarketPlace.entity.Job;
import com.apostek.HomeJobMarketPlace.entity.Seeker;
import com.apostek.HomeJobMarketPlace.entity.Sitter;
import com.apostek.HomeJobMarketPlace.service.ServiceLayer;
import com.apostek.HomeJobMarketPlace.service.ServiceLayerImpl;

public class RunningApplication {
	
	
	public static void main(String[] args) throws SQLException, IOException {
		int choice = 0;
		int menu=0;
		int mid=0;
		
		
		ServiceLayer servicelayer;
		do
		{
			/*Scanner sc=new Scanner(System.in);*/
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("....Welcome to HomeJobMarketPlace....");
			System.out.println("Press 1 to create Seeker");
			System.out.println("Press 2 to create Sitter");
			System.out.println("Press 3 to delete Member");
			System.out.println("Press 4 to update Member");
			System.out.println("Press 5 to list all Seekers");
			System.out.println("Press 6 to list all Sitters");
			System.out.println("Press 7 to search a member");
			System.out.println("Press 8 to list all jobs");
			System.out.println("Press 9 to list all applications");
			System.out.println("Press 10 to create Job");
			System.out.println("Press 11 to apply for a job");      //create an application
			System.out.println("Press 12 to delete a job");
			System.out.println("Press 13 to delete an applied job"); //delete an application
			menu=Integer.parseInt(br.readLine());
			switch(menu)
			{
			case 1:
				
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.addSeekerService(insertSeeker())==1)
					System.out.println("Successfully added...");
				break;
			case 2:
				
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.addSitterService(insertSitter())==1)
					System.out.println("Successfully added...");
				break;
			case 3:
				servicelayer=new ServiceLayerImpl();
				System.out.println("Please enter the member id:");
				mid=Integer.parseInt(br.readLine());
				if(servicelayer.deleteMemberByIdService(mid)==1)
					System.out.println("Successfully Deleted...");
				break;
			case 4:
				System.out.println("Please enter the member id:");
				 mid=Integer.parseInt(br.readLine());
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.isSeekerService(mid))
					servicelayer.updateSeekerService(insertSeeker(),mid);
				else
					servicelayer.updateSitterService(insertSitter(),mid);
					System.out.println("Successfully updated...");
				break;
			case 5:
				System.out.println("....Details of All Seekers In The System.....");
				servicelayer=new ServiceLayerImpl();
				for(Seeker s:servicelayer.getSeekersService())
				{
					seekerDetails(s);
				}
				break;
			case 6:
				System.out.println("....Details of All Sitters In The System.....");
				servicelayer=new ServiceLayerImpl();
				for(Sitter s:servicelayer.getSittersService())
				{
					sitterDetails(s);
				}
				break;
			case 7:
				System.out.println("Please enter EmailId:");
				String email=br.readLine();
				System.out.println("Please enter the member id:");
				int memberid=Integer.parseInt(br.readLine());
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.isSeekerService(memberid))
				seekerDetails(servicelayer.getSeekerByEmailService(email));
				else
					sitterDetails(servicelayer.getSitterByEmailService(email));
				break;
			case 8:
				System.out.println("....Details of All Jobs In The System.....");
				servicelayer=new ServiceLayerImpl();
				for(Job j:servicelayer.getAllJobsService())
				{
					jobDetails(j);
				}
				break;
			case 9:
				System.out.println("Details of All the Applications In The System...");
				servicelayer=new ServiceLayerImpl();
				for(Application app:servicelayer.getAllApplicationService())
				{
					applicationDetails(app);
				}
				break;
			case 10:
				System.out.println("Please enter a valid member id:");
				mid=Integer.parseInt(br.readLine());
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.isValidMember(mid))
					{
					if(servicelayer.isSeekerService(mid))
					{
					if(servicelayer.addJobService(insertJob(mid))==1)
							System.out.println("Successfully inserted...");
					}
					else
						System.out.println("Sitters are not allowed to post a job....");
					}
				else
					System.out.println("Invalid Member Id Please Check Again!");
				break;
			case 11:
				System.out.println("Please enter a valid member id:");
				mid=Integer.parseInt(br.readLine());
				servicelayer=new ServiceLayerImpl();
				if(servicelayer.isValidMember(mid))
				{
					if(!servicelayer.isSeekerService(mid))
					{
						if(servicelayer.addApplicationService(insertApplication(mid))==1)
							System.out.println("Successfully added...");
					}
					else
						System.out.println("Seekers are not allowed to apply for a job!!");
				}
				else
					System.out.println("Invalid Member Id Please Check Again!");
				break;
			case 12:
				int jobId;
				servicelayer=new ServiceLayerImpl();
				System.out.println("Please enter a valid member id:");
				mid=Integer.parseInt(br.readLine());
				if(servicelayer.isValidMember(mid))
				{
					if(servicelayer.isSeekerService(mid))
					{
						System.out.println("Please enter a valid job id:");
						jobId=Integer.parseInt(br.readLine());
						if(servicelayer.isJobPosterService(mid,jobId))
						{
							if(servicelayer.deleteJobByIdService(jobId)==1)
								System.out.println("Successfully deleted");
						}
						else
							System.out.println("You have not posted this job so you cannot delete this job.Try deleting jobs posted by you!!!");
						}
					else
						System.out.println("Sitters are not allowed to delete any jobs");
				}
				else
					System.out.println("Invalid Member Id Please Check Again!");
				break;
			case 13:
			int applicationId;
			servicelayer=new ServiceLayerImpl();
			System.out.println("Please enter an application id:");
			applicationId=Integer.parseInt(br.readLine());
			System.out.println("Please enter a valid member id:");
			mid=Integer.parseInt(br.readLine());
			if(servicelayer.isValidMember(mid))
			{
				if(!servicelayer.isSeekerService(mid))
				{
				if(servicelayer.isApplierService(applicationId,mid))
				{
					if(servicelayer.deleteApplicationById(applicationId)==1)
						System.out.println("Successfully deleted....");
				}
				else
					System.out.println("Sorry You have not applied for this job!!");
			}
				else
					System.out.println("Seekers are not allowed to delete any jobs");
			}
			else
				System.out.println("Invalid Member Id Please Check Again!");
			break;
			default:
				break;
			}
			System.out.println("Press 1 to continue  else press any other digit to terminate...");
			choice=Integer.parseInt(br.readLine());
		}while(choice==1);


	}
	private static void applicationDetails(Application app) {
		System.out.println("Application Id:"+app.getId());
		System.out.println("Job Id:"+app.getJobId());
		System.out.println("Member Id:"+app.getMemberId());
		System.out.println("Expected Pay"+app.getExpectedPay());
		System.out.println("------------------------------------------");
		
	}
	private static void jobDetails(Job j) {
		System.out.println("Job Id:"+j.getId());
		System.out.println("Job Title"+j.getTitle());
		System.out.println("Job Start Date:"+j.getStartDate());
		System.out.println("Job End Date:"+j.getEndDate());
		System.out.println("Job Start Time:"+j.getStartTime());
		System.out.println("Job End Time"+j.getEndTime());
		System.out.println("Pay Per Hour:"+j.getPayPerHour());
		System.out.println("------------------------------------------");
		
	}
	public static Seeker insertSeeker()
	{
		Scanner sc=new Scanner(System.in);
		final String SEEKER="SEEKER";
		Seeker seeker=new Seeker();
		System.out.println("Please enter all the details of Seeker:");
		System.out.println("Enter First Name:");
		seeker.setFristName(sc.nextLine());
		System.out.println("Enter Last Name:");
		seeker.setLastName(sc.nextLine());
		System.out.println("Enter Phone Number:");
		seeker.setPhoneNumber(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Email:");
		seeker.setEmail(sc.nextLine());
		System.out.println("Enter Address:");
		seeker.setAddress(sc.nextLine());
		seeker.setType(SEEKER);
		System.out.println("Enter Spouse Name:");
		seeker.setSpouseName(sc.nextLine());
		System.out.println("Enter Number of Children");
		seeker.setNumberOfChildren(sc.nextInt());
		sc.nextLine();
		return seeker;
	}
	public static Sitter insertSitter(){
		Scanner sc=new Scanner(System.in);
		final String SITTER="SITTER";
		Sitter sitter=new Sitter();
		System.out.println("Please enter all the details of Sitter:");
		System.out.println("Enter First Name:");
		sitter.setFristName(sc.nextLine());
		System.out.println("Enter Last Name:");
		sitter.setLastName(sc.nextLine());
		System.out.println("Enter Phone Number:");
		sitter.setPhoneNumber(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Email:");
		sitter.setEmail(sc.nextLine());
		System.out.println("Enter Address:");
		sitter.setAddress(sc.nextLine());
		sitter.setType(SITTER);
		System.out.println("Enter Experience in years");
		sitter.setExperience(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Expectd Pay");
		sitter.setExpectedPay(sc.nextInt());
		sc.nextLine();
		return sitter;
		
	}
	public static Job insertJob(int mid)
	{
		Scanner sc=new Scanner(System.in);
		Job job=new Job();
		System.out.println("Please enter the details of the Job:");
		System.out.println("Job Title:");
		job.setTitle(sc.nextLine());
		job.setMemberId(mid);
		System.out.println("Start Date in YYYY-MM-DD format:");
		job.setStartDate(sc.nextLine());
		System.out.println("End Date in YYYY-MM-DD format:");
		job.setEndDate(sc.nextLine());
		System.out.println("Start Time in HH:MM:SS format");
		String stime=sc.nextLine();
		job.setStartTime(stime);
		System.out.println("End Time in HH:MM:SS format:");
		String etime=sc.nextLine();
		job.setEndTime(etime);
		System.out.println("Pay Per Hour:");
		job.setPayPerHour(sc.nextInt());
		return job;
	}
	public static Application insertApplication(int mid)
	{
		Scanner sc=new Scanner(System.in);
		Application application=new Application();
		System.out.println("Please enter the details of your Application:");
		System.out.println("Job Id:");
		application.setJobId(sc.nextInt());
		sc.nextLine();
		System.out.println("Member Id:");
		application.setMemberId(sc.nextInt());
		sc.nextLine();
		System.out.println("Expected Pay:");
		application.setExpectedPay(sc.nextInt());
		return application;
	}
	public static void seekerDetails(Seeker s)
	{
		
		System.out.println("Member Id:"+s.getId());
		System.out.println("First Name:"+s.getFristName());
		System.out.println("Last Name:"+s.getLastName());
		System.out.println("Phone Number:"+s.getPhoneNumber());
		System.out.println("Email Address:"+s.getEmail());
		System.out.println("Address:"+s.getAddress());
		System.out.println("Member Type:"+s.getType());
		System.out.println("Number Of Children:"+s.getNumberOfChildren());
		System.out.println("Spouse Name:"+s.getSpouseName());
		System.out.println("-----------------------------------------------");
	}
	public static void sitterDetails(Sitter s)
	{
		System.out.println("Member Id:"+s.getId());
		System.out.println("First Name:"+s.getFristName());
		System.out.println("Last Name:"+s.getLastName());
		System.out.println("Phone Number:"+s.getPhoneNumber());
		System.out.println("Email Address:"+s.getEmail());
		System.out.println("Address:"+s.getAddress());
		System.out.println("Member Type:"+s.getType());
		System.out.println("Experience in years:"+s.getExperience());
		System.out.println("Expected Pay:Rs."+s.getExpectedPay());
		System.out.println("-----------------------------------------------");
	}

}

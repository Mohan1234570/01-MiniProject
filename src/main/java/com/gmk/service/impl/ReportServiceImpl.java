package com.gmk.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.gmk.entities.CitizenPlans;
import com.gmk.repositories.CitizenPlanRepository;
import com.gmk.request.SearchRequest;
import com.gmk.service.ReportService;
import com.gmk.utils.EmailUtils;
import com.gmk.utils.ExcelGenerator;
import com.gmk.utils.PDFGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
    private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<CitizenPlans> fetchPlans(SearchRequest request) {

		CitizenPlans c1 = new CitizenPlans();
		
		if(null != request.getPlanName() && !"".equals(request.getPlanName())) {
			c1.setPlanName(request.getPlanName());
		}
		
		if(null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			c1.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender() && !"".equals(request.getGender())) {
			c1.setCitizenGender(request.getGender());
		}
		
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String date = request.getStartDate();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
			c1.setPlanStartDate(localDate);
		}
		
		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String date = request.getEndDate();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
			c1.setPlanEndDate(localDate);
		}
		
		Example<CitizenPlans> exmp = Example.of(c1);
		List<CitizenPlans> plans = repo.findAll(exmp);
		return plans;
	}

	@Override
	public List<String> fetchPlanNames() {
		List<String> planNames = repo.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> fetchPlanStatus() {
		List<String> statusNames = repo.getStatusNames();
		return statusNames;
	}

	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("Plans.xls");
		
		List<CitizenPlans> plans = repo.findAll();
		excelGenerator.generate(response, plans,f);
		
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "mk4400320@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
	}
	@Override
	public boolean exportPDF(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.pdf");
		List<CitizenPlans> plans = repo.findAll();
		
		pdfGenerator.generate(response, plans, f);
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "mk4400320@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		
		return true;
	}

	}



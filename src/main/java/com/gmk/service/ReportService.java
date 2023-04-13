package com.gmk.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.gmk.entities.CitizenPlans;
import com.gmk.request.SearchRequest;

public interface ReportService {
	
	List<CitizenPlans> fetchPlans(SearchRequest request);
	
	List<String> fetchPlanNames();
	
	List<String> fetchPlanStatus();
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPDF(HttpServletResponse response) throws Exception;

}

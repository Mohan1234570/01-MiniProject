package com.gmk.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gmk.entities.CitizenPlans;
import com.gmk.request.SearchRequest;
import com.gmk.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachement;filename=plans.pdf");
		service.exportPDF(response);
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachement;filename=plans.xls");
		service.exportExcel(response);
	}
	
	@GetMapping("/")
	public String loadData(Model model) {
		model.addAttribute("search",new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("plannames",service.fetchPlanNames());
		model.addAttribute("status", service.fetchPlanStatus());
	}
	
	@PostMapping("/plan")
	public String handleSearch(@ModelAttribute("search")SearchRequest search ,Model model) {
		List<CitizenPlans> plans = service.fetchPlans(search);
		init(model);
		model.addAttribute("plans", plans);
		return "index";
	}
}

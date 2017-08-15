package com.crowdfunding.sjtu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowdfunding.sjtu.model.Audit;
import com.crowdfunding.sjtu.service.IAuditService;

@Controller
public class AuditController {
	@Autowired
	private IAuditService auditservice;
	
	@RequestMapping("/audit_project")
	public String getAuditProjectList(ModelMap modelmap){
		List<Audit> audits = auditservice.getAuditsByStatus(0); // 0 means does not handled
		modelmap.addAttribute("audits", audits);
		return "audit/audit_project_list";
	}
}

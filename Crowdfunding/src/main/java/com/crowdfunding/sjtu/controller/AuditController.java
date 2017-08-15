package com.crowdfunding.sjtu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowdfunding.sjtu.model.Audit;
import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.service.IAuditService;
import com.crowdfunding.sjtu.service.IProjectService;

@Controller
public class AuditController {
	@Autowired
	private IAuditService auditservice;
	@Autowired
	private IProjectService projectservice;
	
	@RequestMapping("/audit_project")
	public String getAuditProjectList(ModelMap modelmap){
		List<Audit> audits = auditservice.getAuditsByStatus(0); // 0 means does not handled
		modelmap.addAttribute("audits", audits);
		return "audit/audit_project_list";
	}
	@RequestMapping("/audit_one_project")
	public String auditOne(Audit audit,String action){   // 审核单个项目通过还是不通过
		System.out.println(audit.getAuditId()); //debug use only
		// update the audit table's setting based on what the user submit
		// update the project table's setting based on what the user submit
		System.out.println("The project Id get from audit page is " + audit.getProjectId() + ",now get project object from DB.");
		Project project = projectservice.getProjectById(audit.getProjectId());
		if (null == project){
			System.out.println("Can't get project from DB. Audit fail");
			return "audit/audit_failure";
		}
		if (action.equals("audit_pass")){
			project.setStatus(2);
			audit.setStatus(1);
			auditservice.SaveOrUpdateAudit(audit);
			projectservice.saveorUpdate(project);
			return "audit/audit_project_success";
		} else if (action.equals("audit_fail")) {
			project.setStatus(0);
			audit.setStatus(1);
			auditservice.SaveOrUpdateAudit(audit);
			projectservice.saveorUpdate(project);
			return "audit/audit_project_failure";    // 是不是应该显示另外一个消息？而不是这个页面？			
		}
		return null;
	}
}


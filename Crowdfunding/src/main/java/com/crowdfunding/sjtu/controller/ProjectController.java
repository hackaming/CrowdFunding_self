package com.crowdfunding.sjtu.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunding.sjtu.dao.IProjectDao;
import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class ProjectController {
	//
	@Autowired
	private IProjectDao projectDao;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IDateService dateService;
	
	@RequestMapping(value="/projectcreate")
	public String createProject(){
		return "project/project_create";
	}
	
	@RequestMapping(value="/project/create", method=RequestMethod.POST)
	public String crtProject(HttpServletRequest req){
		//
		Project project = new Project();
		project.setComment(req.getParameter("comment"));
		project.setCreateDateTime(dateService.getFullDate());
		project.setDeadDate(req.getParameter("deadDate"));
		project.setDescription(req.getParameter("description"));
		project.setImageName(req.getParameter("imagename"));
		project.setLocation(req.getParameter("location"));
		project.setPrice(req.getParameter("price"));
		project.setProjectName(req.getParameter("projectName"));
		project.setStatus(0);
		projectService.saveProject(project);
		//
		if (null != projectService.getProjectByName(req.getParameter("projectName"))){
			return "project/create_success";
		} else {
		return  "project/create_failure";
		}
	}
	
	@RequestMapping("/projectlist")
	public String getProjectList(ModelMap model){
		//
		//List<Project> projectlist = projectService.getProjectList();
		List<Project> projectlist = projectService.getProjectListByStatus(2); //status 1 means the project's been audited
		model.addAttribute("projectlist", projectlist);
		return "project/project_list";
	}
	//
	@RequestMapping(value="/project/projectdetail/{projectId}", method=RequestMethod.GET)
	public String getProjectlst(@PathVariable(value="projectId") int projectId,ModelMap modelmap){
		Project project = projectService.getProjectById(projectId);
		modelmap.addAttribute("project", project);
		return "project/project_detail";
	}
}

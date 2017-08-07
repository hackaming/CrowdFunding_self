package com.crowdfunding.sjtu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunding.sjtu.dao.IProjectDao;
import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class ProjectController {
	//包括项目有关的东西,先把项目有关的增,删,改,查的DAO部分弄完吧,表之类的.
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
		//接收参数
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
		//调用service存入db，如果成功则返回成功，否则就是失败
		if (null != projectService.getProjectByName(req.getParameter("projectName"))){
			return "project/create_success";
		} else {
		return  "project/create_failure";
		}
	}

}

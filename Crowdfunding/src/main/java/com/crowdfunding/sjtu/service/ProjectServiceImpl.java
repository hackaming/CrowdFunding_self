package com.crowdfunding.sjtu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdfunding.sjtu.dao.IProjectDao;
import com.crowdfunding.sjtu.model.Project;
@Transactional
@Service
public class ProjectServiceImpl implements IProjectService{
	
	@Autowired
	private IProjectDao projectdao;

	@Override
	public Project getProjectById(Integer projectId) {
		// TODO Auto-generated method stub
		return projectdao.getProjectById(projectId);
	}

	@Override
	public List<Project> getProjectList() {
		// TODO Auto-generated method stub
		return projectdao.getProjectList();
	}

	@Override
	public void saveProject(Project project) {
		// TODO Auto-generated method stub
		projectdao.saveProject(project);
	}

	@Override
	public void saveorUpdate(Project project) {
		// TODO Auto-generated method stub
		projectdao.saveorUpdate(project);
	}

	@Override
	public Project getProjectByName(String projectName) {
		// TODO Auto-generated method stub
		return projectdao.getProjectByName(projectName);
	}

	@Override
	public void deleteProject(Integer projectId) {
		// TODO Auto-generated method stub
		projectdao.deleteProject(projectId);
	}

	@Override
	public List<Project> getProjectListByStatus(int status) {
		// TODO Auto-generated method stub
		return projectdao.getProjectListByStatus(status);
	}

}

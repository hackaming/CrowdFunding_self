package com.crowdfunding.sjtu.service;

import java.util.List;

import com.crowdfunding.sjtu.model.Project;

public interface IProjectService {
	public Project getProjectById(Integer projectId);
	public List<Project> getProjectList();
	public void saveProject(Project project);
	public void saveorUpdate(Project project);
	public Project getProjectByName(String projectName);
	public void deleteProject(Integer projectId);
}

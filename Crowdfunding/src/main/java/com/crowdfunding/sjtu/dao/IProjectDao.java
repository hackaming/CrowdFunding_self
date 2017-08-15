package com.crowdfunding.sjtu.dao;

import java.util.ArrayList;
import java.util.List;

import com.crowdfunding.sjtu.model.Project;

public interface IProjectDao {
	public Project getProjectById(Integer projectId);
	public List<Project> getProjectList();
	public void saveProject(Project project);
	public void saveorUpdate(Project project);
	public Project getProjectByName(String projectName);
	public void deleteProject(Integer projectId);
	public List<Project> getProjectListByStatus(int status);
}

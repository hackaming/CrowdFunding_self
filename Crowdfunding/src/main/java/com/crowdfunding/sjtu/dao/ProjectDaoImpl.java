package com.crowdfunding.sjtu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crowdfunding.sjtu.model.Project;

@Repository
public class ProjectDaoImpl implements IProjectDao {
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Project getProjectById(Integer projectId) {
		// TODO Auto-generated method stub
		Query query = sessionfactory.getCurrentSession().createQuery("from project p where p.projectId =:projectId");
		query.setInteger("projectId", projectId);
		List<Project> projects = (List<Project>) query.list();
		
		if (projects.size() > 0) {
			return projects.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Project> getProjectList() {
		// TODO Auto-generated method stub
		
		return sessionfactory.getCurrentSession().createQuery("from project").list();
	}

	@Override
	public void saveProject(Project project) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().save(project);		
	}

	@Override
	public void saveorUpdate(Project project) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().saveOrUpdate(project);
	}

	@Override
	public Project getProjectByName(String projectName) {
		Query query = sessionfactory.getCurrentSession().createQuery("from project p where p.projectName =:projectName");
		query.setString("projectName", projectName);
		List<Project> projects = (List<Project>) query.list();
		if (projects.size()>0){
			return projects.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteProject(Integer projectId) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().delete(projectId);

	}

}

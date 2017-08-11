package com.crowdfunding.sjtu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crowdfunding.sjtu.model.JournalTractional;

@Repository
public class JournalTractionalDaoImpl implements IJournalTractionalDao {
	@Autowired
	private SessionFactory sessionfactory;

	public void save(JournalTractional tr) {
		sessionfactory.getCurrentSession().save(tr);
	}

	public void delete(JournalTractional tr) {
		sessionfactory.getCurrentSession().delete(tr);
	}

	public JournalTractional getJournalTractionalById(int id) {
		return null;
	}

	public void saveorupdate(JournalTractional tr) {
		sessionfactory.getCurrentSession().saveOrUpdate(tr);
	}

	@Override
	public void update(JournalTractional tr) {
		sessionfactory.getCurrentSession().update(tr);

	}

	@Override
	public List<JournalTractional> getJournalTractionals() {
		Query query = sessionfactory.getCurrentSession().createQuery("from JournalTractional");
		List<JournalTractional> list = query.list();
		return list;
	}
}

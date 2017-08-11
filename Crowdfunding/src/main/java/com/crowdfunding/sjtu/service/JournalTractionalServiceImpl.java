package com.crowdfunding.sjtu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.dao.IJournalTractionalDao;
import com.crowdfunding.sjtu.model.JournalTractional;

@Service
public class JournalTractionalServiceImpl implements IJournalTractionalService {
	@Autowired
	private IJournalTractionalDao journalTractionalDao;

	@Override
	public void save(JournalTractional tr) {
		// TODO Auto-generated method stub
		journalTractionalDao.save(tr);
	}

	@Override
	public void delete(JournalTractional tr) {
		// TODO Auto-generated method stub
		journalTractionalDao.delete(tr);
	}

	@Override
	public void update(JournalTractional tr) {
		// TODO Auto-generated method stub
		journalTractionalDao.update(tr);
	}

	@Override
	public JournalTractional getJournalTractionalById(int id) {
		// TODO Auto-generated method stub
		return journalTractionalDao.getJournalTractionalById(id);
	}

	@Override
	public void saveorupdate(JournalTractional tr) {
		// TODO Auto-generated method stub
		journalTractionalDao.saveorupdate(tr);
	}

	@Override
	public List<JournalTractional> getJournalTractionals() {
		// TODO Auto-generated method stub
		return journalTractionalDao.getJournalTractionals();
	}

}

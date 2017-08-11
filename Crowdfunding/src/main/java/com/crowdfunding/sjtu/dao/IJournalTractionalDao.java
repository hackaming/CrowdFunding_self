package com.crowdfunding.sjtu.dao;

import java.util.List;

import com.crowdfunding.sjtu.model.JournalTractional;

public interface IJournalTractionalDao {
	//增册改查
	public void save(JournalTractional tr);
	public void delete(JournalTractional tr);
	public void update(JournalTractional tr);
	public JournalTractional getJournalTractionalById(int id);
	public void saveorupdate(JournalTractional tr);
	public List<JournalTractional> getJournalTractionals();
}

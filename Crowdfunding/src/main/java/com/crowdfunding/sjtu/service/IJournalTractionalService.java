package com.crowdfunding.sjtu.service;

import java.util.List;

import com.crowdfunding.sjtu.model.JournalTractional;

public interface IJournalTractionalService {

	public void save(JournalTractional tr);
	public void delete(JournalTractional tr);
	public void update(JournalTractional tr);
	public JournalTractional getJournalTractionalById(int id);
	public void saveorupdate(JournalTractional tr);
	public List<JournalTractional> getJournalTractionals();
}

package com.crowdfunding.sjtu.service;

import java.util.List;

import com.crowdfunding.sjtu.model.Audit;

public interface IAuditService {
	public List<Audit> getAudits();
	public void deleteAuditItem(Audit audit);
	public void saveAudit(Audit audit);
	public void SaveOrUpdateAudit(Audit audit);
	public Audit findById(int auditId);
}

package com.crowdfunding.sjtu.dao;

import java.util.List;

import com.crowdfunding.sjtu.model.Audit;

public interface IAuditDao {
	public List<Audit> getAudits();
	public void deleteAuditItem(Audit audit);
	public void saveAudit(Audit audit);
	public void SaveOrUpdateAudit(Audit audit);
	public Audit findById(int auditId);
	public List<Audit> getAuditsByStatus(int status);

}

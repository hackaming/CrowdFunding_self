package com.crowdfunding.sjtu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.dao.IAuditDao;
import com.crowdfunding.sjtu.model.Audit;

@Service
public class AuditServiceImpl implements IAuditService {

	@Autowired
	private IAuditDao auditdao;

	@Override
	public List<Audit> getAudits() {
		// TODO Auto-generated method stub
		return auditdao.getAudits();
	}

	@Override
	public void deleteAuditItem(Audit audit) {
		// TODO Auto-generated method stub
		auditdao.deleteAuditItem(audit);
	}

	@Override
	public void saveAudit(Audit audit) {
		// TODO Auto-generated method stub
		auditdao.saveAudit(audit);
	}

	@Override
	public void SaveOrUpdateAudit(Audit audit) {
		// TODO Auto-generated method stub
		auditdao.SaveOrUpdateAudit(audit);
	}

	@Override
	public Audit findById(int auditId) {
		// TODO Auto-generated method stub
		return auditdao.findById(auditId);
	}

<<<<<<< HEAD
	@Override
	public List<Audit> getAuditsByStatus(int status) {
		// TODO Auto-generated method stub
		return auditdao.getAuditsByStatus(status);
	}

=======
>>>>>>> f9ac4843a4f79b74a9f53c04b74cbd9d5705ddae
}

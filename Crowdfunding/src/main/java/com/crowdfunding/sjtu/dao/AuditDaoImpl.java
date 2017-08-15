package com.crowdfunding.sjtu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crowdfunding.sjtu.model.Audit;

@Repository
public class AuditDaoImpl implements IAuditDao {

	@Autowired
	private SessionFactory sessionfactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> getAudits() {
		// TODO Auto-generated method stub
		return sessionfactory.getCurrentSession().createQuery("from Audit").list();
	}

	@Override
	public void deleteAuditItem(Audit audit) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().delete(audit);
	}

	@Override
	public void saveAudit(Audit audit) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().save(audit);

	}

	@Override
	public void SaveOrUpdateAudit(Audit audit) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().saveOrUpdate(audit);
	}

	@Override
	public Audit findById(int auditId) {
		// TODO Auto-generated method stub
		Query query = sessionfactory.getCurrentSession().createQuery("from Audit a where a.auditId=:auditId");
		query.setInteger("auditId", auditId);
		List<Audit> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	@Override
	public List<Audit> getAuditsByStatus(int status) {
		// TODO Auto-generated method stub
		Query query = sessionfactory.getCurrentSession().createQuery("from Audit a where a.status=:status");
		query.setInteger("status", status);
		return query.list();
	}
}

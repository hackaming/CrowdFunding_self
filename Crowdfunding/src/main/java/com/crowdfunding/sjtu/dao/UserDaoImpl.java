package com.crowdfunding.sjtu.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.utility.MD5Util;

@Repository
public class UserDaoImpl implements IUserDao {
	/**
	 * @return the sessionfactory
	 */
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public UserDaoImpl() {

	}

	public UserDaoImpl(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	/**
	 * @param sessionfactory
	 *            the sessionfactory to set
	 */
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	@Autowired
	SessionFactory sessionfactory;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().save(user);

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().delete(user);

	}

	@Override
	public void deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = (User) sessionfactory.getCurrentSession().load(User.class, userId);
		if (null != user) {
			sessionfactory.getCurrentSession().delete(user);
		}

	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		Query query = sessionfactory.getCurrentSession().createQuery("from User u where u.userId =:userId");
		query.setInteger("userId", userId);
		List<User> u = query.list();
		if (u.size() > 0) {
			return u.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<User> getUsers() {
		return sessionfactory.getCurrentSession().createQuery("from User u").list();
	}

	@Override
	public boolean login(String userName, String password) {
		User u = this.getUserByName(userName);
		if (null != u) {
			try {
				return u.getPassword().equals(MD5Util.MD5Encrypt(password));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void saveOrUpdate(User user) {
		sessionfactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User getUserByName(String userName) {
		Query query = sessionfactory.getCurrentSession().createQuery("from User u where u.userName =:userName");
		query.setString("userName", userName);
		List<User> ulist = query.list();
		if (ulist.size() > 0) {
			return ulist.get(0);
		} else {
			return null;
		}
	}
}

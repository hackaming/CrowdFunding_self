import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.crowdfunding.sjtu.utility.DateServiceImpl;
import com.crowdfunding.sjtu.utility.MD5Util;
import com.crowdfunding.sjtu.dao.UserDaoImpl;
import com.crowdfunding.sjtu.model.User;

public class UserDaoTest {
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringMVC-servlet.xml");
		SessionFactory sessionfactory = (SessionFactory) context.getBean("sessionFactory");
		UserDaoImpl userDao= new UserDaoImpl(sessionfactory);
		User u = new User();
		u.setCreatDate(DateServiceImpl.SDF_YYYYMMDDHHMMSS.format(new Date()));
		try {
			u.setPassword(MD5Util.MD5Encrypt("password"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		u.setSex("male");
		u.setStatus(1);
		u.setUserName("hackaming");
		//System.out.println(sessionfactory.equals(null));
		userDao.saveUser(u);
		// sessionfactory.getCurrentSession().save(u);
	}
}

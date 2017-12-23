import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crowdfunding.sjtu.service.UserServiceImpl;
import com.sjtu.SpringRelated.UserDao;
import com.sjtu.SpringRelated.UserServiceBean;

public class SpringTest {
	public static void main(String[] argv){
		ApplicationContext ct = new ClassPathXmlApplicationContext("classpath:beans.xml");
		/*PersonService ps =(PersonService) ct.getBean("personService");
		System.out.println(ps.getName());*/
		UserServiceBean ud = (UserServiceBean) ct.getBean("userServiceBean");
		System.out.println(ud.getScores().get("ff"));
		for(Entry<String,Integer> entry:ud.getScores().entrySet()){
			System.out.println(entry.getKey()+entry.getValue());
		}
		ud.getUserDao().showUser();
	}
}

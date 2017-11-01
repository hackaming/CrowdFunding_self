package com.crowdfunding.sjtu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IOrderService;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;
import com.crowdfunding.sjtu.utility.MD5Util;

@Controller
public class UserController {
	
	@Autowired
	private IDateService dateService;
	@Autowired
	private IUserService userServie;
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private IOrderService orderservice;
	@Autowired
	private IProjectService projectservice;
	

	
	@RequestMapping("/")
	public String getHome1(){
		return getHome();
	}
	
	@RequestMapping("/index")
	public String getHome(){
		return "project/project_list";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}
	@RequestMapping("/checkmongodb")
	public String checkmongodb(){
		 Logger logger1 = Logger.getLogger("MongoDB"); 
		for (int i=0;i<10;i++){
			logger1.info("{'info':123}");
			logger1.error("{'error':123}");
			logger1.warn("{'warn':123}");
			logger1.debug("{'debug':123}");
		}
			
		return "user/login";
	}
	@RequestMapping(value="/user/log",method = RequestMethod.POST)
	public String login1(@RequestParam String userName,@RequestParam String password, HttpSession session){
		//@RequestParam("userName") String userName,@RequestParam("password") String password
		//String userName1 = req.getParameter("userName");
		//String password = req.getParameter("password");
		//鏉╂瑧顫掗懢宄板絿閺佺増宓侀惃鍕煙濞夋洘婀侀梻顕�顣介敍灞剧叀娑擄拷娑撳鏋冨锝忕礉妞よ桨绌堕幎濠呯箹娴滄稒甯撮崣锝勭瘍閹肩偞绔诲Δ姘剧礉閸欘垵鍏橀弰顖欓嚋閻儴鐦戦惄鑼仯閿涘本澧嶉張澶屾畱閸︾増鏌熼柈鍊燁洣娣囶喗鏁奸妴锟�
		//System.out.println("get userName from @requestParam" + userName);
		//System.out.println("userName get from login.jsp by getParameter is:"+userName1);
		//org.hibernate.QueryException: Not all named parameters have been set: [userName] [from User u where u.userName =:userName]
		//鏉╂瑤閲滈崷鐗堟煙閺堝UG閿涘本娈忛弮鏈电瑝閻儵浜炬禒锟芥稊鍫濆斧閸ョ媴绱濋弮鐘崇《閻ц缍嶉敍灞惧ГSQU QUERY ERROR閿涘苯鍘涢弨鎹愮箹闁插苯鎯傞妴锟�
		User u = userServie.getUserByName(userName);	
		if ( !userServie.login(userName, password) ){ //此处要改。。。。不需要再登录了，已经取了一次了USER了访问了db了
			//login failure
			return "user/login_failure";
		} else {
			//login successfully
			//鏉╂﹢娓剁憰浣瑰Ω閻€劍鍩涢惂璇茬秿閹存劕濮涢惃鍕殶閹诡喗鏂侀崚鐨奺ssion闁插矂娼�,鏉烆剟銆嶉惄顔煎灙鐞涳拷
			//瀵板懎鐣幋锟�
			//閸氬氦娴嗘い鍦窗閸掓銆冩い鐢告桨
			//return "redirect:/projectlist";			//also works
			logger.info("user id is"+u.getUserId());
			logger.info("user name is"+u.getUserName() + "now set it into session");
			session.setAttribute("user", u);   //登录成功，将user存到session
			return "forward:/projectlist";
		}
	}
	//log out
	@RequestMapping(value="/user/logout")
	public String userLogout(HttpSession session)
	{
		if (null != session.getAttribute("user")){
			session.removeAttribute("user");
		}
		return "user/login";
	}
	
	//get the parameters and save it into db and then return a succ/failure page to the user
	@RequestMapping(value="/user/reg",method = RequestMethod.POST)
	public String reg(HttpServletRequest req, HttpServletResponse resp,@RequestParam("password") String password,@RequestParam("password1") String password1) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//1.閹跺﹤寮弫鐗堝复閺�鎯扮箖閺夈儻绱�2.鐠嬪啰鏁ERVICE鐏炲偊绱濋幓鎺戝弳DB,3.鏉╂瑩鍣锋稉杞扮啊缁狅拷閸栨牭绱濋崗鍫㈡纯閹恒儴鐨熼悽鈥旳O閿涘奔绲鹃崥搴ㄦ桨闂囷拷鐟曚焦鏁煎锝堢箖閺夈儯锟斤拷

		if (!password.equals(password1)){
			return "user/register_failure";
		}
		User user = new User();		
		//闂囷拷鐟曚礁濮為崗銉ュ灲閺傤厾鏁ら幋绋〥閺勵垰鎯佺�涙ê婀惃鍕拷鏄忕帆閿涘矁绻曠憰浣癸拷婵婏拷鍐х娑擃亪妫舵０姗堢礉閺勵垯绗夐弰顖涘Ω鏉╂瑤绨烘禒锝囩垳闁姤鏂侀崚鐗堟箛閸斺�崇湴閿涚喕顩︽稉宥囧姧閹貉冨煑鐏炲倷绱扮搾濠冩降鐡掑﹤顦块敍宀�娲块幒銉ㄧ殶閺堝秴濮熺仦鍌氭皑鐞涘奔绨�?
		//Debug user only
		logger.info("registration called");
		user.setUserName(req.getParameter("userName"));
		user.setPassword(MD5Util.MD5Encrypt(req.getParameter("password")));
		user.setSex(req.getParameter("sex"));
		user.setCellPhone(req.getParameter("cellPhone"));
		//鏉╂﹢娓剁憰浣筋啎缂冪摵ATETIME, STATUS
		user.setCreateDateTime(dateService.getStandardDate());
		userServie.saveUser(user);
		logger.info("user's saved");
		return "user/register_success";
	}
	
	//show the jsp form
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
	
	@RequestMapping("/usercenter")
	public String userCenter(HttpServletRequest request ,HttpSession session,ModelMap modelmap){
		User u = (User) session.getAttribute("user");
		if (null == u){
			return "user/login";
		}
		
		List<Orders> orders = orderservice.getOrdersByUserId(u.getUserId());
		
		modelmap.addAttribute("orders", orders);
		return "user/user_center";
	}
}

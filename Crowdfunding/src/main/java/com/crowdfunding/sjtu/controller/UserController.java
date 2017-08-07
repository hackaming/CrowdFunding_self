package com.crowdfunding.sjtu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;
import com.crowdfunding.sjtu.utility.MD5Util;

@Controller
public class UserController {
	
	@Autowired
	public IDateService dateService;
	@Autowired
	public IUserService userServie;
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
	
	@RequestMapping(value="/user/log",method = RequestMethod.POST)
	public String login1(HttpServletRequest req){
		
		//杩欑鑾峰彇鏁版嵁鐨勬柟娉曟湁闂锛屾煡涓�涓嬫枃妗ｏ紝椤轰究鎶婅繖浜涙帴鍙ｄ篃鎼炴竻妤氾紝鍙兘鏄釜鐭ヨ瘑鐩茬偣锛屾墍鏈夌殑鍦版柟閮借淇敼銆�
		String userName = (String) req.getAttribute("userName");
		String password = (String) req.getAttribute("password");
		
		System.out.println("userName get from login.jsp is:"+userName);
		//org.hibernate.QueryException: Not all named parameters have been set: [userName] [from User u where u.userName =:userName]
		//杩欎釜鍦版柟鏈塀UG锛屾殏鏃朵笉鐭ラ亾浠�涔堝師鍥狅紝鏃犳硶鐧诲綍锛屾姤SQU QUERY ERROR锛屽厛鏀捐繖閲屽惂銆�
		if ( !userServie.login(userName, password) ){
			//login failure
			return "user/login_failure";
		} else {
			//login successfully
			//杩橀渶瑕佹妸鐢ㄦ埛鐧诲綍鎴愬姛鐨勬暟鎹斁鍒皊ession閲岄潰,杞」鐩垪琛�
			//寰呭畬鎴�
			//鍚庤浆椤圭洰鍒楄〃椤甸潰
			return "/projectlist";			
		}
	}
	
	//get the parameters and save it into db and then return a succ/failure page to the user
	@RequestMapping(value="/user/reg",method = RequestMethod.POST)
	public String reg(HttpServletRequest req, HttpServletResponse resp) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//1.鎶婂弬鏁版帴鏀惰繃鏉ワ紝2.璋冪敤SERVICE灞傦紝鎻掑叆DB,3.杩欓噷涓轰簡绠�鍖栵紝鍏堢洿鎺ヨ皟鐢―AO锛屼絾鍚庨潰闇�瑕佹敼姝ｈ繃鏉ャ��
		User user = new User();
		if (!req.getParameter("password").equals(req.getParameter("password1"))){
			return "user/register_failure";
		}
		
		//闇�瑕佸姞鍏ュ垽鏂敤鎴稩D鏄惁瀛樺湪鐨勯�昏緫锛岃繕瑕佹�濊�冧竴涓棶棰橈紝鏄笉鏄妸杩欎簺浠ｇ爜閮芥斁鍒版湇鍔″眰锛熻涓嶇劧鎺у埗灞備細瓒婃潵瓒婂锛岀洿鎺ヨ皟鏈嶅姟灞傚氨琛屼簡?
		//Debug user only
		System.out.println("registration called");
		user.setUserName(req.getParameter("userName"));
		user.setPassword(MD5Util.MD5Encrypt(req.getParameter("password")));
		user.setSex(req.getParameter("sex"));
		user.setCellPhone(req.getParameter("cellPhone"));
		//杩橀渶瑕佽缃瓺ATETIME, STATUS
		user.setCreateDateTime(dateService.getStandardDate());
		userServie.saveUser(user);
		return "user/register_success";
	}
	
	//show the jsp form
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}

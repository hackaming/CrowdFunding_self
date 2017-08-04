package com.crowdfunding.sjtu.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registration_user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9057445092750503177L;

	// UUID
	@Id
	@Column(name = "UUID")
	private String uuid;
	
	// 昵称
	@Column(name = "NICKNAME")
	private String nickName;
	
	// 姓名
	@Column(name = "NAME")
	private String name;
	
	// 性别
	@Column(name = "SEX")
	private String sex;
	
	// 证件类型
	@Column(name = "ID_TYPE")
	private String idType;
	
	// 身份证号码
	@Column(name = "IDCARD")
	private String idCard;
	
	// 手机号码
	@Column(name = "PHONENUMBER")
	private String phoneNumber;
	
	// 登录密码
	@Column(name = "PASSWORD")
	private String password;
	
	// 邮箱地址
	@Column(name = "EMAIL")
	private String email;
	
	// 提取密码
	@Column(name = "EXTRACTPASSWORD")
	private String extractPassword;
	
	// 出生日期
	@Column(name = "BIRTHDAY")
	private String birthday;
	
	// 最高学历
	@Column(name = "EDUCATION")
	private String education;
	
	// 毕业院校
	@Column(name = "SCHOOLNAME")
	private String schoolName;
	
	// 婚姻状况
	@Column(name = "WEDLOCK")
	private String wedlock;
	
	// 居住地址
	@Column(name = "ADDRESS")
	private String address;
	
	// 所在公司行业
	@Column(name = "COMPANY_TRADE")
	private String companyTrade;
	
	// 所在公司规模
	@Column(name = "COMPANY_SCALE")
	private String companyScale;
	
	// 月收入
	@Column(name = "MONTHLY_INCOME")
	private String monthlyIncome;
	
	// 职位
	@Column(name = "POSITION")
	private String position;
	
	// 填报时间
	@Column(name = "CREATE_DATE")
	private String createDate;
	
	// 业务类型
	@Column(name = "TYPE")
	private String type;
	
	// 注销标记
	@Column(name = "CANCEL")
	private String cancel;
	
	// 备注
	@Column(name = "COMMENTS")
	private String comments;

    // 用户名
    @Column(name = "USERNAME")
    private String userName;
    
    // 紧急联系人
    @Column(name = "emer_name")
    private String emerName;
    
    // 紧急联系人电话
    @Column(name = "emer_tel")
    private String emerTel;
    
    // 生活圈
	@Column(name = "life_circle")
	private String lifeCircle;
	
	// 用户地址
	@Column(name = "useraddr")
	private String userAddr;
	
	// 用户公司
	@Column(name = "user_corp")
	private String userCorp;
	
	// 用户职位
	@Column(name = "user_position")
	private String userPosition;
	
	// 月薪
	@Column(name = "user_salary")
	private String userSalary;
	
	// 学历
	@Column(name = "user_degree")
	private String userDegree;
	
	// 毕业学校
	@Column(name = "user_school")
	private String userSchool;

/*	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="uuid", referencedColumnName="user_uuid", unique=true, insertable=false, updatable=false)
	private Account account;*/
	
    @Column(name = "USRCUSTID")
    private String usrCustId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtractPassword() {
        return extractPassword;
    }

    public void setExtractPassword(String extractPassword) {
        this.extractPassword = extractPassword;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyTrade() {
        return companyTrade;
    }

    public void setCompanyTrade(String companyTrade) {
        this.companyTrade = companyTrade;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

/*    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
*/
    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

	public String getEmerName() {
		return emerName;
	}

	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}

	public String getEmerTel() {
		return emerTel;
	}

	public void setEmerTel(String emerTel) {
		this.emerTel = emerTel;
	}

	public String getLifeCircle() {
		return lifeCircle;
	}

	public void setLifeCircle(String lifeCircle) {
		this.lifeCircle = lifeCircle;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserCorp() {
		return userCorp;
	}

	public void setUserCorp(String userCorp) {
		this.userCorp = userCorp;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(String userSalary) {
		this.userSalary = userSalary;
	}

	public String getUserDegree() {
		return userDegree;
	}

	public void setUserDegree(String userDegree) {
		this.userDegree = userDegree;
	}

	public String getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}

}

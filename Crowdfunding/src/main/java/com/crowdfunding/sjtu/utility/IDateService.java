package com.crowdfunding.sjtu.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface IDateService {
	//http://www.cnblogs.com/peida/archive/2013/05/31/3070790.html
	//by refer to lindon's code and above link. Note that SimpleDateFormat's not thread safe, need to create a new one every time use it.
	
	public final static SimpleDateFormat SDF_YYYY = new SimpleDateFormat("yyyy");
	public final static SimpleDateFormat SDF_MM = new SimpleDateFormat("mm");
	public final static SimpleDateFormat SDF_DD = new SimpleDateFormat("dd");
	public final static SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyymmdd");
	public final static SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyymmddhhmmss");
	public final static SimpleDateFormat SDF_STANDARD = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	public String getCurrentYear();
	public String getCurrentMonth();
	public String getCurrentDay();
	public String getStandardDate();
	public String getFullDate();
	public String getCurrentYearMonthDay();
	public Date parseDate(String date) throws ParseException;
}

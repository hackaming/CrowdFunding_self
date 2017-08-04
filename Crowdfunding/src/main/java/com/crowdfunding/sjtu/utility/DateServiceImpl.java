package com.crowdfunding.sjtu.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements IDateService {

	@Override
	public String getCurrentYear() {
		return SDF_YYYY.format(new Date());
	}

	@Override
	public String getCurrentMonth() {
		return SDF_MM.format(new Date());
	}

	@Override
	public String getCurrentDay() {
		return SDF_DD.format(new Date());
	}

	@Override
	public String getStandardDate() {
		return SDF_STANDARD.format(new Date());
	}

	@Override
	public String getFullDate() {
		return SDF_YYYYMMDDHHMMSS.format(new Date());
	}

	@Override
	public String getCurrentYearMonthDay() {
		return SDF_YYYYMMDD.format(new Date());
	}

	@Override
	public Date parseDate(String date) throws ParseException {
		return SDF_STANDARD.parse(date);
	}

}

package com.jc.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateCompare {

	@Test
	public void test() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		  Date sd1=df.parse("2017-08-01");
		  Date sd2=df.parse("2017-08-02");

		    System.out.println(sd1.before(sd2));
		   System.out.println(sd1.after(sd2));
	}
}

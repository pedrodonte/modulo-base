package info.pedrodonte.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class HelperFecha {

	public static Timestamp getActual() {
		return new Timestamp(new Date().getTime());
	}

	public static Timestamp getMasAnios() {
		Calendar c = Calendar.getInstance();
		c.setTime(getActual());
		c.add(Calendar.YEAR, 4);
		Date newDate = c.getTime();
		return new Timestamp(newDate.getTime());
	}
	
	public static Timestamp getInfinito() {
		Calendar c = Calendar.getInstance();
		c.setTime(getActual());
		c.set(9998, 12, 31);
		Date newDate = c.getTime();
		return new Timestamp(newDate.getTime());
	}
	
	

}

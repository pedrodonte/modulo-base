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

	public static Integer calcularEdad(Date fechaNac) {
		Calendar fechaNacimiento = Calendar.getInstance();
		// Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Se asigna la fecha recibida a la fecha de nacimiento.
		fechaNacimiento.setTime(fechaNac);
		// Se restan la fecha actual y la fecha de nacimiento
		int año = fechaActual.get(Calendar.YEAR)
				- fechaNacimiento.get(Calendar.YEAR);
		int mes = fechaActual.get(Calendar.MONTH)
				- fechaNacimiento.get(Calendar.MONTH);
		int dia = fechaActual.get(Calendar.DATE)
				- fechaNacimiento.get(Calendar.DATE);
		// Se ajusta el año dependiendo el mes y el día
		if (mes < 0 || (mes == 0 && dia < 0)) {
			año--;
		}
		// Regresa la edad en base a la fecha de nacimiento
		return año;
	}
	
	public static Integer calcularDiferenciaMinutos(Date fechaAnterior, Date fechaPosterior) {
		Calendar fechaUno = Calendar.getInstance();
		fechaUno.setTime(fechaAnterior);
		
		Calendar fechaDos = Calendar.getInstance();
		fechaDos.setTime(fechaPosterior);
		
		int minutos = fechaDos.get(Calendar.MINUTE)
				- fechaUno.get(Calendar.MINUTE);
		
		return minutos;
	}

}

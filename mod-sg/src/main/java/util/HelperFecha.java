package util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import modulo.base.excepciones.ErrorDelSistemaException;

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

	public static Integer calcularDiferenciaMinutos(Date fechaAnterior,
			Date fechaPosterior) {
		Calendar fechaUno = Calendar.getInstance();
		fechaUno.setTime(fechaAnterior);

		Calendar fechaDos = Calendar.getInstance();
		fechaDos.setTime(fechaPosterior);

		int minutos = fechaDos.get(Calendar.MINUTE)
				- fechaUno.get(Calendar.MINUTE);

		return minutos;
	}

	/**
	 * Parsear fecha String a util.Date, en formato input yyyy-MM-dd
	 * 
	 * @param strFecha
	 * @return
	 * @throws ErrorDelSistemaException
	 */
	public static Date toDate(String strFecha) throws ErrorDelSistemaException {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		try {
			fecha = formatoDelTexto.parse(strFecha);
		} catch (ParseException e) {
			throw new ErrorDelSistemaException("ParseException " + strFecha
					+ " a util.Date");
		}
		return fecha;
	}

	public static String toString(Date fecha) {

		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(fecha);
		return s;
	}
	
	public static List<Date> obtenerFechasDiariasIntervalo(Date fechaInicial, Date fechaFinal)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(fechaInicial);

	    while (calendar.getTime().before(fechaFinal))
	    {
	        Date resultado = calendar.getTime();
	        dates.add(resultado);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
}

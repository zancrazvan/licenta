package com.licenta.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {

	public Date convert(String dateInString) {

		// 2015/08/05 14:00:12
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		try {

			Date date = formatter.parse(dateInString);

			return date;
		} catch (ParseException e) {
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				Date date = formatter.parse(dateInString);
				return date;
			} catch (ParseException e1) {

				e1.printStackTrace();
			}
		}
		return null;

	}

	public String formatDate(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String dateToStr = format.format(date);

		return dateToStr;

	}

	public String getFrontEndDate(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String dateToStr = format.format(date);

		return dateToStr;

	}

	public String getFrontEndDateWithHour(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String dateToStr = format.format(date);

		return dateToStr;

	}

}

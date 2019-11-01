package edu.fema.TrabalhoTopicosSpring.util;

public class Utils {

	public static String formatarTempo(Long elapsed) {
		Long ss = elapsed % 60;
		elapsed /= 60;
		Long min = elapsed % 60;
		elapsed /= 60;
		Long hh = elapsed % 24;
		return strzero(hh) + ":" + strzero(min) + ":" + strzero(ss);
	}

	private static String strzero(Long n) {
		if (n < 10)
			return "0" + String.valueOf(n);
		return String.valueOf(n);
	}
	
}

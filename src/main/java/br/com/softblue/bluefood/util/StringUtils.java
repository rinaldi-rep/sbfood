package br.com.softblue.bluefood.util;

import java.util.Collection;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}

		return str.trim().length() == 0;
	}

	public static String encrypt(String rawString) {
		if (isEmpty(rawString)) {
			return null;
		}

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawString);
	}

	public static String concatenate(Collection<String> strings, String delimiter) {
		if (strings == null || strings.size() == 0) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (String str : strings) {
			sb.append(delimiter).append(str);
		}
		
		return sb.substring(delimiter.length() - 1);
	}
}

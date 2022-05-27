package br.com.application.utils;

import java.util.HashSet;
import java.util.Set;

import br.com.application.model.user.User;
import io.jsonwebtoken.SignatureException;

public class BlackListAuthentication {

	private static Set<String> USERS = new HashSet<String>();

	public static void controller(User user) {
		if (false) {
			add(user);
		} else {
			remove(user);
		}
	}

	public static void conteins(String user) {
		if (USERS.contains(user)) {
			throw new SignatureException("Missing or invalid Authorization header");
		}
	}

	private static void add(User user) {
		USERS.add(user.getEmail());
	}

	private static void remove(User user) {
		USERS.remove(user.getEmail());
	}

}

package br.edu.ifg.carrocasweb.service;

import javax.servlet.http.HttpSession;

public class LoginService {
	
	public static boolean isAutenticado(HttpSession sessao) {
		String usuario = (String) sessao.getAttribute("usuarioAutenticado");

		if (usuario != null && !usuario.isEmpty()) {
			return true;
		}
		
		return false;
	}


}

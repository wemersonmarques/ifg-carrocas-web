package br.edu.ifg.carrocasweb.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.persist.dao.UsuarioDAO;

@Controller
public class LoginController extends Thread {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/logon")
	public ModelAndView logon() {
		return new ModelAndView("login/login");
	}

	@RequestMapping("/logar")
	public ModelAndView logar(@ModelAttribute("user") String usuario, @ModelAttribute("pass") String pass) {
		Usuario user = usuarioDao.consultarPorLogin(usuario);
		
		if (user != null && user.getLogin().equals(usuario) && user.getSenha().equals(pass)) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioAutenticado", user.getLogin());
			return new ModelAndView("redirect:");
		}

		return new ModelAndView("redirect:");
	}

	@RequestMapping("/deslogar")
	public ModelAndView deslogar() {

		HttpSession sessao = request.getSession();
		sessao.invalidate();

		return new ModelAndView("redirect:");
	}
}

package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.persist.dao.UsuarioDAO;
import br.edu.ifg.carrocasweb.service.LoginService;

@Controller
public class UsuarioController extends Thread {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private HttpSession sessao;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public String inicio() {
		return "cadastro/cadastrousuario";
	}

	public UsuarioController() {
		this.start();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public String salvar(Usuario usuario) {
		if (!usuarioDao.existe(usuario.getLogin())) {
			usuarioDao.salvar(usuario);
			return "cadastro/cadastrousuario";
		} else {
			usuarioDao.atualizar(usuario);
		}
		return "redirect:";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/listarusuarios")
	public ModelAndView usuarios() {
		ModelAndView mav = new ModelAndView("cadastro/cadastrousuario");
		Iterable<Usuario> usuarios = usuarioDao.consultarTodos(Usuario.class);
		mav.addObject("usuarios", usuarios);

		return mav;
	}

	@PostMapping("**/pesquisarusuario")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mav = new ModelAndView("cadastro/cadastrousuario");
		List<Usuario> listaUsuarios = usuarioDao.consultarPorNome(nomepesquisa);
		mav.addObject("usuariosnome", listaUsuarios);
		mav.addObject("usuarioo", new Usuario());
		return mav;
	}

	@GetMapping("/editarusuario")
	public ModelAndView editarUsuario() {
		String login = (String) sessao.getAttribute("usuarioAutenticado");
		Usuario usuario = usuarioDao.consultarPorLogin(login);

		if (LoginService.isAutenticado(sessao)) {
			ModelAndView mav = new ModelAndView("edicao/edicaousuario");

			mav.addObject("usuario", usuario);

			return mav;
		} else {
			return new ModelAndView("redirect:logon");
		}
	}

}

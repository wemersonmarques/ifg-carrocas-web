package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.persist.dao.UsuarioDAO;

@Controller
public class UsuarioController extends Thread {

	@Autowired
	private UsuarioDAO usuarioDao;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public String inicio() {
		return "cadastro/cadastrousuario";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public String salvar(Usuario usuario) {
		if (!usuarioDao.existe(usuario.getLogin())) {
			usuarioDao.salvar(usuario);
			return "cadastro/cadastrousuario";
		}
		return "redirect:cadastrousuario";

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

}

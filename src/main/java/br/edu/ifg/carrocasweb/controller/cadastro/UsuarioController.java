package br.edu.ifg.carrocasweb.controller.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.persist.dao.UsuarioDAO;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public String inicio(){
		return "cadastro/cadastrousuario";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public String salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
		return "cadastro/cadastrousuario";
	}
	
}

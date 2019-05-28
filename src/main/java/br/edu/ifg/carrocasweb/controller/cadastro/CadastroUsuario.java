package br.edu.ifg.controller.cadastro;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifg.model.usuario.Usuario;
import br.edu.ifg.persist.dao.UsuarioDAO;

@RestController
public class CadastroUsuario {

	private final static Logger LOG = Logger.getLogger(CadastroUsuario.class.getName());
	
	@Autowired
	private UsuarioDAO dao;
	
	public CadastroUsuario () {
	}
	
	public Usuario cadastrar(Usuario usuario) {
		
		if (!dao.existe(usuario.getLogin())) {
			LOG.info("Usuario não existia e será persistido");
			dao.salvar(usuario);
		}
		
		return dao.consultarPorLogin(usuario.getLogin());
	}
	
	@RequestMapping(value = "/usuarioId")
	public String consultaUsuario(@RequestParam String usuarioId){
		System.out.println("ID requisitado: " + usuarioId);
		return "req concluida";
	}
}

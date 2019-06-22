package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.persist.dao.AnuncioDAO;


@Controller
public class AnuncioController {
	
	@Autowired
	private AnuncioDAO anuncioDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroanuncio")
	public String inicio(){
		return "cadastro/cadastroanuncio";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvaranuncio")
	public String salvar(Anuncio anuncio) {
		anuncioDao.salvar(anuncio);
		return "cadastro/cadastroanuncio";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaranuncios")
	public ModelAndView anuncios() {
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		Iterable<Anuncio> anuncios = anuncioDao.consultarTodos(Anuncio.class);
		mav.addObject("anuncios", anuncios);

		return mav;		
	}
	
	@PostMapping("**/pesquisanuncio")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		List<Anuncio> listaAnuncio = anuncioDao.consultarPorTitulo(nomepesquisa); 
		mav.addObject("anunciosnome", listaAnuncio);
		mav.addObject("anuncio", new Anuncio());
		return mav;
	}

}

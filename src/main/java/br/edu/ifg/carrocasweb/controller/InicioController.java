package br.edu.ifg.carrocasweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.veiculo.Marca;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;
import br.edu.ifg.carrocasweb.persist.dao.AnuncioDAO;
import br.edu.ifg.carrocasweb.persist.dao.MarcaDAO;
import br.edu.ifg.carrocasweb.persist.dao.VeiculoDAO;

@Controller
public class InicioController extends Thread {
	@Autowired
	private AnuncioDAO anuncioDao;
	
	@Autowired
	private MarcaDAO marcaDao;
	
	@Autowired
	private VeiculoDAO veiculoDao;
	
	@RequestMapping(method = RequestMethod.GET, value = {"/", "/index.html"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index.html");
		Iterable<Anuncio> anuncios = anuncioDao.consultarTodos(Anuncio.class);
		mav.addObject("anuncios", anuncios);
		mav.addObject("anuncio", new Anuncio());
		mav.addObject("marcas", marcaDao.consultarTodos(Marca.class));
		return mav;	
	}
	
	@PostMapping("/pesquisarPorTitulo")
	public ModelAndView pesquisarPorTitulo(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mav = new ModelAndView("index.html");
		List<Anuncio> anuncios = anuncioDao.consultarPorTitulo(nomepesquisa); 
		mav.addObject("marcas", marcaDao.consultarTodos(Marca.class));
		mav.addObject("anuncios", anuncios);
		return mav;
	}
	
	@RequestMapping("/pesquisarPorMarca")
	public ModelAndView pesquisarPorMarca(@RequestParam("idMarcaVeiculo") long id) {
		ModelAndView mav = new ModelAndView("index.html");
		List<Anuncio> anuncios = anuncioDao.consultarPorMarca(id);
		mav.addObject("marcas", marcaDao.consultarTodos(Marca.class));
		mav.addObject("anuncios", anuncios);
		return mav;
	}
}

package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.functionality.FileWritter;
import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.veiculo.Marca;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;
import br.edu.ifg.carrocasweb.persist.dao.AnuncioDAO;
import br.edu.ifg.carrocasweb.persist.dao.MarcaDAO;
import br.edu.ifg.carrocasweb.persist.dao.VeiculoDAO;


@Controller
public class AnuncioController extends Thread {
	
	@Autowired
	private AnuncioDAO anuncioDao;
	
	@Autowired
	private MarcaDAO marcaDao;
	
	@Autowired
	private VeiculoDAO veiculoDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroanuncio")
	public ModelAndView inicio(){
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		List<Marca> marcas = marcaDao.consultarTodos(Marca.class);
		Veiculo veiculo = new Veiculo();
		Anuncio anuncio = new Anuncio();

		mav.addObject("marcas", marcas);
		mav.addObject("marca", new Marca());
		mav.addObject("veiculo", veiculo);
		mav.addObject("anuncio", anuncio);
	
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvaranuncio")
	public String salvar(Anuncio anuncio, @RequestParam("files") MultipartFile[] files) {
		Veiculo veiculo = new Veiculo();
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		mav.addObject("veiculo", veiculo);
		
		
		veiculo.setMarca((Marca) marcaDao.consultarPorId(Marca.class, anuncio.getIdMarcaVeiculo()));
		veiculo.setModelo(anuncio.getModeloVeiculo());
		veiculo.setMotorizacao(anuncio.getMotorizacaoVeiculo());
		veiculo.setQuilometragem(anuncio.getQuilometragemVeiculo());
		veiculoDao.salvar(veiculo);
		
		anuncio.setVeiculo((Veiculo) veiculoDao.consultarPorId(Veiculo.class, veiculo.getId()));
		anuncioDao.salvar(anuncio);
		
		FileWritter upload = new FileWritter();
		upload.upload(files, anuncio.getId());
		
		return "redirect:cadastroanuncio";
	}
	
	@Deprecated
	@RequestMapping(method = RequestMethod.GET, value = "/listaranuncios")
	public ModelAndView anuncios() {
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		Iterable<Anuncio> anuncios = anuncioDao.consultarTodos(Anuncio.class);
		mav.addObject("anuncios", anuncios);
		mav.addObject("anuncio", new Anuncio());

		return mav;		
	}
	

}

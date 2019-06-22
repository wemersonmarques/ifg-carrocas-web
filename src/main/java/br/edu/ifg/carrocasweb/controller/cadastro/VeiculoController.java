package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.veiculo.Marca;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;
import br.edu.ifg.carrocasweb.persist.dao.MarcaDAO;
import br.edu.ifg.carrocasweb.persist.dao.VeiculoDAO;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoDAO veiculoDao;
	
	@Autowired
	private MarcaDAO marcaDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroveiculo")
	public ModelAndView inicio(){
		ModelAndView mav = new ModelAndView("cadastro/cadastroveiculo");
		List<Marca> marcas = marcaDao.consultarTodos(Marca.class);
		Veiculo veiculo = new Veiculo();
		mav.addObject("marcas", marcas);
		mav.addObject("marca", new Marca());
		mav.addObject("veiculo", veiculo);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarveiculo")
	public ModelAndView salvar(Veiculo veiculo) {		
		ModelAndView mav = new ModelAndView("cadastro/cadastroveiculo");
		mav.addObject("veiculo", veiculo);
		veiculo.setMarca((Marca) marcaDao.consultarPorId(Marca.class, Long.valueOf(veiculo.getMarcaIddd())));
		veiculoDao.salvar(veiculo);	
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarveiculos")
	public ModelAndView veiculos() {
		ModelAndView mav = new ModelAndView("cadastro/cadastroveiculo");
		Iterable<Veiculo> veiculos = veiculoDao.consultarTodos(Veiculo.class);
		mav.addObject("veiculo", new Veiculo());
		mav.addObject("veiculos", veiculos);

		return mav;		
	}
	
	@PostMapping("**/pesquisarveiculo")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mav = new ModelAndView("cadastro/cadastroveiculo");
		List<Veiculo> veiculos = veiculoDao.consultarPorModelo(nomepesquisa); 
		mav.addObject("veiculosnome", veiculos);
		mav.addObject("veiculo", new Veiculo());
		return mav;
	}

}

package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;
import br.edu.ifg.carrocasweb.persist.dao.VeiculoDAO;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoDAO veiculoDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroveiculo")
	public String inicio(){
		return "cadastro/cadastroveiculo";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarveiculo")
	public String salvar(Veiculo veiculo) {
		veiculoDao.salvar(veiculo);
		return "cadastro/cadastroveiculo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarveiculos")
	public ModelAndView usuarios() {
		ModelAndView mav = new ModelAndView("cadastro/cadastroveiculo");
		Iterable<Veiculo> veiculos = veiculoDao.consultarTodos(Veiculo.class);
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

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

@Controller
public class MarcaController {
	
	@Autowired
	private MarcaDAO marcaDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastromarca")
	public String inicio(){
		return "cadastro/cadastromarca";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarmarca")
	public String salvar(Marca marca) {
		marcaDao.salvar(marca);
		return "cadastro/cadastromarca";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarmarcas")
	public ModelAndView marcas() {
		ModelAndView mav = new ModelAndView("cadastro/cadastromarca");
		Iterable<Marca> marcas = marcaDao.consultarTodos(Marca.class);
		mav.addObject("marcas", marcas);

		return mav;		
	}
	
	@PostMapping("**/pesquisarmarca")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView mav = new ModelAndView("cadastro/cadastromarca");
		List<Marca> marcas = marcaDao.consultarPorNome(nomepesquisa); 
		mav.addObject("marcasnome", marcas);
		mav.addObject("marca", new Marca());
		return mav;
	}

}

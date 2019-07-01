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
public class MarcaController extends Thread {
	
	@Autowired
	private MarcaDAO marcaDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastromarca")
	public ModelAndView inicio(){
		ModelAndView mav = new ModelAndView("cadastro/cadastromarca");
		mav.addObject("marcas", marcaDao.consultarTodos(Marca.class));
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarmarca")
	public String salvar(Marca marca) {
		marcaDao.salvar(marca);
		
		return "redirect:cadastromarca";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarmarcas")
	public ModelAndView marcas() {
		ModelAndView mav = new ModelAndView("cadastro/cadastromarca");
		Iterable<Marca> marcas = marcaDao.consultarTodos(Marca.class);
		mav.addObject("marcas", marcas);

		return mav;		
	}

}

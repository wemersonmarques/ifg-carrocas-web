package br.edu.ifg.carrocasweb.controller.cadastro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.model.veiculo.Marca;
import br.edu.ifg.carrocasweb.persist.dao.MarcaDAO;
import br.edu.ifg.carrocasweb.service.LoginService;

@Controller
public class MarcaController extends Thread {

	@Autowired
	private MarcaDAO marcaDao;
	
	@Autowired
	private HttpSession sessao;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastromarca")
	public ModelAndView inicio() {
		if (LoginService.isAutenticado(sessao)) {
			ModelAndView mav = new ModelAndView("cadastro/cadastromarca");
			mav.addObject("marcas", marcaDao.consultarTodos(Marca.class));

			return mav;
		} else {
			return new ModelAndView("redirect:logon");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarmarca")
	public String salvar(Marca marca) {
		if (LoginService.isAutenticado(sessao)) {
			marcaDao.salvar(marca);
		}

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

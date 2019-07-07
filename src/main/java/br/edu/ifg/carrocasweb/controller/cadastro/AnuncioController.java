package br.edu.ifg.carrocasweb.controller.cadastro;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.carrocasweb.dto.AnuncioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifg.carrocasweb.functionality.FileWritter;
import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.model.veiculo.Marca;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;
import br.edu.ifg.carrocasweb.persist.dao.AnuncioDAO;
import br.edu.ifg.carrocasweb.persist.dao.MarcaDAO;
import br.edu.ifg.carrocasweb.persist.dao.UsuarioDAO;
import br.edu.ifg.carrocasweb.persist.dao.VeiculoDAO;
import br.edu.ifg.carrocasweb.service.LoginService;

@Controller
public class AnuncioController extends Thread {

	@Autowired
	private AnuncioDAO anuncioDao;

	@Autowired
	private MarcaDAO marcaDao;

	@Autowired
	private VeiculoDAO veiculoDao;

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private HttpSession sessao;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastroanuncio")
	public ModelAndView cadastroAnuncio() {
		if (LoginService.isAutenticado(sessao)) {
			ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
			List<Marca> marcas = marcaDao.consultarTodos(Marca.class);
			Veiculo veiculo = new Veiculo();
			Anuncio anuncio = new Anuncio();

			String usuario = (String) sessao.getAttribute("usuarioAutenticado");
			Usuario user = usuarioDao.consultarPorLogin(usuario);
			List<Anuncio> anuncios = anuncioDao.consultarPorUsuario(user);

			mav.addObject("marcas", marcas);
			mav.addObject("marca", new Marca());
			mav.addObject("veiculo", veiculo);
			mav.addObject("anuncio", anuncio);
			mav.addObject("anuncios", anuncios);

			return mav;
		} else {
			return new ModelAndView("redirect:logon");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvaranuncio")
	public String salvarAnuncio(AnuncioDTO anuncioDto, @RequestParam("files") MultipartFile[] files) {
		if (LoginService.isAutenticado(sessao)) {
			Veiculo veiculo = anuncioDto.getVeiculo();
			veiculo.setMarca((Marca) marcaDao.consultarPorId(Marca.class, anuncioDto.getIdMarcaVeiculo()));

			Anuncio anuncio = anuncioDto.getAnuncio();
			anuncio.setVeiculo(veiculo);
			anuncio.setUsuario(usuarioDao.consultarPorLogin(
					String.valueOf(
							sessao.getAttribute("usuarioAutenticado"))));

			FileWritter upload = new FileWritter();

			// Se anúncio já existir só vai atualizar
			if (anuncio.getId() != null && anuncioDao.existe(String.valueOf(anuncio.getId()))) {
				anuncio.setDataCadastro(new Date());
				veiculoDao.salvar(veiculo);
				anuncioDao.atualizar(anuncio);
				// Apaga arquivo anterior se existir
				if (files != null) {
					upload.apagarFotoExistente(anuncio.getId());
				}
			} else {
				veiculoDao.salvar(veiculo);
				anuncioDao.salvar(anuncio);
			}

			// Persistindo foto
			upload.upload(files, anuncio.getId());

			return "redirect:cadastroanuncio";
		} else {
			return "redirect:logon";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listaranuncios")
	public ModelAndView listarAnuncios() {
		ModelAndView mav = new ModelAndView("cadastro/cadastroanuncio");
		Iterable<Anuncio> anuncios = anuncioDao.consultarTodos(Anuncio.class);
		mav.addObject("anuncios", anuncios);
		mav.addObject("anuncio", new Anuncio());

		return mav;
	}

	@RequestMapping("/editarAnuncio")
	public ModelAndView editarAnuncio(@RequestParam("id") Long id) {
		if (LoginService.isAutenticado(sessao)) {
			ModelAndView mav = new ModelAndView("edicao/edicaoanuncio");
			Anuncio anuncio = (Anuncio) anuncioDao.consultarPorId(Anuncio.class, id);
			Veiculo veiculo = anuncio.getVeiculo();

			List<Marca> marcas = marcaDao.consultarTodos(Marca.class);

			mav.addObject("anuncio", anuncio);
			mav.addObject("veiculo", veiculo);
			mav.addObject("marcas", marcas);

			return mav;
		} else {
			return new ModelAndView("redirect:logon");
		}
	}

	@RequestMapping("inativarAnuncio")
	public ModelAndView inativarAnuncio(@RequestParam("id") Long id) {
		if (LoginService.isAutenticado(sessao)) {
			Anuncio anuncio = (Anuncio) anuncioDao.consultarPorId(Anuncio.class, id);

			if (anuncio != null) {
				anuncioDao.inativar(anuncio);
			}

			return new ModelAndView("redirect:cadastroanuncio");
		} else {
			return new ModelAndView("redirect:logon");
		}
	}

}

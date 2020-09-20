package br.com.profile.doadores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.profile.doadores.dao.DoadoresUFDao;
import br.com.profile.doadores.dao.EstadoDao;
import br.com.profile.doadores.model.Doadores;
import br.com.profile.doadores.model.Doadores_;
import br.com.profile.doadores.model.json.DoadorEstado;
import br.com.profile.doadores.model.json.DoadorUF;
import br.com.profile.doadores.repositories.DoadoresDaoImpl;

@CrossOrigin(value = "*")
@RequestMapping(path = "ui-doadores")
@RestController
public class UiDoadoresController {

	@Autowired
	private DoadoresDaoImpl doadoresDaoImpl;
	
	@Autowired
	private DoadoresUFDao doadorUFDao;
	
	@Autowired
	private EstadoDao estadoDao;
	
	
	@GetMapping(value = "/all")
	public ModelAndView list() {
		List<Doadores> doadores = this.doadoresDaoImpl.findAll(Sort.by(Order.asc(Doadores_.NOME)));
		return new ModelAndView("index", "doadores", doadores);
	}
	
	@GetMapping(value = "/estados")
	public ModelAndView estados() throws Exception {
		List<DoadorUF> doadoresUF = this.doadorUFDao.contarDoadoresPorUF();
		return new ModelAndView("doador_uf", "doadoresUF", doadoresUF);
	}
	
	@GetMapping(value = "/doadorEstado")
	public ModelAndView dadorEstado() throws Exception {
		List<DoadorEstado> doadoresUFCollection = this.estadoDao.getDoadoresPorEstado();
		return new ModelAndView("doador_uf_list", "doadoresEstado", doadoresUFCollection);
	}
}

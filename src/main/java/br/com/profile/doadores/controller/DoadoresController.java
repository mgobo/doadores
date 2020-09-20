package br.com.profile.doadores.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.profile.doadores.dao.DoadoresGeneroDao;
import br.com.profile.doadores.dao.DoadoresIMCFaixaIdadeDao;
import br.com.profile.doadores.dao.DoadoresTipoSanguineoDao;
import br.com.profile.doadores.dao.DoadoresUFDao;
import br.com.profile.doadores.dao.EstadoDao;
import br.com.profile.doadores.model.json.DoadorEstado;
import br.com.profile.doadores.model.json.DoadorIMC;
import br.com.profile.doadores.model.json.DoadorObesos;
import br.com.profile.doadores.model.json.DoadorTpSangue;
import br.com.profile.doadores.model.json.DoadorUF;
import br.com.profile.doadores.model.json.JsonResult;
import br.com.profile.doadores.model.json.JsonResult.Builder;

@CrossOrigin(value = {"*"})
@RequestMapping(path = "/doador")
@RestController
public class DoadoresController {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private DoadoresUFDao doadoresUFDao;
	
	@Autowired
	private DoadoresIMCFaixaIdadeDao doadoresIMCFaixaIdadeDao;
	
	@Autowired
	private DoadoresGeneroDao doadoresGeneroDao;
	
	@Autowired
	private DoadoresTipoSanguineoDao doadoresTipoSanguineoDao;
	
	@Autowired
	private EstadoDao estadoDao;
	
	@GetMapping(path = "/estado/profile")
	public ResponseEntity<JsonResult<DoadorEstado>> estadoProfile(){
		Builder<DoadorEstado> builder = new Builder<>();
		try {
			List<DoadorEstado> doadorEstado = this.estadoDao.getDoadoresPorEstado();
			
			builder = new Builder<DoadorEstado>().withCollectionData(doadorEstado)
				   						   	 	   .withTotalRecords(doadorEstado.size());
			
			return ResponseEntity.ok(builder.build());
		}catch(Exception ex) {
			builder.withMessageError(ex.getMessage())
			   	   .withTotalRecords(0l);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
	}
	
	@GetMapping(path = "/estado")
	public ResponseEntity<JsonResult<DoadorUF>> estado(){
		Builder<DoadorUF> builder 		 = new Builder<DoadorUF>();
		try {
			List<DoadorUF> collectionEstados = this.doadoresUFDao.contarDoadoresPorUF();
			builder = new Builder<DoadorUF>().withCollectionData(collectionEstados)
											   .withTotalRecords(collectionEstados.size());
			
			return ResponseEntity.ok(builder.build());
		}catch(Exception ex) {
			builder.withMessageError(ex.getMessage())
				   .withTotalRecords(0l);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
		}
	}
	
	@GetMapping(path = "/imc/faixa")
	public ResponseEntity<JsonResult<DoadorIMC>> imcFaixa(){
		Builder<DoadorIMC> builder 		 = new Builder<DoadorIMC>();
		try {
			List<DoadorIMC> doadorIMC = this.doadoresIMCFaixaIdadeDao.doadoresIMCFaixaIdade();
			builder = new Builder<DoadorIMC>().withCollectionData(doadorIMC)
											    .withTotalRecords(doadorIMC.size());
			
			return ResponseEntity.ok(builder.build());
		}catch(Exception ex) {
			builder.withMessageError(ex.getMessage())
				   .withTotalRecords(0l);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
		}
	}
	
	@GetMapping(path = "/obesos/genero")
	public ResponseEntity<JsonResult<DoadorObesos>> obesosGenero(){
		Builder<DoadorObesos> builder   = new Builder<DoadorObesos>();
		EntityManager entityManager		  = this.entityManagerFactory.createEntityManager();
		try {
			DoadorObesos doadorObesos = this.doadoresGeneroDao.doadoresObesoPorGenero(entityManager);
			builder = new Builder<DoadorObesos>().withData(doadorObesos)
											       .withTotalRecords(1);
			
			return ResponseEntity.ok(builder.build());
		}catch(Exception ex) {
			builder.withMessageError(ex.getMessage())
				   .withTotalRecords(0l);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.clear();
				entityManager.close();
			}
		}
	}
	
	@GetMapping(path = "/media/idade/sangue")
	public ResponseEntity<JsonResult<DoadorTpSangue>> mediaIdadeSangue(){
		Builder<DoadorTpSangue> builder   = new Builder<DoadorTpSangue>();
		EntityManager entityManager		  	= this.entityManagerFactory.createEntityManager();
		try {
			List<DoadorTpSangue> collection = this.doadoresTipoSanguineoDao.doadoresTipoSanguineoIdadeMedia(entityManager);
			builder = new Builder<DoadorTpSangue>().withCollectionData(collection)
											         .withTotalRecords(collection.size());
			
			return ResponseEntity.ok(builder.build());
		}catch(Exception ex) {
			builder.withMessageError(ex.getMessage())
				   .withTotalRecords(0l);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.clear();
				entityManager.close();
			}
		}
	}
	
}

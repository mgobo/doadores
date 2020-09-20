package br.com.profile.doadores.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.profile.doadores.model.Doadores;
import br.com.profile.doadores.model.Estado;
import br.com.profile.doadores.model.Estado_;
import br.com.profile.doadores.model.json.DoadorEstado;
import br.com.profile.doadores.model.json.DoadorJson;
import br.com.profile.doadores.repositories.EstadoDaoImpl;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EstadoDao {

	@Autowired
	private EstadoDaoImpl estadoDaoImpl;
	
	public List<DoadorEstado> getDoadoresPorEstado(){
		List<Estado> estadoCollection = this.estadoDaoImpl.findAll(Sort.by(Order.asc(Estado_.DESCRIPTION)));
		List<DoadorEstado> pacienteEstado = new ArrayList<>();
		
		estadoCollection.forEach(e->{
			
			DoadorEstado pe = new DoadorEstado();
			List<DoadorJson> doadoresJsonCollection = new ArrayList<>();
			pe.setSiglaEstado(e.getDescription());
			e.getProfileCollection().stream().sorted(Comparator.comparing(Doadores::getNome)).forEach(p->{
				DoadorJson doadoresJson = new DoadorJson(p.getNome(), p.getCpf(), p.getDoadoresTipoSanguineo().getTipo_sanguineo());
				doadoresJsonCollection.add(doadoresJson);
			});
			pe.setDoadoresJsonCollection(doadoresJsonCollection);
			pe.setTotalNoEstado(e.getProfileCollection().size());
			
			pacienteEstado.add(pe);
			
		});
		
		return pacienteEstado;
	}
}

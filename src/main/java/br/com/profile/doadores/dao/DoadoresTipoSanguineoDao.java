package br.com.profile.doadores.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.profile.doadores.model.DoadoresTipoSanguineo;
import br.com.profile.doadores.model.DoadoresTipoSanguineo_;
import br.com.profile.doadores.model.json.DoadorTpSangue;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DoadoresTipoSanguineoDao {

	public List<DoadorTpSangue> doadoresTipoSanguineoIdadeMedia(EntityManager entityManager){
		
		CriteriaBuilder cb 				 		= entityManager.getCriteriaBuilder();
		CriteriaQuery<DoadoresTipoSanguineo> cq = cb.createQuery(DoadoresTipoSanguineo.class);
		Root<DoadoresTipoSanguineo> from		= cq.from(DoadoresTipoSanguineo.class);
		
		TypedQuery<DoadoresTipoSanguineo> typedQuery = entityManager.createQuery(cq.select(from).orderBy(cb.asc(from.get(DoadoresTipoSanguineo_.tipo_sanguineo))));
		List<DoadoresTipoSanguineo> collection  	 = typedQuery.getResultList();
		
		final List<DoadorTpSangue> doadoresTpSangueCollection = new ArrayList<>();
		collection.forEach(tp->{
			DoadorTpSangue doadoresTpSangue = new DoadorTpSangue(tp.getTipo_sanguineo(), (tp.getSomaIdades().doubleValue()/tp.getSomaDoadores().doubleValue()), tp.getSomaDoadores().intValue());
			doadoresTpSangueCollection.add(doadoresTpSangue);
		});
		
		return doadoresTpSangueCollection;
	}
	
}

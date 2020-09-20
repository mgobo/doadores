package br.com.profile.doadores.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.profile.doadores.model.DoadoresGenero;
import br.com.profile.doadores.model.DoadoresGenero_;
import br.com.profile.doadores.model.json.DoadorObesos;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DoadoresGeneroDao {

	public DoadorObesos doadoresObesoPorGenero(EntityManager entityManager){
		
		CriteriaBuilder cb 				 = entityManager.getCriteriaBuilder();
		CriteriaQuery<DoadoresGenero> cq = cb.createQuery(DoadoresGenero.class);
		Root<DoadoresGenero> from		 = cq.from(DoadoresGenero.class);
		
		TypedQuery<DoadoresGenero> typedQuery = entityManager.createQuery(cq.select(from).orderBy(cb.asc(from.get(DoadoresGenero_.id))));
		List<DoadoresGenero> collection  = typedQuery.getResultList();
		
		long mascObesos = collection.stream()
									.filter(p->p.getSexo().equalsIgnoreCase("masculino"))
									.filter(p->p.getImc() > 30d)
									.collect(Collectors.toList()).stream().count();
		
		long masculinos = collection.stream()
									.filter(p->p.getSexo().equalsIgnoreCase("masculino"))
									.collect(Collectors.toList()).stream().count();
		
		long femObesos 	= collection.stream()
									.filter(p->p.getSexo().equalsIgnoreCase("feminino"))
									.filter(p->p.getImc() > 30d)
									.collect(Collectors.toList()).stream().count();
		
		long femininos = collection.stream()
									.filter(p->p.getSexo().equalsIgnoreCase("feminino"))
									.collect(Collectors.toList()).stream().count();
		
		
		Double masculinosObesos = Long.valueOf(mascObesos).doubleValue()/Long.valueOf(masculinos).doubleValue();
		Double femininosObesos  = Long.valueOf(femObesos).doubleValue()/Long.valueOf(femininos).doubleValue();
		
		return new DoadorObesos(masculinos, masculinosObesos*100, femininos, femininosObesos*100, collection.size());
	}
	
}

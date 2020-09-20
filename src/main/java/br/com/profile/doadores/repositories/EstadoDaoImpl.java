package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.profile.doadores.model.Estado;

public interface EstadoDaoImpl extends JpaRepository<Estado, Long>{

	@Query(value = "SELECT e FROM Estado e WHERE e.description = :description")
	Estado findByDescrption(@Param("description") String description);
	
}

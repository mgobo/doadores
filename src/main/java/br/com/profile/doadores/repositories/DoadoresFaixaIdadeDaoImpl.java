package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.profile.doadores.model.DoadoresFaixaIdade;

public interface DoadoresFaixaIdadeDaoImpl extends JpaRepository<DoadoresFaixaIdade, Long> {

	@Query(value = "SELECT d FROM DoadoresFaixaIdade d WHERE d.codigo = :codigo")
	DoadoresFaixaIdade getFaixaIdadeByCodigo(@Param("codigo") String codigo);
}

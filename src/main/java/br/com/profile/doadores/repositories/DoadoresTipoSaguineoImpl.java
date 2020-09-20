package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.profile.doadores.model.DoadoresTipoSanguineo;

public interface DoadoresTipoSaguineoImpl extends JpaRepository<DoadoresTipoSanguineo, Long>{

	@Query("SELECT ts FROM DoadoresTipoSanguineo ts WHERE ts.tipo_sanguineo = :tipo")
	public DoadoresTipoSanguineo getTipoSanguineoByTipo(@Param("tipo") String tipo);
	
}

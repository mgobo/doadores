package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.profile.doadores.model.DoadoresDePara;
import br.com.profile.doadores.model.DoadoresTipoSanguineo;

public interface DoadoresDeParaDaoImpl extends JpaRepository<DoadoresDePara, Long>{

	@Query("SELECT doadores FROM DoadoresDePara doadores WHERE doadores.doadorTipoSanguineoDe.id = :doador AND doadores.doadorTipoSanguineoPara.id = :receptor")
	public DoadoresTipoSanguineo getTipoSanguineoByTipo(@Param("doador") Long doador, @Param("receptor") Long receptor);
	
}

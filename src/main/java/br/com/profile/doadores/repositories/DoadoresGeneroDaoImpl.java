package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.profile.doadores.model.DoadoresGenero;

public interface DoadoresGeneroDaoImpl extends JpaRepository<DoadoresGenero, Long>{

}

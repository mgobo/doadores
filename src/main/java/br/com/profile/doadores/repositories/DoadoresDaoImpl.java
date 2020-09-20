package br.com.profile.doadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.profile.doadores.model.Doadores;

public interface DoadoresDaoImpl extends JpaRepository<Doadores, String> {

}

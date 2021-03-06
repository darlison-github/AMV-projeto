package com.darlisonhenrique.caf.damanha.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>, JpaSpecificationExecutor<Colaborador> {

	Optional<Colaborador> findByCpf(String cpf);

}

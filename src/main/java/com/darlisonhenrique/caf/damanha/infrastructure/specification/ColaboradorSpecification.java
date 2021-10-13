package com.darlisonhenrique.caf.damanha.infrastructure.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.darlisonhenrique.caf.damanha.domain.dto.FiltroColaborador;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;

public class ColaboradorSpecification {

	public static Specification<Colaborador> filtrarColaborador(FiltroColaborador filtroColaborador) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.hasLength(filtroColaborador.getNome())) {
				predicates.add(
						cb.like(cb.upper(root.get("nome")), "%" + filtroColaborador.getNome().toUpperCase() + "%"));
			}

			if (StringUtils.hasLength(filtroColaborador.getCpf())) {
				predicates.add(cb.like(root.get("cpf"), "%" + filtroColaborador.getCpf() + "%"));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}

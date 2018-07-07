package br.com.ivana.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivana.got.domain.Graph;

public interface GraphRepository extends JpaRepository<Graph, Long> {

	Graph findById(long id);
	
}

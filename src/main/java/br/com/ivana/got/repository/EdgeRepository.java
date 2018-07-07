package br.com.ivana.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.ivana.got.domain.Edge;
import br.com.ivana.got.domain.Graph;

public interface EdgeRepository  extends JpaRepository<Edge, Long>{

	Edge findById(long id);
	
	Iterable<Edge> findByGraph(Graph g);
}

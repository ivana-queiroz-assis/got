package br.com.ivana.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ivana.got.domain.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

	Route findById(long id);	
	
	@Query("from Route r where r.source = :source and r.target=:target and r.stops<=:stops")
	List<Route> findByRouteAndStops(@Param("source") String source,@Param("target") String target, @Param("stops")int stops);
}

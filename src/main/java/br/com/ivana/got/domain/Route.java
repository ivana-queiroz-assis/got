package br.com.ivana.got.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String source;
	
	private String target;
	
	private int stops;
	
	private String route;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}	

	public Route(String source, String target, int stops, String route) {
		super();
		this.source = source;
		this.target = target;
		this.stops = stops;
		this.route = route;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Route() {
		super();
	}

	
	
	
}

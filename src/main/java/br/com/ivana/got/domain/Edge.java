package br.com.ivana.got.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonPropertyOrder({"source","target"})
public class Edge implements Serializable {
	
	@Override
	public String toString() {
		return "Edge [id=" + id + ", source=" + source + ", target=" + target + ", distance=" + distance + ", graph="
				+ graph + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669699726787605288L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonProperty("source")
	private String source;
	@JsonProperty("target")
	private String target;
	
	private int distance;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "graph_id")
	@JsonBackReference		
	private Graph graph;
	
	public Edge(Long id, String source, String target, int distance, Graph graph) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
		this.distance = distance;
		this.graph = graph;
	}
	public Edge() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}

	

	
	
}
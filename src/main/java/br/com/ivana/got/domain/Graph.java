package br.com.ivana.got.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="graph")
@JsonPropertyOrder({"data"})
public class Graph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7706217389363678256L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="graph_id")
	private Long id;
	

	@JsonProperty("data")
	@OneToMany(mappedBy="graph", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	@JsonManagedReference
	private List<Edge> listaEdges;
	
	public Graph() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<Edge> getListaEdges() {
		return listaEdges;
	}


	public void setListaEdges(List<Edge> listaEdges) {
		this.listaEdges = listaEdges;
	}


	@Override
	public String toString() {
		return "Graph [id=" + id + ", listaEdges=" + listaEdges + "]";
	}


	
}

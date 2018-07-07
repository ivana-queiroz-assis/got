package br.com.ivana.got.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivana.got.domain.Distance;
import br.com.ivana.got.domain.Edge;
import br.com.ivana.got.domain.Graph;
import br.com.ivana.got.domain.Route;
import br.com.ivana.got.repository.EdgeRepository;
import br.com.ivana.got.repository.GraphRepository;
import br.com.ivana.got.repository.RouteRepository;

@RestController
public class GraphController {

	
	private List<String> listaVertices= new ArrayList<>();	
	 private List<String> pilha = new LinkedList<String>();
	private Map<String, Set<Distance>> mapaDistancias= new HashMap<String,Set<Distance>>();
	private Set<String> verticeMarcadoProf = new HashSet<>();
	private Map<String, List<String>> verticesAdj = new HashMap<String, List<String>>();

	@Autowired
	GraphRepository repoGraph;

	@Autowired
	EdgeRepository repoEdge;

	@Autowired
	RouteRepository repoRoute;

	@GetMapping("/graph")
	public ResponseEntity<Object> getGraph() {

		return ResponseEntity.ok("Ok");
	}

	@PostMapping("/graph")
	public ResponseEntity<Graph> save(@Valid @RequestBody Graph graph, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Graph g = repoGraph.save(graph);
		return ResponseEntity.ok(g);
	}

	@GetMapping("/graph/{id}")
	public ResponseEntity<Graph> getGraph(@PathVariable int id) {
		Graph g = repoGraph.findById(id);
		if (g != null) {
			return ResponseEntity.ok(g);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/routes/from/{source}/to/{target}")
	public List<Route> getRoutes(@Valid @RequestBody Graph graph, BindingResult result,
			@PathVariable String source, @PathVariable String target, @RequestParam("maxStops") int maxStops) {

		
		Graph g = repoGraph.save(graph);
		mapeaGrafo(g);
		
			buscaProfundidade(listaVertices.get(0), 0, listaVertices.get(0));	
			verticeMarcadoProf.clear();
		
		
		return repoRoute.findByRouteAndStops(source, target, maxStops);
	}

	public void mapeaGrafo(Graph g) {

		// mapea os vértices adjacentes de cada vértice
		for (Edge e : repoEdge.findByGraph(g)) {
			List<String> adjacentes;
			if (!this.verticesAdj.containsKey(e.getSource())) {
				adjacentes = new ArrayList<String>();
				adjacentes.add(e.getTarget());
				this.verticesAdj.put(e.getSource(), adjacentes);
			} else {
				adjacentes = verticesAdj.get(e.getSource());
				adjacentes.add(e.getTarget());
				this.verticesAdj.put(e.getSource(), adjacentes);
			}
		
			//mapeia todos os vértices
			if(!listaVertices.contains(e.getSource())) {
				listaVertices.add(e.getSource());
			}
			if(!listaVertices.contains(e.getTarget())) {
				listaVertices.add(e.getTarget());
			}
		}
		
		for(String vert: listaVertices) {
			System.out.println("_______________________Vertice: "+vert);
			for(String v: verticesAdj.get(vert)) {
				System.out.println("Adjvacente: "+v);
			}	
		}

	}
	
	
	
	public void buscaProfundidade(String v, int stops, String rota) {		
               
       
        for (String vAdj : this.verticesAdj.get(v)) {
        	System.out.println("Vertice adjacente: "+vAdj);
            //se o nó adj não foi visitado
            if (!foiMarcado(vAdj) && !rota.contains(vAdj)) {
            	System.out.println("\nNão foi marcado: "+vAdj+" , rota: "+rota+vAdj);
            	Route r= new Route(String.valueOf(rota.charAt(0)),vAdj,++stops,rota+vAdj);
            	repoRoute.save(r);
                buscaProfundidade(vAdj, stops, rota+vAdj );
            }else {
            	System.out.println("\nFoi marcado: "+vAdj+" , rota: "+rota+vAdj);
            	Route r= new Route(v,vAdj,++stops,rota+vAdj);
            	repoRoute.save(r);
            }
            System.out.println("\nNao sei o que aconteceu: : "+vAdj+" , rota: "+rota+vAdj);
            verticeMarcadoProf.add(v);
        }
       
       
    }

	public boolean foiMarcado(String v) {
        for (String vertMarcado : verticeMarcadoProf) {
            if (vertMarcado.equals(v)) {
                return true;
            }
        }
        return false;
    }
	
	 public void inserePilha(String v) {
	        this.pilha.add(v);
	    }

	    public void removePilha(String v) {
	        this.pilha.remove(this.pilha.size() - 1);
	    }


}

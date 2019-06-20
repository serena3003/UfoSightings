package it.polito.tdp.ufo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {
	
	private SightingsDAO dao;
	private Graph<String, DefaultEdge> grafo;
	private Map<Integer, Sighting> sightingMap;

	
	public Model() {
		dao = new SightingsDAO();
		grafo = new SimpleDirectedGraph<>(DefaultEdge.class);
	}
	
	public List<String> getYears(){
		return dao.getYears();
	}

	public void creaGrafo(int year) {
		
		sightingMap = new HashMap<>();
		
		List<Sighting> sightingList = dao.getSightings(year);
		for(Sighting s : sightingList) {
			sightingMap.put(s.getId(), s);
			grafo.addVertex(s.getState());
		}
		
		
		for(Sighting s : sightingList) {
			for(Sighting s1 : sightingList) {
				if(!s1.getState().equals(s.getState())) {
					if(s1.getDatetime().isBefore(s.getDatetime())) {
						grafo.addEdge(s1.getState(), s.getState());
					}
					else {
						grafo.addEdge(s.getState(), s1.getState());
					}
				}
			}
		}
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public Map<Integer, Sighting> getSightingMap() {
		return sightingMap;
	}
	
	
}

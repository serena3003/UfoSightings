package it.polito.tdp.ufo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			//if(grafo.containsVertex(s.getState())) {
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
		//}
		}
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public Map<Integer, Sighting> getSightingMap() {
		return sightingMap;
	}
	
	public List<String> getVertici(){
		List<String> result = new ArrayList<>(grafo.vertexSet());
		Collections.sort(result);
		return result;
	}

	public List<String> getPrecedenti(String state) {
		List<String> result = new ArrayList<String>();
		for(DefaultEdge e : grafo.edgesOf(state)) {
			String source = grafo.getEdgeSource(e);
			if(!source.equals(state))
				result.add(source);
		}
		return result;
	}
	
	public List<String> getSuccessivi(String state) {
		List<String> result = new ArrayList<String>();
		for(DefaultEdge e : grafo.edgesOf(state)) {
			String target = grafo.getEdgeTarget(e);
			if(!target.equals(state))
				result.add(target);
		}
		return result;
	}

	public List<String> getRaggiungibili(String state) {
		List<String> result = new ArrayList<String>();
		List<String> predecessori = getPrecedenti(state);
		List<String> successori = getSuccessivi(state);
		for(String succ : successori) {
			List<String> succList = getSuccessivi(succ);
			for(String s : succList) {
				if(!result.contains(s) && !s.equals(state)) {
					result.add(s);
				}
			}
		}
		
		for(String prec : predecessori) {
			List<String> precList = getPrecedenti(prec);
			for(String p : precList) {
				if(!result.contains(p)&& !p.equals(state)) {
					result.add(p);
				}
			}
		}
		return result;
	}
}

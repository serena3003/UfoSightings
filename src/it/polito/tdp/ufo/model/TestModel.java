package it.polito.tdp.ufo.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo(1945);
		System.out.println("Grafo creato. Vertici: " + model.getGrafo().vertexSet().size() + " e archi " + model.getGrafo().edgeSet().size());
		System.out.println("\n----------\n");
		System.out.println(model.getGrafo().toString());
		
		/*List<String> stateS = model.getSuccessivi("va");
		System.out.println("----SUCCESSORI\n");
		for(String s : stateS)
			System.out.println(s.toString());
		
		List<String> stateP = model.getPrecedenti("va");
		System.out.println("-----PRECEDENTI\n");
		for(String s : stateP)
			System.out.println(s.toString());
		
		List<String> stateR = model.getRaggiungibili("va");
		System.out.println("----RAGGIUNGIBILI\n");
		for(String s : stateR)
			System.out.println(s.toString());*/
		
		List<String> stateR = model.getPercorso("fl");
		for(String s : stateR)
			System.out.println(s.toString());
		
	}

}

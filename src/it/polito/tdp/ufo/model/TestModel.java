package it.polito.tdp.ufo.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo(1949);
		System.out.println("Grafo creato. Vertici: " + model.getGrafo().vertexSet().size() + " e archi " + model.getGrafo().edgeSet().size());
		System.out.println("\n----------\n");
		System.out.println(model.getGrafo().toString());

	}

}

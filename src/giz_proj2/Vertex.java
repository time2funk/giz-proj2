package giz_proj2;

import java.util.ArrayList;

public class Vertex {
	public String name;
	ArrayList<Edge> edgesIn;
	ArrayList<Edge> edgesOut;
	
	public Vertex(String name){
		this.name = name;
		edgesIn = new ArrayList<>();
		edgesOut = new ArrayList<>();
	}
	public void addEdgeIn(Edge e){
		edgesIn.add(e);
	}
	public void addEdgeIn(ArrayList<Edge> e){
		for(int i=0; i<e.size(); i++){
			edgesIn.add(e.get(i));
		}
	}
	public void addEdgeOut(Edge e){
		edgesOut.add(e);
	}
	public void addEdgeOut(ArrayList<Edge> e){
		for(int i=0; i<e.size(); i++){
			edgesOut.add(e.get(i));
		}
	}
}

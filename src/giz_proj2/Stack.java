package giz_proj2;

import java.util.ArrayList;


public class Stack {
	ArrayList<Edge> edges;
	ArrayList<Vertex> vertexes; 
	public Stack(){
		this.edges = new ArrayList<Edge>();
		this.vertexes = new ArrayList<Vertex>();
	}
	
	public void dataParser(Object[][] o){
		for(int i=0; i < o.length; i++){
			String v_f_name = o[i][0].toString();
			String v_s_name = o[i][1].toString();
			int val = Integer.valueOf(o[i][2].toString());
			Vertex start = null;
			Vertex end = null;
			
			for(int j=0; j<this.vertexes.size(); j++ ){
				if( this.vertexes.get(j).name.equals(v_f_name) ){
					start = this.vertexes.get(j);
				}
				if( this.vertexes.get(j).name.equals(v_s_name) ){
					end = this.vertexes.get(j);
				}
			}
			if(start == null){
				start = new Vertex(v_f_name);
				this.vertexes.add(start);
				System.out.println("Vertex [" + v_f_name + "] is created");
			}	
			if(end == null){
				end = new Vertex(v_s_name);
				this.vertexes.add(end);
				System.out.println("Vertex [" + v_s_name + "] is created");
			}	
			Edge tmp = new Edge(start,end,val);
			this.edges.add(tmp);
			
			start.addEdgeOut(tmp);
			end.addEdgeIn(tmp);
			System.out.println("Edge is created");
		}
		
		System.out.println(">> Data is parsed");		
	}
	
	public void maxFlow(String start_name, String end_name){
		if(this.edges.size() > 0 && this.vertexes.size() > 1){
			Vertex start = null;
			Vertex end = null;
			
			for(int j=0; j<this.vertexes.size(); j++ ){
				if( this.vertexes.get(j).name.equals(start_name) ){
					start = this.vertexes.get(j);
				}
				if( this.vertexes.get(j).name.equals(end_name) ){
					end = this.vertexes.get(j);
				}
			}
			if(start == null || end == null){
				System.out.println("!> wrond Vertex name");
			}else{
				
				alKarpa(start, end);
				
			}
		}
	}
	private int alKarpa(Vertex start, Vertex end){
		System.out.println(">> startVertex:"+start.name + "| endVertex:" + end.name);
		
		boolean running = true;
		do{
			running = start.dfs(end);
			System.out.println("_________________________");
		}while( running );
		
		System.out.println(end.sumOutEdges());
		
		return end.sumOutEdges();
	}
	
}

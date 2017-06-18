package giz_proj2;

import java.util.ArrayList;
import java.util.LinkedList;

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
	public int sumOutEdges(){
		int res = 0;
		for(Edge e : this.edgesOut)
			res += e.flow;
		return res;
	}

	public boolean dfs(Vertex end){
		
		LinkedList<Edge> Elist = new LinkedList<>();
		
		ArrayList<Vertex> vertex_stack = new ArrayList<>();
		vertex_stack.add(this);
		
		return dfs(end, Elist, vertex_stack);
	}
	
	public boolean dfs(Vertex end, LinkedList<Edge> Elist, ArrayList<Vertex> Vstack){
		System.out.print("|dfs|: ");//CONSOLE
		
		////////////////////////////////////////////
		if(this == end){
			System.out.println("Finish!");//CONSOLE			
			for(Edge e : Elist)//CONSOLE
				System.out.println("- "+e.start.name+","+e.end.name+','+e.flow);
			Edge.push(Elist);//PUSH MINFLOW
			Elist.clear();
			Vstack.clear();
			return true;
		}
		if(this.edgesOut.size() == 0)
			return false;
		////////////////////////////////////////////

		Edge way = null;
				
		for(int i=0; i < this.edgesOut.size(); i++){
			if(this.edgesOut.get(i).flow <= 0)
				continue;

			Vertex tmpV = this.edgesOut.get(i).end;
			boolean flag=true;
			
			for(int j=0; j < Vstack.size(); j++){
				if(tmpV == Vstack.get(j))						
					flag = false;
			}
			if(!flag)
				continue;
				
				
			if(way == null){
				way = this.edgesOut.get(i);
			}else if(this.edgesOut.get(i).flow < way.flow){
				way = this.edgesOut.get(i);
			}
			
		}
		if(way == null)
		{
			System.out.println("stepback");
			return false;
		}
		Elist.add(way);
		Vstack.add(way.end);
		
		System.out.println(this.name+"->"+way.end.name);//CONSOLE
		
		boolean dfsresult = way.end.dfs(end, Elist, Vstack);
		
		if(!dfsresult){
			Elist.removeLast();
			if(this.dfs(end, Elist,Vstack) ){
				return true;
			}else{
				return false ;
				
			}
		}
		else{
			return true;
		}
		
	}

}

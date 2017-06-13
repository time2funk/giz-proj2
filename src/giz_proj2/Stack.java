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
//		ArrayList<Edge> stackEdge = new ArrayList<Edge>();
		
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
	private void alKarpa(Vertex start, Vertex end){
		System.out.println(">> strat V:"+start.name + " end V:" + end.name);
		
		ArrayList<Vertex> tmp = new ArrayList<>();
		int result = dns(tmp, start, end, -1);
		
		System.out.println(result);
	}
	
	private int dns(ArrayList<Vertex> stack, Vertex start, Vertex end, int minflow){
//		System.out.print("[ ! ]");
		Vertex tmpV = start;
		stack.add(tmpV);
		
		if(start != end){
			Edge way = tmpV.edgesOut.get(0);
			
			for(int i=1; i<tmpV.edgesOut.size(); i++){
				if(tmpV.edgesOut.get(i).flow < way.flow){					
					boolean bool = true;
//					for(int j=0; j<stack.size(); j++){						
//						if(stack.get(j).name.equals( tmpV.edgesOut.get(i).end.name )){
//							bool = false;
//						}						
//					}
					if(bool){
						way = tmpV.edgesOut.get(i);					
					}
				}
			}
			
			if(minflow == -1) 
				minflow = way.flow;
			else
				if(minflow > way.flow) 
					minflow = way.flow;
			
			System.out.println(">> From ["+start.name+"] to ["+way.end.name+"]");
			System.out.println(">> -flow ["+way.flow+"]");
			System.out.println(">> -minflow ["+minflow+"]");
			
			minflow = dns(stack, way.end, end, minflow);	
			way.fill = minflow;
		}
		
		return minflow;
	}
}

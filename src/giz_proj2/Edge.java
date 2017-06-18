package giz_proj2;

import java.util.Iterator;
import java.util.LinkedList;

//import java.util.ArrayList;

public class Edge {
	public Vertex start;
	public Vertex end;
	public int flow;
	
	public Edge(Vertex s, Vertex e, int flow){
		this.start = s;
		this.end = e;
		if(flow <= 0){
			System.out.println("!> error (wrong value)");
			try {
				throw new Exception("");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
			
		this.flow = flow;
	}
	public void del(){
		this.start.edgesOut.remove(this);
		this.end.edgesIn.remove(this);
	}
	public static void push(LinkedList<Edge> list){
        Iterator<Edge> iter = list.iterator(); 
        
//        MINFLOW
        int minflow = list.get(0).flow;
        
        for(int i=1; i<list.size(); i++){
        	if(list.get(i).flow < minflow)
        		minflow = list.get(i).flow;
        	else
        		continue;
        }
//        PUSH
        while(iter.hasNext()){
        	Edge E = iter.next();
//        	II_______________________II
        	E.flow -= minflow;
			
        	Vertex a = E.start;
        	Vertex b = E.end;
//        	I_________________________I
        	boolean flag = false;
        	for(int i=0;i<b.edgesOut.size();i++){
        		Edge edge = b.edgesOut.get(i);
        		
        		if(edge.end == a){
        			edge.flow += minflow;	
        			flag = true;
        		}
        	}
        	if(!flag){
    			System.out.println("create Edge <"+E.end.name+E.start.name+">");
            	Edge tmpE = new Edge(b,a,minflow);
            	a.edgesIn.add(tmpE);
            	b.edgesOut.add(tmpE);
        	}
        	
			if(E.flow == 0){
				System.out.println("remove Edge <"+E.start.name+E.end.name+">");
				E.del();
			}
        }
    	System.out.println("push minflow:"+minflow);
	}
}

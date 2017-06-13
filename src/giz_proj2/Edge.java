package giz_proj2;

//import java.util.ArrayList;

public class Edge {
	public Vertex start;
	public Vertex end;
	public int flow;
	public int fill;
	
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
		this.fill = 0;
	}
}

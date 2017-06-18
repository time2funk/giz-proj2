package giz_proj2;

import java.util.Arrays;

//[a,b,4]
//[b,c,3]
//[c,d,6]
//[a,e,6]
//[e,b,4]
//[e,c,4]
//[e,f,3]
//[f,d,5]
//from 'a' to 'd'
public class Main {

	public static void main(String[] args) {
		System.out.println(">> Start");
		
		MyFileReader fr = new MyFileReader("src/giz_proj2/data.txt");
		String text = fr.getString();
		MyTextParser tp = new MyTextParser(text);
		
		Object[][]arr = tp.arr; // now we have array from the file
		for(int i=0; i < arr.length; i++){
			System.out.println(Arrays.toString(arr[i]));
		}
		Stack stack = new Stack();
		stack.dataParser(arr);
		stack.maxFlow("a","d");//vertex start , end name
	}

}

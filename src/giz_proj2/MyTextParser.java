package giz_proj2;


public class MyTextParser {
	public Object [][] arr;
	
	public MyTextParser(String text) {
		arr = null; 
		
		for (int i = 0; i < text.length(); i++) {
			String pice = "";

			if (text.charAt(i) == '[') {
				i++;
				arrayUp();
				for (; i < text.length(); i++) {
					if (text.charAt(i) == ']') {
						String[] parts = pice.split(",");
//						arr[arr.length-1][0] = Integer.parseInt(parts[0]);
//						arr[arr.length-1][1] = Integer.parseInt(parts[1]);	
//						arr[arr.length-1][2] = Integer.parseInt(parts[2]);
						arr[arr.length-1][0] = parts[0];
						arr[arr.length-1][1] = parts[1];	
						arr[arr.length-1][2] = parts[2];						
						break;
					}
					pice += text.charAt(i);
				}
			}
		}
//		for(int i = 0; i < arr.length; i++){
//			System.out.println( arr[i][0]+","+arr[i][1] );
//		}
		System.out.println(">> The text is parsed");
	}
	
	private void arrayUp(){
		if(arr == null){
			arr = new Object[1][3];
		}else{
			Object tmp[][] = new Object [arr.length+1][3];
			for(int i = 0; i < arr.length; i++)
				for(int j = 0; j < arr[i].length; j++)
					tmp[i][j] = arr[i][j];
			arr = tmp;
		}
	}
}

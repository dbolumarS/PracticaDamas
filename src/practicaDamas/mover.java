package practicaDamas;

import java.util.Scanner;

public class mover {
	public static void main(String[] args) {
		
	Scanner tec = new Scanner(System.in);
	
        int[][] matriz = {
        	{1,2,3,4,5},
        	{1,2,3,4,5},
        	{1,2,3,4,5},
        	{1,2,3,4,5},
        	{1,2,3,4,5}
        };
        
        int[] diagoPrincipal = new int[matriz.length];
        int[] diagoSecundaria = new int[matriz.length];
 
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                if(i==j){
                    diagoPrincipal[i] = matriz[i][j];
                }
 
                if(i+j == matriz.length-1){
                    diagoSecundaria[i] = matriz[i][j];
                }
            }
        }
        System.out.println("\nDiagonal Principal");
        
 
        System.out.println("\n\nDiagonal Secundaria");
      
        
 
        System.out.println("\n\n");
}

	
		
	}

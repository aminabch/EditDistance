import java.util.*;

public class EditDistance implements EditDistanceInterface {

    int c_i, c_d, c_r;
    int MAX = Integer.MAX_VALUE;
    int UNDEF = -1;
        
    public EditDistance(int c_i, int c_d, int c_r){
        this.c_i = c_i;
        this.c_d = c_d;
        this.c_r = c_r;
    }
        
    public int[][] editDistance(String s1, String s2) {
  	
    int n = s1.length();
    int m = s2.length();
    
    int[][] d = new int [n+1][m+1];
    
    for(int i =0; i<n+1; i++)
    	for(int j =0; j<m+1;j++)
    		d[i][j]=UNDEF;
      
    editDistanceAux(s1,n,s2,m,d);  		
   	   
        return d;
    }

    public void editDistanceAux(String s1, int i, String s2, int j, int[][]d) {
        /* To be completed in Q2 in order to optimize editDistance */    
    	if(i==0){
      	   d[0][j]=j*this.c_i;
      	   return;
    	}      	  
      	 if(j==0){
      		d[i][0]=i*this.c_d;
      		return;
      	 }
      	

    	if(d[i-1][j-1]==-1){	
    	editDistanceAux(s1, i-1, s2,j-1, d); 
    	}
    	int min=d[i-1][j-1];     
    	
		if(s1.charAt(i-1)!=s2.charAt(j-1)){
			min = d[i-1][j-1]+this.c_r;
		   }		
    	if(this.c_d<min){
    		if(d[i-1][j]==-1){	
    			editDistanceAux(s1, i-1, s2,j, d); 
    	    	}
    		min = Math.min(min, d[i-1][j]+this.c_d);
    	}
    	if(this.c_i<min){
    		if(d[i][j-1]==-1){	
    			editDistanceAux(s1, i, s2,j-1, d); 
    	    	}
    		min = Math.min(min, d[i][j-1]+this.c_i);    // Tu avais mal place la prenthese
    	}	    	
    	d[i][j]=min;  //met a jour la valeur de min    	   
    }
    
//------------------QUESTION 3 ---------------
    
    //modification des deux fonctions 

    public void editDistanceAuxBis(String s1, int i, String s2, int j, int[][]d, String [][]op) {
        /* To be completed in Q2 in order to optimize editDistance */    
    
    	
    	String s = ""; 
    	if(i==0){
      	   d[0][j]=j*this.c_i;
      	   return;
    	}      	  
      	 if(j==0){
      		d[i][0]=i*this.c_d;
      		return;
      	 }
      	

    	if(d[i-1][j-1]==-1){	
    	editDistanceAuxBis(s1, i-1, s2,j-1, d,op); 
    	}
    	
    	int min=d[i-1][j-1];     
    	
		if(s1.charAt(i-1)!=s2.charAt(j-1)){
			min = d[i-1][j-1]+this.c_r;
			s = "remplacement";
		   }		
		
    	if(this.c_d<min){
    		if(d[i-1][j]==-1){	
    			editDistanceAuxBis(s1, i-1, s2,j, d,op); 
    	    	}
    		if(d[i-1][j]+this.c_d<min){
    		min = Math.min(min, d[i-1][j]+this.c_d);
    		s = "deletion";
    		}
    	}
    	if(this.c_i<min){
    		if(d[i][j-1]==-1){	
    			editDistanceAuxBis(s1, i, s2,j-1, d,op); 
    	    	}
    		if(Math.min(min, d[i][j-1]+this.c_i)<min){
    		min = Math.min(min, d[i][j-1]+this.c_i); 
    		s = "insertion";
    		}// Tu avais mal place la prenthese
    	}	    	
    	d[i][j]=min;
    	op[i][j]=s;//met a jour la valeur de min    	   
    }
    
   
    
  //modification de editDistanceBis 
   
    
    //construction de la liste 
    
    public List<String> minimalEdits(String s1, String s2) {
        /* To be completed (for extra credit) in Q3 */
    	
    	LinkedList<String> L = new LinkedList<String>();
    	int m = s1.length();
    	int n = s2.length();
    	
    	 int[][] d = new int [m+1][n+1];
    	 String [][] op = new String[m+1][n+1];
    	 
    	 for(int i = 0; i<n ;i++)	 
    			 op[i][0]="deletion";
    	 
    	 for(int j = 0; j<n ;j++)	 
			 op[0][j]="insertion";
    	    
    	   for(int i =0; i<m+1; i++)
    	    	for(int j =0; j<n+1;j++){
    	    		d[i][j]=UNDEF;
    	    		op[i][j]=""; 	    		
    	    	}
    	   editDistanceAuxBis(s1, m,  s2, n, d, op);    
    	   int i = n; 
    	   int j = m;
    	  
    	   
    	   
    	   while (i>0 && j>0) { 
    		   
    		   if(op[i][j].equals("remplacement")){  
    			   L.add(op[i][j]+"("+(i-1)+","+s2.charAt(j-1)+")");
    			   i--;
    			   j--;
    			  
    		   }   		   
    		   else if(op[i][j].equals("deletion")){
    			   L.add(op[i][j]+"("+(i-1)+")");
    			   i--;
    			  			
    		   }
    		   else if(op[i][j].equals("insertion")){
    			   L.add(op[i][j]+"("+(i)+","+s2.charAt(j-1)+")");
    			   j--;
    			  
    		   }	 
    		   else{i--;j--;}
   	   }
    	   
    	return L;
}
    
}

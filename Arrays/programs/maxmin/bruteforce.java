// this program finds max and min of
// element in an array using brute force approach 

import java.util.*;

public class bruteforce {
    public static void main(String[] args) {
           
      Scanner sc = new Scanner(System.in);
      int size = sc.nextInt();
    
      int[] array = new int[size];
      for(int i=0; i<size ; i++){
          array[i] = sc.nextInt();
      }

      int max= array[0];
      int min= array[0];

      for(int i=0 ; i<size ; i++){
            if(array[i]>max){
                max = array[i];
            }
      }

      System.out.println("The maximum element in this array is: " + max);

      for(int i=0 ; i<size ; i++){
         if(array[i]<min){
             min = array[i];
         }
      }

      System.out.println("The minimum element in this array is: " + min);
   
      //Sum of array 

      int sum = 0;
      for(int x: array){
          sum+=x;
      }
      System.out.println(sum);
    }
}

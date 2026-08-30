import java.util.*;

class arrays {
    
    public static void main(String[] args) {
    
      Scanner sc = new Scanner(System.in);
      int size = sc.nextInt();
    
      int[] array = new int[size];
      for(int i=0; i<size ; i++){
          array[i] = sc.nextInt();
      }
      System.out.println("\n" + array.length);

      System.out.println(array); //this will not print array
   
      // Here we are converted array to string 
      System.out.println(Arrays.toString(array));
    }
}

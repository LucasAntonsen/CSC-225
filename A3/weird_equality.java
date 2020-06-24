/* By Lucas Antonsen
 * 
 * Problem Description by Prof. Richard Little
 * 
 * Alice and Bob each have an array of integers, namely A and B. A and B have the same size, say n. Alice and Bob
 * have come up with a new definition called "weird equality": two arrays A and B are weirdly equal to each other if at
 * least one of the following four conditions is met:
 * 
 * 1) A = B, meaning that the elements in the corresponding indices are the same.
 * 
 * If n is divisible by 2, we divide each of the arrays into 2 parts of equal size. So, A will be divided into A1 and A2 (from
 * left to right). Similarly, B is divided into B1 and B2. Now, the following conditions are checked and if any of them is
 * satisfied we can still say that the arrays are weirdly equal to each other.
 * 
 * 2) (A1 is weird equal to B1) AND (A2 is weird equal to B2)
 * 
 * 3) (A1 is weird equal to B1) AND (A2 is weird equal to B2)
 * 
 * 4) (A2 is weird equal to B2) AND (A2 is weird equal to B1)
 * 
 * Note: if n is not divisible by 2, then conditions 2, 3, and 4 are NOT satisfied.
 * 
 * At the moment, Alice and Bob are thinking day and night to determine whether their arrays satisfy the weird equality
 * or not. Write a program for them to determine this and end their pain!
 * 
 * Input Format
 * 
 * First line has an integer 1<= n <=10^4. Second line has n integers of array a0, a1, ..., an-1 of array A, and the third line has
 * n integers b0, b1,...,bn-1 of array B.
 * 
 * Constraints
 * 
 * 0<= ai <=10^8
 * 0<= bi <=10^8
 * 
 * Output Format
 * 
 * On one line print "YES" if the arrays are weird equal to each other, and "NO" if they are not. This output is case-sensitive
 * and the quotes are just for clarity.
 * 
 */
import java.util.*;
public class weird_equality {
	
	public static boolean arrayEqual(int[] a, int[] b, int n) {
		
		//first check if a = b
		
		//arrays are empty						runtime determined by number of comparisons and assignments
		if (n == 0) {							//1C
			return false;						//1A
		}
		
		//arrays have one element
		if (n == 1) {							//1C
			return a[0] == b[0];				//1C+1A
		}
		
		//arrays have odd number of elements. we cannot divide the array any further given the problem description
		//so we must compare the two arrays element by element
		if (n % 2 != 0) {						//1C
			int same = 0;						//1A
			
			//check if arrays are the same
			for (int x = 0; x < n; x++) {		//n*(1A+1C)+1A
				if(a[x] == b[x]) {				//nC
					same++;						//nA
				}
			}
			//if n, the number of elements is equal to same, the number of elements which are identical,
			//then return true, else false
			return n == same;					//1C+1A
		}
		
		//arrays have even number of elements
		
		//make four subarrays of equal size
		int[] a1 = new int[n/2];				//1A
		int[] a2 = new int[n/2];				//1A
		int[] b1 = new int[n/2];				//1A
		int[] b2 = new int[n/2];				//1A
		
		//copy elements to each subarray from original array
		for (int i = 0; i < (n/2); i++) {		//n/2*(1A+1C)+1A
			a1[i] = a[i];						//n/2*(1A+
			a2[i] = a[n/2+i];					//1A+
			b1[i] = b[i];						//1A+
			b2[i] = b[n/2+i];					//1A)	
		}
												//6T(n/2)+1A
		
		//recursively check for cases 2 to 4. case 1, A = B is covered by case 2
		return (arrayEqual(a1, b1, n/2) && arrayEqual(a2, b2, n/2)) || (arrayEqual(a1, b1, n/2) && arrayEqual(a1, b2, n/2)) 
				|| (arrayEqual(a2, b2, n/2) && arrayEqual(a2, b1, n/2));
	}
	
	public static void main (String[] args){
		
		//receive input
		Scanner input = new Scanner(System.in);
		int size = Integer.parseInt(input.nextLine());
		String str1 = input.nextLine();
		String str2 = input.nextLine();
		
		//create string arrays
		String[] arrstr1 = str1.split(" ");
		String[] arrstr2 = str2.split(" ");
		
		//create int arrays from string arrays
		int[] arr1 = new int[size];
		int[] arr2 = new int[size];
		for(int y = 0 ; y < size; y++) {
	         arr1[y] = Integer.parseInt(arrstr1[y]);
	         arr2[y] = Integer.parseInt(arrstr2[y]);
	    }
		input.close();
		
		if (arrayEqual(arr1, arr2, size) == true) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}
}


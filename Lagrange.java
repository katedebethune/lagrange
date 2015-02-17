/*
 * Lagrange.java
 *
 * Computer Science E-22
 */

import java.util.*;

/**
 * An application that reads positive integers from the user and
 * breaks them into sums of at most four positive perfect squares.
 */
public class Lagrange {
    private int number;      // the number we are trying to break up
	private int[] terms;

    /**
     * constructor
     */
    public Lagrange(int num) {
        number = num;
		terms = new int[4];
    }
	/*
     * largestSquare - a private helper method that returns the 
     * largest perfect square less than or equal to the specified 
     * positive integer n.
     */
    private static int largestSquare(int n) {
        int sqrt = (int)Math.sqrt(n);
        return sqrt * sqrt;
    }
    /**
     * findSum - the key recursive-backtracking method.
     * We call it to break the specified number (num) into a sum 
     * of at most maxTerms perfect squares.  
     * Returns true if the solution has been found and false otherwise.
     * 
     * NOTE: If you choose to modify the signature of this method,
     * you must also change the code in the main method that
     * uses the method.
     */
    public boolean findSum(int num, int maxTerms) {
		if (num == 0 && maxTerms >= 0) {  //base case
			return true; //solution found
		}
		if (num != 0 && maxTerms==0) {
			return false; //backtrack
		}
		//for extra credit "make it smarter"
		if (maxTerms==2 && num==3) {
			return false; //backtrack
		}
		for (int i = (int)Math.sqrt(num); i>0; i--) {
			if(num >= i * i && maxTerms>0) {
				applyInt(i * i, maxTerms);
			}
				if(findSum(num-(i*i), maxTerms-1)) {
					return true;
				}
				removeInt(num, maxTerms);
		}
		return false;
	}	      
	public void applyInt(int num, int term) {
		terms[terms.length-term] = num;
	}
	public void removeInt(int num, int term) {
		terms[terms.length - term] = 0;
	}
    
	/**
     * printSolution - print the solution to the problem
     */
    public void printSolution() {
		//Solution display as required by assignment
		System.out.println();
		System.out.print(number + " = ");
		int countTerms = 0;
		for (int i = 0; i<terms.length; i++) {
			if (terms[i]>0) {
				countTerms++;
			}
		}
		for (int j = 0; j < countTerms; j++) {
			if (countTerms==1) {
				System.out.print(terms[j]);
			}
			else if (j < countTerms-1) {
				System.out.print(terms[j] + " + "); 
			}
			else {
				System.out.println(terms[j]);
			}
		}
	}

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a positive integer (-1 to quit): ");
            int n = console.nextInt();
            console.nextLine();
            
            if (n == -1) {
                System.out.println("Goodbye!");
                return;
            } else if (n <= 0)
                continue;
    
            Lagrange problem = new Lagrange(n);
            
            if (problem.findSum(n, 4)) {
                problem.printSolution();
            } else {
                System.out.println("could not find a sum for " + n);
                System.out.println();
            }
            System.out.println();
        }
    }
}


package sourabhs.datastructures.array;

/**
 * @author Sourabh
 * 
 * LeetCode
 * 
 * Suppose you are at a party with n people (labelled from 0 to n - 1) and among them,
 * there may exist one celebrity. The definition of a celebrity is that all the other
 * n - 1 people know him/her but he/she does not know any of them.
 * 
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?"
 * to get information of whether A knows B. You need to find out the celebrity 
 * (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * 
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. 
 * Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 * 
 */

public class FindTheCelebrity {
	
	int[][] neighbour;
	public int findCelebrity(int n) {
		int candidate = 0;
		for (int i = 1; i < n; i++) {
			if (knows(candidate, i))
				candidate = i;
		}
		
		for (int i = 0; i < n; i++) {
			if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) 
				return -1;
		}
		
		return candidate;
	}
	
	public boolean knows(int a, int b) {
	    boolean doesKnows = false;

		if(neighbour.length < a || neighbour.length < b)
			doesKnows = false;
		else 
			doesKnows = neighbour[a][b]  >= 1 ? true : false;
		return doesKnows;
	}

	public static void main(String[] args) {
		int n = 4;
		FindTheCelebrity obj = new FindTheCelebrity();
		obj.neighbour = new int[][] {{0, 1, 1, 0},
		    						 {0, 0, 1, 0},
		    						 {0, 0, 0, 0},
		    						 {0, 0, 1, 0}};
		int id = obj.findCelebrity(n);
	    
		if (id == -1)
	    	System.out.println("No celebrity");
	    else
	    	System.out.println("Celebrity ID "+ id);
	}
}

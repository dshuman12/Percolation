//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Random;
public class Percolation
{            
	private boolean[][] open;
    private WeightedQuickUnionUF connections;
    private int[][] array;
    private int N;
    private int numOpen;
    public Percolation(int myN)// create N-by-N grid, with all sites blocked
    {
        this.N = myN;
    	if (N <= 0)
        	throw new java.lang.IllegalArgumentException();
        open = new boolean[N][N];
        connections = new WeightedQuickUnionUF(N*N+2);
        array = new int[N][N]; //id array, sets an id to each
        int counter = 0;
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                open[r][c] = false;
                array[r][c] = counter;
                counter++;
            } 
        }
        
        
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                
            }
        }
        numOpen = 0; //runs the test case of random
        while(!percolates()){
        	
	        int i = (int)(Math.random()*N+1);
	        int j = (int)(Math.random()*N+1);
	        if (open[i-1][j-1])
	        	continue;
	        open(i,j);
	        numOpen++;
	    }
    }
    public double getPercentNumOpen()
    {
    	/*System.out.println("numOpen: " + numOpen);
    	System.out.println("N*N: " + (N*N));
    	System.out.println("PercentNumOpen: " + (1.0*numOpen)/(1.0*N*N));*/
    	return (1.0*numOpen)/(1.0*N*N);
    }
    public void open(int i, int j)
    {
    	if(i < 1 || i > N || j < 1 || j > N)
    		throw new java.lang.IndexOutOfBoundsException();
    	open[i-1][j-1] = true;
		if (i != 1 && isOpen(i-1,j))//checking to see if the box above is open
			connections.union(array[i-1][j-1], array[i-2][j-1]); //any reference to array[][] or open[][] must subtract 1
		if (i < N && isOpen(i+1, j))
			connections.union(array[i-1][j-1], array[i][j-1]);//checking to see if the box below is open
		if (j != 1 && isOpen(i,j-1))
			connections.union(array[i-1][j-1], array[i-1][j-2]);
		if (j < N && isOpen(i,j+1))
			connections.union(array[i-1][j-1], array[i-1][j]);
    }

    public boolean isOpen(int i, int j)
    {
    	if(i < 1 || i > N || j < 1 || j > N)
    		throw new java.lang.IndexOutOfBoundsException(); //make sure the i and j is in the correct range
    	return open[i-1][j-1]; //whenever open[][] or array[][] is referenced 
    }

    public boolean isFull(int i, int j)
    {
    	if(i < 1 || i > N || j < 1 || j > N)
    		throw new java.lang.IndexOutOfBoundsException(); //make sure i and j are in range
    	if (open[i-1][j-1] == false) //make sure its open
    		return false;
    	if (i == 1) //first line
    		return true;
		for (int k = 0; k < N; k++) //checks if matches id of any top value
			if (connections.find(array[i-1][j-1]) == connections.find(array[0][k]))
				return true;
		return false;
    }

    public boolean percolates() //checks if any bottom value is full. 
    {
    	for (int k = 1; k < N; k++)
			if (isFull(N, k))
				return true;
    	return false;
    }
}

public class PercolationStats
{
    int T;
    double[] array;
    int N;
	public PercolationStats(int N, int myT)
    {
		this.N = N;
		this.T = myT;
		this.array = new double[T];
		Percolation p = new Percolation(N);
		
		for (int i = 0; i < T; i++)
		{
			p = new Percolation(N); //to do many different percolation
			array[i] = (p.getPercentNumOpen());
			System.out.println("array[i]: " + array[i]);
		}        
    }
    
    public double mean()
    {
        double sum = 0; 
    	for (int i = 0; i < T; i++)
        {
        	sum+=array[i];
        }
    	return sum/T;
    }
    
    public double stddev()
    {
        double sum = 0;
        for (int i = 0; i < T; i++)
        {
        	sum += (array[i] - mean())*(array[i] - mean());
        }
        return Math.sqrt(sum/T);
    }
    
    public double confidenceLo()
    {
        //z of .95 = 1.96
    	return mean() - (stddev()/((Math.sqrt(T))*1.96));
    }
    
    public double confidenceHi()
    {
    	return mean() + (stddev()/((Math.sqrt(T))*1.96));
    }
    
   public static void main(String[] args)
    {
	   	if (args.length < 2)
	   		return;
	   	int N = Integer.parseInt(args[0]);
	   	int T = Integer.parseInt(args[1]);
	    PercolationStats pStats = new PercolationStats(N, T);
    	System.out.println("% java PercolationStats " + args[0] + " " + args[1]);
    	System.out.println("mean = " + pStats.mean());
    	System.out.println("stddev = " + pStats.stddev());
    	System.out.println("95% confidence interval = " + pStats.confidenceLo() + ", " + pStats.confidenceHi());
    }
    
}
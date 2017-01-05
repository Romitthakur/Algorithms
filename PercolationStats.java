import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
 
	private double[] trialResults;
	
	// perform trials independent experiments on an n-by-n grid
	// Construct Percolation object with n items
	// Construct array of size trails
	// Perform experiment for each trail - Generate open sites randomly
	// until system percolates and save open sites value in array
	public PercolationStats(int n, int trials){
		
		if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
	
		trialResults = new double[trials];
		
		for(int i = 0; i < trials; i++){
			Percolation per = new Percolation(n);
			while(!per.percolates()){
				int p = StdRandom.uniform(n);
				int q = StdRandom.uniform(n);
				per.open(p+1, q+1); 
			}
			trialResults[i] = per.numberOfOpenSites()/(double)(n*n);
		}
	}
	
	// sample mean of percolation threshold
	// Calculate mean using library function
	public double mean(){
		return StdStats.mean(trialResults);
	}
   
	// sample standard deviation of percolation threshold
	public double stddev(){
		return StdStats.stddev(trialResults);
	}
   
	public double confidenceLo(){
		// low  endpoint of 95% confidence interval
		double cLo = mean() - (1.96 * stddev()) / Math.sqrt(trialResults.length);
		return cLo;
	}
   
	public double confidenceHi(){
		// high endpoint of 95% confidence interval
		double cHi = mean() + (1.96 * stddev()) / Math.sqrt(trialResults.length);
		return cHi;
	}

	public static void main(String[] args){
		// test client (described below)
		
		String n = args[0];
		String T = args[1];
		
//		String n = "200";
//		String T = "100";

		
		PercolationStats pS = new PercolationStats(Integer.parseInt(n), Integer.parseInt(T));
		
		StdOut.println("mean                    = " + pS.mean());
		StdOut.println("stddev                  = " + pS.stddev());
		StdOut.println("95% confidence interval = " + pS.confidenceLo() + ", " + pS.confidenceHi());
		/*
		int n = 2;
		int T = 100000;
		
		PercolationStats pS = new PercolationStats(n, T);
		System.out.println("mean = " + pS.mean());
		System.out.println("stddev = " + pS.stddev());
		System.out.println("95% confidence interval = " + pS.confidenceLo() + ", " + pS.confidenceHi());
		*/
	}
}
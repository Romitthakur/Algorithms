import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int N;
	private WeightedQuickUnionUF WQUF;
	private int[][] grid;
	private int openSites;
	
	// create n-by-n grid, with all sites blocked
	// 0 -> blocked sites, 1 -> open site
	// Allocate WQUF array having N*N sites + 2 virtual sites
	// N variable for margin adjustments
	
	public Percolation(int n) {
		if (n <= 0 ) throw new java.lang.IllegalArgumentException();
		grid = new int[n][n];
		WQUF = new WeightedQuickUnionUF(n*n + 2);
		N = n;
		openSites = 0;
	}
	   
	// open site (row, col) if it is not open already
	// Check bounds
	// if open site is in top or bottom - connect to virtual sites
	// Open site in grid and connect open sites in WQUF 
	// First make direction arrays dx and dy
	// Connect left, right, bottom and up sites if not connected to original site
	
	public void open(int row, int col){
		int Row = row - 1; //Removing margin
		int Col = col - 1; //Removing margin
		if (!checkBounds(Row, Col))  throw new java.lang.IndexOutOfBoundsException();
		
		if (grid[Row][Col] == 1) return; //Already site is open
		grid[Row][Col] = 1;
		openSites++;
		
		int site = Row * N + Col;
		
		if (Row == 0) 		WQUF.union(site, N*N); 		// connecting top row open site to virtual site 1
		if (Row == N-1) 	WQUF.union(site,  N*N + 1);	// connecting bottom row open site to virtual site 2 
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		for (int i = 0; i < 4; i++){
			int x = Row + dx[i];
			int y = Col + dy[i];
			
			if (checkBounds(x, y)){
				if (grid[x][y] == 1){
					int newSite = x * N + y;
					WQUF.union(newSite, site);
				}
			}
		}
	}
	
	// is site (row, col) open?
	// Check bounds 
	// Check if grid[row][col] is 1
	
	public boolean isOpen(int row, int col){
		if(checkBounds(row - 1, col - 1)){
			if (grid[row - 1][col - 1] == 1) return true;
		}else{
			throw new java.lang.IndexOutOfBoundsException();
		}
		return false;
	}
	
	// is site (row, col) full?
	// check if given site is connected to top virtual site
	
	public boolean isFull(int row, int col){
		if (checkBounds(row - 1, col - 1)){
			int newSite = (row-1) * N + (col-1);
			if (WQUF.connected(N * N , newSite)) return true;
		}else{
			throw new java.lang.IndexOutOfBoundsException();
		}
		return false;
	}
	
	// number of open sites
	// Return count variable that is maintained int open call 
	public int numberOfOpenSites(){
		return openSites;
	}
	  
	// does the system percolate?
	// check if virtual sites are connected or not
	public boolean percolates(){
		if (WQUF.connected(N * N , N * N + 1)) return true;
		return false;
	}
	
	private boolean checkBounds(int row, int col){
		if (row < 0 || row >= N || col < 0 || col >= N){
			//throw new java.lang.IllegalArgumentException();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
	
	}
	/*
	 * Commenting below code for submission
	public static void main(String[] args)  throws Exception{
		// test client (optional)
		
		Scanner sc = new Scanner(new FileInputStream("Resources/percolation/input10-no.txt"));
		
		int N = sc.nextInt();
		
		Percolation per = new Percolation(N);
		while (sc.hasNextInt()){
			int p = sc.nextInt();
			int q = sc.nextInt();
			per.open(p, q);
		}

		if(per.percolates())	System.out.println("Percolates, " + " Open Sites: " + per.numberOfOpenSites());
		else System.out.println("Does not Percolates" + " Open Sites: " + per.numberOfOpenSites());
		sc.close();
	}
	*/
}

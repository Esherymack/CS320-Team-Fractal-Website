package edu.ycp.cs320.CS320_Team_Fractal_Website.renderer;

public class RendererTask implements Runnable 
{
	private double x1, y1, x2, y2;
	private int startCol, endCol, startRow, endRow;
	private int[][] iterCounts;
	
	public RendererTask(double x1, double y1, double x2, double y2, 
					  int startCol, int endCol, int startRow, int endRow,
					  int[][] iterCounts)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.startCol = startCol;
		this.endCol = endCol;
		this.startRow = startRow;
		this.endRow = endRow;
		this.iterCounts = iterCounts;
	}
	
	public void run() 
	{
        for (int i = startRow; i < endRow; i++) 
        {
            for (int j = startCol; j < endCol; j++) 
            {
                Complex c = getComplex(i, j);
                int iterCount = computeIterCount(c);
                iterCounts[i][j] = iterCount;
            }
        }
    }

	public Complex getComplex(int x, int y)
	{
		return new Complex(x, y);
	}
	
	public int computeIterCount(Complex complex) {
    	//Zo is initially 0+0i
    	Complex z = new Complex(0,0);
    	double dx = x2 - x1;
    	double dy = y2 - y1;
    	Complex c = new Complex(((dx / endRow) * (1 + complex.getRealNum())) + x1, ((dy / endRow) * (1 + complex.getImagNum())) + y1);
    	
    	//# of iterations
    	int count = 0;
    	//while z has magnitude of less than 2 and iteration counts have not exceeded MAX
    	while (z.getMagnitude() < 2.0) {
    		if (count > 100) {
    			return 0;
    		}
    		//iterate complex number
        	z = z.multiply(z).add(c);
        	//increment count
    		count++;
    	}
    	System.out.println(count);
    	return count;
    }
}

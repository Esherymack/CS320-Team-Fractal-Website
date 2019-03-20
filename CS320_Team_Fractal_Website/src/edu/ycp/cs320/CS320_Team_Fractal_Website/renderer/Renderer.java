package edu.ycp.cs320.CS320_Team_Fractal_Website.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/*
 * The renderer class is a modification of Dakota's CS201_Assign05 (Mandelbrot) renderer.
 * It gets passed parameters from the type class that creates a Renderer object and generates the result
 * It generates and saves an image that is then placed in the webpage viewer.
 */

public class Renderer 
{
	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;
	public String t = null;
	double x1, y1, x2, y2;
	
	public int[][] iterCounts = new int[WIDTH][HEIGHT];
	
	public Renderer(String type, double X1, double Y1, double X2, double Y2) throws InterruptedException
	{
		t = type;
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
	}
	
	public void startThreads()
	{
		// when a new Renderer is created, start threads and tasks assigned to them
		RendererTask[] renderTasks = new RendererTask[4];
		
		for(int i = 0; i < 4; i++)
		{
			renderTasks[i] = new RendererTask(this.x1, this.y1, this.x2, this.y2, WIDTH/4*i, WIDTH/4*(i+1), 0, HEIGHT, iterCounts);
		}
		
		Thread[] threads = new Thread[4];
		for(int j = 0; j < 4; j++)
		{
			threads[j] = new Thread(renderTasks[j]);
		}
		
		for(int m = 0; m < 4; m++)
		{
			threads[m].start();
		}
		
		try
		{
			for(int l = 0; l < 4; l++)
			{
				threads[l].join();
			}
		}
		catch(InterruptedException e)
		{
			System.err.println("A thread was interrupted.");
		}
	}

	public boolean renderImage(Object... arguments) throws IOException
	{
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		switch(this.t)
		{
		case "sierpinski":
			FractalFuncs.drawSierpinski((int)arguments[0], g, (Point)arguments[1], (Point)arguments[2], (Point)arguments[3]);
			break;
		case "mandelbrot":
			FractalFuncs.drawMandelbrot(g, WIDTH, HEIGHT, iterCounts);
			break;
		default:
			System.out.println("Bad case");
			break;
		}
		
		// dispose of the drawing object
		g.dispose();
		
		OutputStream os = new BufferedOutputStream(new FileOutputStream("./war/img/result.png"));
		try
		{
			ImageIO.write(bufferedImage, "PNG",  os);
		}
		finally
		{
			os.close();
		}
		return true;
	}
	
}

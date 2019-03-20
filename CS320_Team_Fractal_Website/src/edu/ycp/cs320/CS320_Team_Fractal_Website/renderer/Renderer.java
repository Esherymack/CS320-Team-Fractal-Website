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
	
	private BufferedImage bufferedImage;
	public int[][] iterCounts = new int[WIDTH][HEIGHT];
	
	public Renderer() throws InterruptedException
	{
		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.dispose();
	}
	
	public void startThreads(double x1, double y1, double x2, double y2)
	{
		// when a new Renderer is created, start threads and tasks assigned to them
		RendererTask[] renderTasks = new RendererTask[4];
		
		for(int i = 0; i < 4; i++)
		{
			renderTasks[i] = new RendererTask(x1, y1, x2, y2, WIDTH/4*i, WIDTH/4*(i+1), 0, HEIGHT, iterCounts);
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
	
	public void renderSierpinski(int height, Point p1, Point p2, Point p3) throws IOException
	{
		Graphics g = bufferedImage.getGraphics();
		
		FractalFuncs.drawSierpinski(height, g, p1, p2, p3);
		
		g.dispose();
		
		try(OutputStream os = new BufferedOutputStream(new FileOutputStream("./war/img/result.png")))
		{
			ImageIO.write(bufferedImage, "PNG", os);
		}
	}
	
	public void renderMandelbrot(double x1, double y1, double x2, double y2) throws IOException
	{
		Graphics g = bufferedImage.getGraphics();
		startThreads(x1, y1, x2, y2);
		FractalFuncs.drawMandelbrot(g, WIDTH, HEIGHT, iterCounts);
		
		g.dispose();
		
		try(OutputStream os = new BufferedOutputStream(new FileOutputStream("./war/img/result.png")))
		{
			ImageIO.write(bufferedImage, "PNG", os);
		}
	}
}

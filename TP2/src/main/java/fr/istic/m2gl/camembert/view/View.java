package fr.istic.m2gl.camembert.view;

import java.awt.Color;

import javax.swing.JFrame;

import fr.istic.m2gl.camembert.model.Model;

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	private Camembert cam;
	
	public View(Model m) {
		setTitle("Camembert");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		this.cam = new Camembert(m);
				
		getContentPane().add(this.cam);
		
		setVisible(true);
	}
	
	public Camembert getCamembertComponent() {
		return this.cam;
	}
	
}

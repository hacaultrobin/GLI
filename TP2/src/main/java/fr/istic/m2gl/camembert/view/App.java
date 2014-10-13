package fr.istic.m2gl.camembert.view;

import java.awt.Color;

import javax.swing.JFrame;

import fr.istic.m2gl.camembert.model.CamembertModel;
import fr.istic.m2gl.camembert.model.Model;

public class App extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 600;
	public static int HEIGHT = 400;
	
	public App() {
		setTitle("Camembert");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		Model m = new CamembertModel("Test");
		m.addField("A", "Le champs A", 10.5f);
		m.addField("B", "Le champs B", 4.5f);
		m.addField("C", "Le champs C", 8);
		
		
//		JPanel panel = new JPanel();
//		panel.setBackground(Color.white);
//		panel.add(new Camembert());
//		panel.setVisible(true);
//		getContentPane().add(panel);
		getContentPane().add(new Camembert(m));
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new App();
	}
}

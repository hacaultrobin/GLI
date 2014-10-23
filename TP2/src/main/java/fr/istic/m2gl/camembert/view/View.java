package fr.istic.m2gl.camembert.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.istic.m2gl.camembert.model.Model;

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	private CamembertView cam;
	private Model model;
	
	private JLabel fieldInfo = new JLabel("<html>&nbsp;<br>&nbsp;</html>");
	
	public View(Model m) {
		model = m;
		
		setTitle("Camembert");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		add(fieldInfo, BorderLayout.NORTH);
		
		cam = new CamembertView(model);
		add(cam, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public CamembertView getCamembertView() {
		return cam;
	}
	
	public void handleClick(Point2D clickedPoint) {
		int index = cam.getIndexOfClickedField(clickedPoint);

		if (index != -1 ){
			String fieldName = new ArrayList<String>(model.getFieldsNames()).get(index);
			String fieldDesc = model.getDesc(fieldName);
			float fieldValue = model.getValue(fieldName);
			
			fieldInfo.setText("<html>"+fieldName+" : "+fieldValue+"€<br>"+fieldDesc+"</html>");
			revalidate();
		}
	}
	
}

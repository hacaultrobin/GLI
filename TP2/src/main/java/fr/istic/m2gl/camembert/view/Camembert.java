package fr.istic.m2gl.camembert.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

import fr.istic.m2gl.camembert.command.ICommand;
import fr.istic.m2gl.camembert.model.Model;

public class Camembert extends JComponent {

	private static final long serialVersionUID = 1L;
	private Model m;
	private String title;
	private float totalValue;
	private Collection<String> fieldsName;
	
	private ICommand clickOnFieldCmd;
	
	public Camembert(Model m) {
		this.m = m;
		m.setCamembertChangedCmd(() -> loadData());
		
		loadData();		
	}
	
	private void loadData() {
		title = m.getTitle();
		totalValue = m.getTotalValues();
		fieldsName = m.getFieldsNames();
		repaint();
	}
	
	/**
	 * Sets a command to execute on click on a field
	 */
	public void setOnFieldClickCommand(ICommand cmd) {
		this.clickOnFieldCmd = cmd;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;
		int gap;
				
		float start = 0;
		float delta = 0;
		List<Arc2D> arcs = new ArrayList<>();
		
		for (String fieldName : fieldsName) {
			float value = m.getValue(fieldName);
			delta = (360 * value / totalValue);
			
			Arc2D field = new Arc2D.Double(View.WIDTH/2-100, View.HEIGHT/2-100, 200, 200, start, delta, Arc2D.PIE);
			graph.setColor(new Color(new Random().nextInt()));
			graph.fill(field);
			graph.draw(field);
			
			arcs.add(field);
			
			graph.setColor(Color.GRAY);
			graph.setFont(new Font("Arial", Font.BOLD, 11));
			graph.drawString(fieldName, start, 10);
			
			start += delta;
		}

		Arc2D blank = new Arc2D.Double(View.WIDTH/2-65, View.HEIGHT/2-65, 130, 130, 0, 360, Arc2D.PIE);
		graph.setColor(Color.WHITE);
		graph.fill(blank);
		graph.draw(blank);
		
		Arc2D center = new Arc2D.Double(View.WIDTH/2-50, View.HEIGHT/2-50, 100, 100, 0, 360, Arc2D.PIE);
		graph.setColor(new Color(00,73,84));
		graph.fill(center);
		graph.draw(center);

		graph.setColor(Color.GRAY);
		graph.setFont(new Font("Arial", Font.BOLD, 11));
		gap = title.length()*3;
		graph.drawString(title, View.WIDTH/2-gap, View.HEIGHT/2-10);

		graph.setColor(Color.WHITE);
		String total = totalValue+" €";
		gap = total.length()*3;
		graph.drawString(total, View.WIDTH/2-gap, View.HEIGHT/2+10);
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point2D click_position = e.getPoint();
				Arc2D clicked_arc = getClickedArc(arcs, click_position);
				if (clicked_arc != null && !center.contains(click_position) &&
						!blank.contains(click_position) && clickOnFieldCmd != null) {
					clickOnFieldCmd.execute(); // = Notify the controller				
				}				
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}			
		});
		
	}
	
	private Arc2D getClickedArc(List<Arc2D> arcs, Point2D clickedPoint) {
		for (int i = 0; i < arcs.size(); i++) {
			if (arcs.get(i).contains(clickedPoint)) {
				return arcs.get(i);
			}
		}
		return null;
	}
}

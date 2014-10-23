package fr.istic.m2gl.camembert.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.istic.m2gl.camembert.view.View;

public class CamembertController {
	
	public CamembertController(View view) {
		view.getCamembertView().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.handleClick(e.getPoint());		
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
	
}

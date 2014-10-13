package fr.istic.m2gl.camembert.controller;

import fr.istic.m2gl.camembert.view.Camembert;

public class CamembertController {
	
	private Camembert camembert;
	
	public CamembertController(Camembert camembert) {
		this.camembert = camembert;
		this.camembert.setOnFieldClickCommand(() -> handleOnFieldClickCommand());
	}
	
	private void handleOnFieldClickCommand() {
		System.err.println("Click on field --> TODO update view");
	}

}

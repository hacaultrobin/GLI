package fr.istic.m2gl.game2048.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.istic.m2gl.game2048.model.Board;
import fr.istic.m2gl.game2048.model.BoardImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	
	private Board b;
	
	@FXML
	private GridPane gridFx;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		b = new BoardImpl(4);
		
	}
	
	@FXML
	public void handleMove(KeyEvent e) {
		switch (e.getCode()) {
			case LEFT:
				b.packIntoDirection(Board.Direction.LEFT);
				break;

			case RIGHT:
				b.packIntoDirection(Board.Direction.RIGHT);
				break;
				
			case UP:
				b.packIntoDirection(Board.Direction.TOP);
				break;
				
			case DOWN:
				b.packIntoDirection(Board.Direction.BOTTOM);
				break;

			default:
				break;
		}
	}

}

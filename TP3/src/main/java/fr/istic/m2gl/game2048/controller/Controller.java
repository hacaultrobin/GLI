package fr.istic.m2gl.game2048.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.istic.m2gl.game2048.model.Board;
import fr.istic.m2gl.game2048.model.BoardImpl;
import fr.istic.m2gl.game2048.model.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	
	private Board b;
	
	@FXML
	private GridPane gridFx;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		b = new BoardImpl(4);
		b.addTileRandomly();
		b.addTileRandomly();
		b.commit();
		updateGrid();
	}
	
	@FXML
	public void handleMove(KeyEvent e) {
		switch (e.getCode()) {
			case LEFT:
				b.packIntoDirection(Board.Direction.LEFT);
				b.addTileRandomly();
				b.commit();
				updateGrid();
				break;

			case RIGHT:
				b.packIntoDirection(Board.Direction.RIGHT);
				b.addTileRandomly();
				b.commit();
				updateGrid();
				break;
				
			case UP:
				b.packIntoDirection(Board.Direction.TOP);
				b.addTileRandomly();
				b.commit();
				updateGrid();
				break;
				
			case DOWN:
				b.packIntoDirection(Board.Direction.BOTTOM);
				b.addTileRandomly();
				b.commit();
				updateGrid();
				break;

			default:
				break;
		}
	}

	private void updateGrid() {
		for (Node child: gridFx.getChildren()) {
			// Recuperation du tile de la case courante
			Tile t = b.getTile(GridPane.getRowIndex(child)+1, GridPane.getColumnIndex(child)+1);

			// Suppression du contenue de la case
			Pane p = (Pane) child;
			p.getChildren().clear();
			
			// Maj de la case
			if (t != null) {
				int value = (int) Math.pow(2, t.getRank());
				
				Label l = new Label(value+"");
				p.getChildren().add(l);
			}
		}
	}
}

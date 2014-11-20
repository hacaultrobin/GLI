package fr.istic.m2gl.game2048.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.istic.m2gl.game2048.model.Board;
import fr.istic.m2gl.game2048.model.BoardImpl;
import fr.istic.m2gl.game2048.model.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Controller implements Initializable {
	
	private Board b;
	private List<String> colors = new ArrayList<String>();
	
	@FXML
	private GridPane gridFx;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colors.add("#EEE4DA"); //2
		colors.add("#EDE0C8"); //4
		colors.add("#F2B179"); //8
		colors.add("#F59563"); //16
		colors.add("#F67C5F"); //32
		colors.add("#F65E3B"); //64
		colors.add("#EDCF72"); //128
		colors.add("#EDCC61"); //256
		colors.add("#EDC850"); //512
		colors.add("#EDC53F"); //1024
		colors.add("#EDC22E"); //2048
		
		b = new BoardImpl(4);
		b.addTileRandomly();
		b.addTileRandomly();
		b.commit();
		
		updateGrid();
	}
	
	@FXML
	public void handleMove(KeyEvent e) {
		boolean move;
		switch (e.getCode()) {
			case LEFT:
				b.packIntoDirection(Board.Direction.LEFT);
				move = true;
				break;

			case RIGHT:
				b.packIntoDirection(Board.Direction.RIGHT);
				move = true;
				break;
				
			case UP:
				b.packIntoDirection(Board.Direction.TOP);
				move = true;
				break;
				
			case DOWN:
				b.packIntoDirection(Board.Direction.BOTTOM);
				move = true;				
				break;

			default:
				move = false;
				break;
		}
		
		if (move && b.wasModified()) {
			b.addTileRandomly();
			b.commit();
			updateGrid();
			if (b.isGameOver()) {
				Text t = new Text("Game over!");
				t.setFont(Font.font("Arial", FontWeight.BOLD, 22));
				t.setFill(Color.RED);
				
		        Popup popup = new Popup();
		        popup.getContent().add(t);
		        popup.show(gridFx.getScene().getWindow());
			}
		}
	}

	private void updateGrid() {
		for (Node child: gridFx.getChildren()) {
			// Recuperation du tile de la case courante
			Tile t = b.getTile(GridPane.getRowIndex(child)+1, GridPane.getColumnIndex(child)+1);

			// Suppression du contenue de la case
			StackPane p = (StackPane) child;
			p.getChildren().clear();
			
			// Maj de la case
			if (t != null) {
				int value = (int) Math.pow(2, t.getRank());
				
				Label l = new Label(value+"");
				l.setPrefWidth(200);
				l.setPrefHeight(200);
				l.setAlignment(Pos.CENTER);
				l.setFont(Font.font("Arial", FontWeight.BOLD, 26));
				l.setTextFill(Color.GREY);
				try {
					l.setStyle("-fx-background-color: "+colors.get(t.getRank()));
				} catch (IndexOutOfBoundsException ignore) {
					l.setStyle("-fx-background-color: #3C3A32");
				}
				
				p.getChildren().add(l);
				p.setAlignment(Pos.CENTER);
			}
		}
	}
}

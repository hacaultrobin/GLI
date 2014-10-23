import fr.istic.m2gl.camembert.controller.CamembertController;
import fr.istic.m2gl.camembert.model.CamembertModel;
import fr.istic.m2gl.camembert.model.Model;
import fr.istic.m2gl.camembert.view.View;

public class Main {

	public static void main (String[] args) {
		// Init of the model, with 3 fields
		Model model = new CamembertModel("Test");
		model.addField("A", "Field A", 2);
		model.addField("B", "Field B", 4);
		model.addField("C", "Field C", 8);
		
		// Init of the view, which refers to the model and set commands on it
		View view = new View(model);
		
		// Init of the controller, which refers to the Camembert component included in the view
		new CamembertController(view);
	}

}

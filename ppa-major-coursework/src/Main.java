import base_statistics.ModelWrapper;
import view.MainFrame;

public class Main {

	private static ModelWrapper modelWrapper;
	private static MainFrame mainFrame;
	
	public static void main(String[] args) {
		
		modelWrapper = new ModelWrapper();
		mainFrame = new MainFrame(modelWrapper);
		
	}
	
}

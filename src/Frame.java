import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {
	
	Panel panel;
	
    Image icon = new ImageIcon("res/icon.png").getImage();
	
	Frame(int width, int height) {
		panel = new Panel(width, height);
		this.add(panel);
		this.pack();
		this.setTitle("Clock");
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(icon);
		
	}
	
	
}

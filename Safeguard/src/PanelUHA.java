import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class PanelUHA extends JPanel{

	private static final long serialVersionUID = 4775110577266594406L;
	private BufferedImage image;

    public PanelUHA() {
    	try {                
    		image = ImageIO.read(new File("iconeUHA.png"));
    	} catch (IOException ex) {
            // handle exception...
    	}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}

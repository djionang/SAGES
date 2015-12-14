package test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
try{
			int width=75;
			int height=75;
			
			byte[] contenuFichier;
			BufferedImage originalImage = ImageIO.read(new File(
					"c:/Hiver.jpg"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			contenuFichier = baos.toByteArray();
			baos.close();

	
			//Lecture de l'image contenu dans le tableau de bytes
			InputStream in = new ByteArrayInputStream(contenuFichier);
			BufferedImage bim= ImageIO.read(in);
			
			//Transformation de l'image pour lui donner la taille qui nous interesse
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
		    Graphics2D g = bi.createGraphics(); 
		    g.drawImage(bim, 0, 0, width, height, null); 
		    
		    //Sauvegarde de l'image sur disque
		    ImageIO.write(bi, "JPG", new File("c:/test.jpg")); 
		     	   
	   } catch (Exception e) { 
	      e.printStackTrace(); 
	      
	    }//end try 

	}

}

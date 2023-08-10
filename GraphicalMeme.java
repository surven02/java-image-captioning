import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GraphicalMeme extends Meme {


    public GraphicalMeme(BackgroundImage image, String caption, User creator) {
        super(image, caption, creator);
        creator.getMemesCreated().add(this);
    }

    public BufferedImage compileMeme() throws IOException {
        return this.compileMeme(550);
    }


    public BufferedImage compileMeme(int maxDimension) throws IOException {
        // Read in the background image from the filename
        BufferedImage image = ImageIO.read(new File(getBackgroundImage().getImageFileName()));
        
        // Determine the height and width of the original image
        int origWidth = image.getWidth();
        int origHeight = image.getHeight();

        // Scale the image to the maximum height and width
        int width = 0;
        int height = 0;
        if (origWidth > origHeight) {
            width = maxDimension;
            height = (int) (origHeight * ((double) width / origWidth));
        } else {
            height = maxDimension;
            width = (int) (origWidth * ((double) height / origHeight));
        }
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gd = scaled.createGraphics();
        gd.drawImage(tmp, 0, 0, null);
        gd.dispose();

        // Get font information for writing onto the image
        Graphics g = scaled.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        FontMetrics metrics = g.getFontMetrics(g.getFont().deriveFont(30f));

        // Upper-case the caption
        String text = getCaption().toUpperCase();

        // Split the caption text into words and create lines that are no wider
        // than the image.  This provides wrapping if the caption is wider than
        // the input image.
        String[] words = text.split(" ");
        ArrayList<String> lines = new ArrayList<>();
        int currentLength = 0;
        String currentLine = "";
        for (int i = 0; i < words.length; i++) {
            int len = metrics.stringWidth(words[i] + " ");
            if (currentLength + len > width) {
                lines.add(currentLine);
                currentLength = 0;
                currentLine = "";
            }
            currentLine += words[i] + " ";
            currentLength += len;

        }
        if (currentLine != "")
            lines.add(currentLine);

        // Draw the caption onto the image at the appropriate space based on
        // captionVerticalAlign
        int numLines = lines.size();
        int heightAllLines = (metrics.getHeight() + 10) * numLines;
        int positionY = metrics.getHeight() + 10;
        if (this.getCaptionVerticalAlign().equals("bottom"))
            positionY = height - heightAllLines;
        else if (this.getCaptionVerticalAlign().equals("middle"))
            positionY = (height - heightAllLines + metrics.getHeight()) / 2;
        for (String line : lines) {
            int positionX = (scaled.getWidth() - metrics.stringWidth(line.trim())) / 2;
            g.setColor(Color.BLACK);
            g.drawString(line.trim(), positionX + 1, positionY + 1);
            g.setColor(Color.WHITE);
            g.drawString(line.trim(), positionX, positionY);
            positionY += metrics.getHeight() + 10;
        }

        // Clean up the graphics object used to create the image of the meme
        g.dispose();

        // Return the scaled image
        return scaled;

    }
}

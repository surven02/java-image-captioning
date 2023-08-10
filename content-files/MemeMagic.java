import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MemeMagic extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private JPanel buttonPanel;
    private JTextField titleTextField;
    private JTextField captionTextField;
    private JTextField descriptionTextField;
    private JLabel verticalAlignLabel;
    private JComboBox<String> options;
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }



    public static void main(String[] args) {
        
    	//create a main user object for the Meme Magic instance
        User user = new User();

        //instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // invoke the GUI
        //start the GUI
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }


    private void initialize() {

    	//exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                // title the window to the desired caption
        this.setTitle("Meme Magic");

           //use the borderlayout object on the main panel
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);
        
        // label to display the full image
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));
        
        //display the full image on the panel
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);

                //display the controls for creating a sample Meme
        controlPanel = new JPanel(new BorderLayout());
        
        //panel that holds the bgimage and give it a title
        JPanel bgImagePanel = new JPanel(new BorderLayout());
        bgImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));
        
        
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);
        
        
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));

        // Textfield
        titleTextField = new JTextField("");
        titleTextField.setPreferredSize(new Dimension(350, 20));
                
        descriptionTextField = new JTextField("");
        descriptionTextField.setPreferredSize(new Dimension(350, 20));
        
        captionTextField = new JTextField("");
        captionTextField.setPreferredSize(new Dimension(350, 20));
        
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        
        JPanel backgroundpanel1 = new JPanel();
        JPanel backgroundpanel2 = new JPanel();
        JPanel backgroundpanel3 = new JPanel();
        
        backgroundpanel1.add(backgroundImageFileLabel);

        // Label
        
        
        JLabel titleMemeLabel = new JLabel("Title:                 ");
        titleMemeLabel.setPreferredSize(new Dimension(100, 20));
        
        JLabel descriptionMemeLabel = new JLabel("Description: ");
        descriptionMemeLabel.setPreferredSize(new Dimension(100, 20));
        
        JLabel captionMemeLabel = new JLabel("Caption: ");
        captionMemeLabel.setPreferredSize(new Dimension(100, 20));

        verticalAlignLabel = new JLabel("Vertical Align: ");
        verticalAlignLabel.setPreferredSize(new Dimension(347, 20));
        
        backgroundpanel2.add(titleMemeLabel);
        backgroundpanel3.add(descriptionMemeLabel);
        backgroundpanel2.add(titleTextField);
        
        backgroundpanel3.add(descriptionTextField);

        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        backgroundpanel1.add(backgroundImageButton);
        
        
        
        buttonPanel = new JPanel();
        
        JButton generateMemeButton = new JButton("Generate");
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        generateMemeButton.addActionListener(new generateButtonListener());
        buttonPanel.add(generateMemeButton);
        
        JButton saveMemeButton = new JButton("Save");
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        saveMemeButton.addActionListener(new SaveButtonListener());
        
        buttonPanel.add(saveMemeButton);


        // TODO The button needs a listener
        backgroundImageButton.addActionListener(new OpenButtonListener());
        
        //contains the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundpanel1.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        //add a panel with the bgimage fileName to the info panel
        bgImagePanel.add(backgroundpanel1, BorderLayout.NORTH);
        bgImagePanel.add(backgroundpanel2, BorderLayout.CENTER);
        bgImagePanel.add(backgroundpanel3, BorderLayout.SOUTH);
        

        

        
        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
        options = new JComboBox<String>();
        options.addItem("top");
        options.addItem("middle");
        options.addItem("bottom");
        
        
        
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(captionMemeLabel);
        optionsPanel.add(captionTextField);
        optionsPanel.add(verticalAlignLabel);
        optionsPanel.add(options);  
        
        //set size of the main app window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
        
        
                //add the bgimage info panel to the control panel
        controlPanel.add(bgImagePanel, BorderLayout.NORTH);
        controlPanel.add(optionsPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        //add panels to the main display according to borderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        //add panelPane to the contentPane of the window
        this.getContentPane().add(panelPane);

       
    }
    
    

    private class OpenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                
                String title = titleTextField.getText();
                String caption = captionTextField.getText();
                String verticalAlign = (String)(options.getSelectedItem());
        		String description = descriptionTextField.getText();
        		
        		
        		
        		
                // TODO: Writing an image throws a checked exception that must be handled.
                //       Catch the exceptions and provide the user with an appropriate message
                
                try {
                ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
                } catch(NullPointerException e1) {
                System.out.println("Null Pointer Exception Error.");
                } catch(IOException e2) {
                	e2.printStackTrace();
                }
                
                currentMeme = new GraphicalMeme(new BackgroundImage(backgroundImageFileNameLabel.getText() , title, description) , caption, user); 
        		currentMeme.setCaptionVerticalAlign(verticalAlign);
            }
        }
    }
    
    private class generateButtonListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent evt) {
    		String title = titleTextField.getText();
    		String description = descriptionTextField.getText();
    		String caption = captionTextField.getText();
    		String verticalAlign = (String)(options.getSelectedItem());
    		
    		currentMeme = new GraphicalMeme(new BackgroundImage(backgroundImageFileNameLabel.getText() , title, description) , caption, user); 
    		currentMeme.setCaptionVerticalAlign(verticalAlign);
    		
    		try {
				imageDisplayLabel.setIcon(new ImageIcon(currentMeme.compileMeme()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e1) {
				System.out.println("Null Pointer Exception Error");
			}
    		
    	}
    }


}






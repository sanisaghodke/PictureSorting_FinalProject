package com.finalproject;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
public class FolderSelector extends JFrame implements ActionListener{

	static JLabel inputDirectoryLabel;
	static JLabel outputDirectoryLabel;

	public String getInput(){
		return this.inputDirectoryLabel.getText();
	}
	
	public String getOutput(){
		return this.outputDirectoryLabel.getText();
	}

	public static void main(String args[]){
        // frame to contains GUI elements
        JFrame fileChooser = new JFrame("Picture Sorter");

        // set the size of the frame
        fileChooser.setSize(600, 400);

        // set the frame's visibility
        fileChooser.setVisible(true);

        fileChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        // button to open open dialog
        JButton inputButton = new JButton("input directory");
		inputDirectoryLabel = new JLabel("no input file selected");
		JButton outputButton = new JButton("output directory");
		//JLabel inputLabel = new JLabel("input label");
		outputDirectoryLabel = new JLabel("output label");
		JLabel emptyLabel = new JLabel("");
		JButton okButton = new JButton("OK");

        // make an object of the class filechooser
        FolderSelector f1 = new FolderSelector();

        // add action listener to the button to capture user
        // response on buttons
        inputButton.addActionListener(f1);
		outputButton.addActionListener(f1);
        okButton.addActionListener(f1);

        // make a panel to add the buttons and labels
        GridLayout buttonLayout = new GridLayout(3,2);
		JPanel p = new JPanel(buttonLayout);
		//JPanel inpDirPanel = new JPan

        // add buttons to the frame
        p.add(inputButton);
		p.add(inputDirectoryLabel);
		//p.add(inputLabel);

		p.add(outputButton);
        p.add(outputDirectoryLabel);
		p.add(emptyLabel);
		p.add(okButton);

        // add panel to the frame
        fileChooser.add(p);

        fileChooser.setVisible(true);
    }

	public void actionPerformed(ActionEvent evt){
        // if the user presses the save button show the save dialog
        String com = evt.getActionCommand();

        if (com.equals("output directory")) {
            // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // set the selection mode to directories only
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected directory
                outputDirectoryLabel.setText(j.getSelectedFile().getAbsolutePath());
            }
            // if the user cancelled the operation
            else
                outputDirectoryLabel.setText("the user cancelled the operation");
        }
        // if the user presses the open dialog show the open dialog
        else if (com.equals("input directory")) {
            // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // set the selection mode to directories only
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected directory
                inputDirectoryLabel.setText(j.getSelectedFile().getAbsolutePath());
            }
            // if the user cancelled the operation
            else
                inputDirectoryLabel.setText("the user cancelled the operation");
        }else {
            // if the user presses the OK button, call the folder processor method
            System.out.println("ok");
            System.out.println(inputDirectoryLabel.getText());
            System.out.println(outputDirectoryLabel.getText());
            ImageMetadataSorter.folderProcessor(inputDirectoryLabel.getText(), outputDirectoryLabel.getText());
        }
    }
}
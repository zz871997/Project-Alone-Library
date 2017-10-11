package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.SearchBookView;

public class PrintSearchInforController {
	private String saveFilePath;
	
	private MainUI mainUI;
	
	
	private SearchBookView searchBookView;
	
	// Constructor
	public PrintSearchInforController (MainUI mainUI) {
		this.mainUI = mainUI;
		JButton btnPrint = mainUI.getSearchBookView().getBtnPrint();
		
		
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setAction();
				} catch (UnsupportedEncodingException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		//setAction();
	}
	
	
	/*----------------Set Action------------------- */
	private void setAction() throws UnsupportedEncodingException, FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.mainUI);
		
		if(select == JFileChooser.APPROVE_OPTION) {
			saveFilePath = fileChooser.getCurrentDirectory().toString() 
					       + "\\" + fileChooser.getSelectedFile().getName();
			
			System.out.println(saveFilePath);
			printToWord(saveFilePath);
		}
	}
	
	// Print data to MS Word
	private void printToWord(String path) throws UnsupportedEncodingException, FileNotFoundException {
		
//		try {
//			File file = new File(path);
//			FileWriter fileWriter = new FileWriter(file);
//			
//			fileWriter.write("Mai Tiên Dũng");
//			
//			fileWriter.close();
//		} 
//		catch (IOException e) {
//			System.out.println(e.toString());
//			System.out.println("Printing to MS Word is failed!!!");
//		}
		
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(path), "UTF-8"));
			try {
			    out.write("Trần Quang Khải");
			}
			catch (IOException e) {
				System.out.println(e.toString());
				System.out.println("Printing to MS Word is failed!!!");
			}
			
			finally {
			    try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}

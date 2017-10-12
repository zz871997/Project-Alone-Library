package tranquangkhai20152005.library.test;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DemoPOI {

   public static void main(String[] args)throws Exception {

      //Blank Document
      XWPFDocument document = new XWPFDocument(); 
      
      //Write the Document in file system
      FileOutputStream out = new FileOutputStream(new File("C:/Users/User/Music/Desktop/createparagraph.docx"));
        
      //create Paragraph
      XWPFParagraph paragraph = document.createParagraph();
      XWPFRun run = paragraph.createRun();
      run.setText("At tutorialspoint.com, we strive hard to " +
         "provide quality tutorials for self-learning " +
         "purpose in the domains of Academics, Information " +
         "Technology, Management and Computer Programming Languages.");
      
      
      XWPFTable table = document.createTable(1, 2);
      XWPFTableRow row1 = table.getRow(0);
     // row1.getCell(0).removeParagraph(0);
      
      XWPFTableCell cell1 = row1.getCell(0);
      setTextCenter("Demo dinh menh", cell1);
      

      XWPFTableCell cell2 = row1.getCell(1);
      setTextCenter("Tran quang khai", cell2);
      
      
      
      
      
      document.write(out);
      out.close();
      System.out.println("createparagraph.docx written successfully");
   }
   
   private static void setTextCenter(String text, XWPFTableCell cell) {
	   	XWPFParagraph paragraph1 = cell.getParagraphs().get(0);
		paragraph1.setAlignment(ParagraphAlignment.CENTER);
 		XWPFRun cellRun = paragraph1.createRun();
 		cellRun.setText(text);
 		cellRun.setBold(true);
 		
 	}
}
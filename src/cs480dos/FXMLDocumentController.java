package cs480dos;


import java.io.BufferedReader;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLDocumentController {

    @FXML private Label label;
    @FXML private MenuItem fileSelector;
    @FXML private TextArea contents;
    @FXML private Button clearContents;
    private File file;
    
    @FXML 
    private void handle() {
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(new Stage());
        clearContents.setDisable(false);
        displayFileContents();
    }
    
    @FXML
    private void clearArea() {
        contents.clear();
        clearContents.setDisable(true);
    }
    
    @FXML
    private void displayFileContents() {
        contents.clear();
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            contents.appendText(sb.toString());
        } catch(Exception e) {
            contents.appendText("No file found");
        }
    }
}

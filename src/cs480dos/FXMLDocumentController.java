


import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML private Button analysis;
    @FXML private Button analysisSave;
    @FXML private Label problemDisplay;
    @FXML private DatePicker datePicker;
    @FXML private Label saveMessage;
    
    private File file;
    private ArrayList<Log> list;
    private ArrayList<int[]> chunks;
    private String analyzed;
    
    @FXML 
    private void handle() {
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(new Stage());
        clearContents.setDisable(false);
        analysis.setDisable(false);
        displayFileContents();
    }
    
    @FXML
    private void clearArea() {
        contents.clear();
        saveMessage.setText("");
        clearContents.setDisable(true);
        analysis.setDisable(true);
        analysisSave.setDisable(true);
        problemDisplay.setText("");
    }
    
    @FXML
    private void displayFileContents() {
        contents.clear();
        int position = contents.caretPositionProperty().get();
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            contents.appendText(sb.toString());
            contents.positionCaret(position);
            br.close();
        } catch(Exception e) {
            contents.appendText("No file found");
        }
    }
    
    @FXML
    private void runAnalysis() throws IOException {
        contents.clear();
        int position = contents.caretPositionProperty().get();
        
        LogParser lp = new LogParser(file);
        list = lp.readText();
        LogAnalysis results = new LogAnalysis(list);
        results.setChunks();
        chunks = results.getChunks();
        
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < chunks.size();index++) {
            sb.append("Threat ").append(index + 1).append("\n\n");
            for(int i = chunks.get(index)[0]; i <= chunks.get(index)[1];i++) {
                String line = list.get(i).toString();
                sb.append(line).append("\n");                
            }
            sb.append("\n\n");
        }
        analyzed = sb.toString();
        contents.appendText(analyzed);
        contents.positionCaret(position);
        
        problemDisplay.setText(chunks.size() + " potential attacks found");
        analysisSave.setDisable(false);
    }
    
    @FXML
    private void saveAnalysis() {
        if (!(list == null || chunks == null || analyzed == null)) {            
            try {
                FileWriter br = new FileWriter("C:/Users/user/Documents/NetBeansProjects/AttackDetection/src/storage.txt", true);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date d = new Date();
                br.write(sdf.format(d) + "\n" + analyzed + "\n**");
                
                saveMessage.setText("Analysis Saved");
                br.close();
            } catch (Exception ex) {
                System.out.println("Didn't work");
            }
        }
    }
    
    @FXML
    private void getAnalysisByDate() {
        clearArea();        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate d = datePicker.getValue();
            String date = formatter.format(d).split("/n")[0];
            
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/user/Documents/NetBeansProjects/AttackDetection/src/storage.txt"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) 
                sb.append(line).append("\n");
            
            String[] prev = sb.toString().split("\\*\\*");
            StringBuilder sb1 = new StringBuilder();
            int threats = 0;
            for (String s : prev) {
                if (s != null && s.startsWith(date)) {
                    threats += s.split("Threat").length - 1;
                    sb1.append(s).append("\n");
                }
            }
            contents.appendText(sb1.toString());
            problemDisplay.setText(threats + " potential attacks found");
            clearContents.setDisable(false);
        } catch (Exception ex) {
            System.out.println(ex.getClass().toString());
            System.out.println(ex.getMessage());
        } 
    }
}

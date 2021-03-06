import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.*;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import java.io.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class GenerateSalaryReport{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Staff> reportTable;

    @FXML
    private TableColumn<Staff, String> idColumn;

    @FXML
    private TableColumn<Staff, String> nameColumn;

    @FXML
    private TableColumn<Staff, Double> salaryColumn;

    @FXML
    private Button backButton;

    @FXML
    void pressedBack(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("OptionMenuScene");
    }

    @FXML
    void initialize() throws FileNotFoundException{
      idColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("id"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("name"));
      salaryColumn.setCellValueFactory(new PropertyValueFactory<Staff,Double>("salary"));
      reportTable.setItems(RunTheProgram.allStaffOBList);
      
    }

    }
   

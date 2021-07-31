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

public class CleanerTopThreeGenerator {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Staff> topThreeTable;

    @FXML
    private TableColumn<Staff, String> idColumn;

    @FXML
    private TableColumn<Staff, String> nameColumn;

    @FXML
    private TableColumn<Staff, String> revenueColumn;

    @FXML
    private Button backButton;

    @FXML
    void pressBack(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("CleanerMenu");
    }

    @FXML
    void initialize() {
      idColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("id"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("name"));
      revenueColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("amount"));
      topThreeTable.setItems(RunTheProgram.topThreeList);

    }
}

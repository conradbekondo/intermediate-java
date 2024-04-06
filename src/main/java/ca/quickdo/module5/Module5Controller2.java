package ca.quickdo.module5;

import ca.quickdo.module5.model.Person;
import ca.quickdo.module5.services.PeopleService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Module5Controller2 {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final ObservableList<Person> people = FXCollections.observableArrayList();

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfEmail;

    @FXML
    private ComboBox<String> cbCity;

    @FXML
    private Button btnPickImage;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Person> tvPeople;

    @FXML
    private TableColumn<Person, Object> colAvatar;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colPhone;

    @FXML
    private TableColumn<Person, String> colEmail;

    @FXML
    private TableColumn<Person, String> colCity;

    @FXML
    private TableColumn<Person, Object> colAction;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNames()));
        colPhone.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPhone()));
        tvPeople.setItems(people);
        executorService.submit(() -> {
            final var results = PeopleService.getPeople(Integer.MAX_VALUE);
            Platform.runLater(() -> people.setAll(results));
        });
    }

    public void shutdown() throws SQLException {
        PeopleService.shutdown();
        executorService.shutdown();
    }

}

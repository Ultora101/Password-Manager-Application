package com.password.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow {

    private final Stage stage;

    public MainWindow(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setLeft(buildSidebar());
        root.setCenter(buildMainContent());
        root.setBottom(buildStatusBar());

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        stage.setTitle("VaultKey - Password Manager");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(450);
        stage.show();
    }

    private VBox buildSidebar() {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().addAll("sidebar");
        sidebar.setPrefWidth(190);

        // Logo goes here
        Label logo = new Label("VaultKey");
        logo.getStyleClass().add("sidebar-logo");
        VBox logoBox = new VBox(logo);
        logoBox.getStyleClass().add("sidebar-header");

        // nav items
        Button allEntries = navButton("All Entries", true);
        Button work = navButton("Work", false);
        Button social = navButton("Social", false);
        Button finance = navButton("Finance", false);
        Button other = navButton("Other", false);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button settings = navButton("Settings", false);
        Button lockVault = navButton("Lock Vault", false);
        lockVault.getStyleClass().add("nav-btn-danger");

        sidebar.getChildren().addAll(logoBox, allEntries, work, social, finance, other, spacer, settings, lockVault);
        return sidebar;

    }

    private Button navButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.getStyleClass().add("nav-btn");
        btn.setMaxWidth(Double.MAX_VALUE);
        if (active)
            btn.getStyleClass().add("nav-btn-active");
        return btn;
    }

    private VBox buildMainContent() {
        VBox content = new VBox();
        content.getStyleClass().add("main-content");

        // toolbar
        HBox toolbar = buildToolbar();

        // Table
        TableView<Object> table = buildTable();
        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(toolbar, table);
        return content;
    }

    private HBox buildToolbar() {
        HBox toolbar = new HBox(10);
        toolbar.getStyleClass().add("toolbar");
        toolbar.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText("Search entries...");
        search.getStyleClass().add("search-field");
        HBox.setHgrow(search, Priority.ALWAYS);

        Button filter = new Button("Filter");
        Button addEntry = new Button("+ Add Entry");
        filter.getStyleClass().add("btn-secondary");
        addEntry.getStyleClass().add("btn-primary");

        toolbar.getChildren().addAll(search, filter, addEntry);
        return toolbar;
    }

    private TableView<Object> buildTable() {
        TableView<Object> table = new TableView<>();
        table.getStyleClass().addAll("main-table");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No entries yet. Click '+ Add Entry' to get started."));

        TableColumn<Object, String> nameCol = new TableColumn<>("Name");
        TableColumn<Object, String> usernameCol = new TableColumn<>("Username");
        TableColumn<Object, String> passwordCol = new TableColumn<>("Password");
        TableColumn<Object, String> categoryCol = new TableColumn<>("Category");
        TableColumn<Object, String> modifiedCol = new TableColumn<>("Last Modified");
        TableColumn<Object, Void> actionsCol = new TableColumn<>("");

        nameCol.setPrefWidth(180);
        usernameCol.setPrefWidth(160);
        passwordCol.setPrefWidth(140);
        categoryCol.setPrefWidth(100);
        modifiedCol.setPrefWidth(120);
        actionsCol.setPrefWidth(120);

        // wire to model properties once PasswordEntry class exsists:
        // nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getColumns().addAll(nameCol, usernameCol, passwordCol, categoryCol, modifiedCol, actionsCol);
        return table;

    }

    private HBox buildStatusBar() {
        HBox bar = new HBox();
        bar.getStyleClass().add("status-bar");
        bar.setAlignment(Pos.CENTER_LEFT);

        Label count = new Label("0 entries"); // will need to wire up a counter for entries later
        Label status = new Label("Vault unlocked");
        count.getStyleClass().add("status-text");
        status.getStyleClass().add("status-text");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        bar.getChildren().addAll(count, spacer, status);
        return bar;

    }
}

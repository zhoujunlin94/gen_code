package io.github.zhoujunlin94.code.gen;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.PageResult;
import cn.hutool.db.ds.DSFactory;
import io.github.zhoujunlin94.code.gen.common.SettingContext;
import io.github.zhoujunlin94.code.gen.fx.BaseApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2024-05-09-16:43
 */
public class GenCodeApp extends BaseApplication {

//    public static final List<AbstractGenCodeComponent> GEN_CODE_COMPONENTS = CollUtil.newArrayList(
//            new GenEntityCodeComponent(), new GenDTOCodeComponent(), new GenPageQueryDTOCodeComponent(), new GenVOCodeComponent(),
//            new GenMapperCodeComponent(), new GenMapperXmlCodeComponent(), new GenHandlerCodeComponent(),
//            new GenServiceCodeComponent(), new GenServiceImplCodeComponent(), new GenEndpointCodeComponent()
//    );
//
//    public static void run() {
//        Setting context = new Setting("genCode.setting");
//        String tableNames = context.get("tables");
//        for (String tableName : StrUtil.splitTrim(tableNames, StrUtil.COMMA)) {
//            Table table = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
//            AbstractGenCodeComponent.initContext(table, context);
//            GEN_CODE_COMPONENTS.forEach(component -> component.genCode(table, context));
//        }
//    }

    private TableView<DbConf> tableView;
    private int currentPage = 1;
    private final int pageSize = 10;
    private Label pageLabel;
    private ObservableList<DbConf> data;
    private TextField idField, dbNameField;

    private int totalPages;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 初始化数据
        data = FXCollections.observableArrayList();
        // 初始化表格
        tableView = new TableView<>();
        tableView.setItems(data);

        // 显示当前页信息
        pageLabel = new Label("Page: " + currentPage + "/" + totalPages);

        loadDataFromDatabase();


        TableColumn<DbConf, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        // Age列
        TableColumn<DbConf, String> dbNameColumn = new TableColumn<>("dbName");
        dbNameColumn.setCellValueFactory(cellData -> cellData.getValue().dbNameProperty());

        TableColumn<DbConf, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<DbConf, Void>() {
            private final Button editButton = new Button("Edit");

            {
                // 编辑按钮点击事件
                editButton.setOnAction(event -> {

                });

            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // 在每行末尾显示新增按钮
                    setGraphic(editButton);
                }
            }
        });


        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(dbNameColumn);
        tableView.getColumns().add(actionColumn);

        // 输入字段
        idField = new TextField();
        idField.setPromptText("id");
        dbNameField = new TextField();
        dbNameField.setPromptText("dbName");

        // 创建按钮
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addPerson());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deletePerson());

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updatePerson());

        Button prevPageButton = new Button("Previous");
        prevPageButton.setOnAction(e -> prevPage());

        Button nextPageButton = new Button("Next");
        nextPageButton.setOnAction(e -> nextPage());


        // 布局
        HBox form = new HBox(10, idField, dbNameField, addButton, deleteButton, updateButton);
        form.setPadding(new javafx.geometry.Insets(10));

        HBox pagination = new HBox(10, prevPageButton, pageLabel, nextPageButton);
        pagination.setPadding(new javafx.geometry.Insets(10));

        VBox vBox = new VBox(10, tableView, form, pagination);
        vBox.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(vBox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX TableView with Paginated Database");
        primaryStage.show();
    }

    private static final DataSource DATA_SOURCE = DSFactory.create(SettingContext.getSetting("h2.setting")).getDataSource();
    private static final Db DB = DbUtil.use(DATA_SOURCE);

    // 从数据库加载数据，支持分页
    @SneakyThrows
    private void loadDataFromDatabase() {
        PageResult<Entity> pageResult = DB.page(Entity.create("db_conf"), currentPage - 1, pageSize);
        totalPages = pageResult.getTotalPage();
        // 获取当前页的数据
        List<DbConf> retList = pageResult.stream().map(entity -> entity.toBean(DbConf.class)).collect(Collectors.toList());
        data.clear();
        data.addAll(retList);

        // 更新页面标签
        updatePageLabel();
    }

    // 更新页面标签显示
    private void updatePageLabel() {
        pageLabel.setText("Page: " + currentPage + "/" + totalPages);
    }

    // 增加数据
    private void addPerson() {
        /*String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        if (DatabaseHelper.addPerson(name, age)) {
            loadDataFromDatabase();  // 更新数据
            nameField.clear();
            ageField.clear();
        }*/
    }

    // 删除数据
    private void deletePerson() {
        /*Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null && DatabaseHelper.deletePerson(selectedPerson.getId())) {
            loadDataFromDatabase();  // 更新数据
        }*/
    }

    // 更新数据
    private void updatePerson() {
        DbConf selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            String id = idField.getText();

//            int age = Integer.parseInt(ageField.getText());
//            if (DatabaseHelper.updatePerson(selectedPerson.getId(), name, age)) {
//                loadDataFromDatabase();  // 更新数据
//                nameField.clear();
//                ageField.clear();
//            }
        }
    }

    // 上一页
    private void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            loadDataFromDatabase();
        }
    }

    // 下一页
    private void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            loadDataFromDatabase();
        }
    }
}


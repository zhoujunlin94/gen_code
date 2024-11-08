package io.github.zhoujunlin94.code.gen;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2024-11-08-13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbConf {
    private Integer id;
    private String dbName;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public StringProperty dbNameProperty() {
        return new SimpleStringProperty(dbName);
    }

}

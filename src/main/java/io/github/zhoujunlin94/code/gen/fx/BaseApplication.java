package io.github.zhoujunlin94.code.gen.fx;

import cn.hutool.core.util.StrUtil;
import javafx.application.Application;
import javafx.scene.control.Alert;

/**
 * @author zhoujunlin
 * @date 2024-11-08-11:44
 */
public abstract class BaseApplication extends Application {

    protected void alert(Alert.AlertType alertType, String title, String message) {
        this.alert(alertType, title, null, message);
    }

    protected void alert(Alert.AlertType alertType, String title, String headerText, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(StrUtil.blankToDefault(headerText, null));
        alert.setContentText(message);
        alert.showAndWait();
    }

}

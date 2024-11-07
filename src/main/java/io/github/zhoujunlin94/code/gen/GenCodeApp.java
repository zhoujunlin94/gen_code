package io.github.zhoujunlin94.code.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.component.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2024-05-09-16:43
 */
public class GenCodeApp extends Application {

    public static final List<AbstractGenCodeComponent> GEN_CODE_COMPONENTS = CollUtil.newArrayList(
            new GenEntityCodeComponent(), new GenDTOCodeComponent(), new GenPageQueryDTOCodeComponent(), new GenVOCodeComponent(),
            new GenMapperCodeComponent(), new GenMapperXmlCodeComponent(), new GenHandlerCodeComponent(),
            new GenServiceCodeComponent(), new GenServiceImplCodeComponent(), new GenEndpointCodeComponent()
    );

    public static void run() {
        Setting context = new Setting("genCode.setting");
        String tableNames = context.get("tables");
        for (String tableName : StrUtil.splitTrim(tableNames, StrUtil.COMMA)) {
            Table table = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
            AbstractGenCodeComponent.initContext(table, context);
            GEN_CODE_COMPONENTS.forEach(component -> component.genCode(table, context));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

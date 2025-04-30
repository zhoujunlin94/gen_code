package io.github.zhoujunlin94.code.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.common.SettingContext;
import io.github.zhoujunlin94.code.gen.component.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-05-09-16:43
 */
public class GenCodeApp {

    public static final Map<String, List<AbstractGenCodeComponent>> GEN_CODE_COMPONENTS_MAP = new HashMap<>();

    static {
        GEN_CODE_COMPONENTS_MAP.put("base", CollUtil.newArrayList(new GenEntityCodeComponent(), new GenMapperCodeComponent(), new GenMapperXmlCodeComponent(), new GenHandlerCodeComponent()));
        GEN_CODE_COMPONENTS_MAP.put("endpoint", CollUtil.newArrayList(new GenPageQueryDTOCodeComponent(), new GenVOCodeComponent(), new GenDTOCodeComponent(), new GenServiceCodeComponent(), new GenServiceImplCodeComponent(), new GenEndpointCodeComponent()));
    }

    public static void run() {
        DataSource dataSource = DSFactory.create(SettingContext.getSetting("db.setting")).getDataSource();
        Setting context = SettingContext.getSetting("genCode.setting");
        String tableNames = context.get("tables");
        List<String> modes = StrUtil.splitTrim(context.get("mode"), StrUtil.COMMA);

        for (String tableName : StrUtil.splitTrim(tableNames, StrUtil.COMMA)) {
            Table table = MetaUtil.getTableMeta(dataSource, tableName);
            AbstractGenCodeComponent.initContext(table, context);
            modes.forEach(mode -> GEN_CODE_COMPONENTS_MAP.getOrDefault(mode, new ArrayList<>())
                    .forEach(component -> component.genCode(table, context)));
        }
    }

    public static void main(String[] args) {
        run();
    }


}


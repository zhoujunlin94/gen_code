package io.github.zhoujunlin94.code.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.code.gen.component.AbstractGenCodeComponent;
import io.github.zhoujunlin94.code.gen.component.GenEntityCodeComponent;
import io.github.zhoujunlin94.code.gen.component.GenMapperCodeComponent;
import io.github.zhoujunlin94.code.gen.component.GenMapperXmlCodeComponent;
import io.github.zhoujunlin94.code.gen.constant.CommonConstant;

import java.io.IOException;
import java.util.List;

/**
 * @author zhoujunlin
 * @date 2024-05-09-16:43
 */
public class GenCodeApp {

    public static final List<AbstractGenCodeComponent> GEN_CODE_COMPONENTS = CollUtil.newArrayList(
            new GenEntityCodeComponent(), new GenMapperCodeComponent(), new GenMapperXmlCodeComponent()
    );

    public static void run() {
        Setting context = new Setting(CommonConstant.GEN_CODE_SETTING);
        String tableName = context.get(CommonConstant.TABLE);
        Table table = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
        AbstractGenCodeComponent.initContext(table, context);
        GEN_CODE_COMPONENTS.forEach(component -> component.genCode(table, context));
    }


    public static void main(String[] args) throws IOException {
        run();
    }


}

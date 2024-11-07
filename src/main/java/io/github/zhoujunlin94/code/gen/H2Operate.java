package io.github.zhoujunlin94.code.gen;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.ds.DSFactory;
import io.github.zhoujunlin94.code.gen.common.SettingContext;
import lombok.SneakyThrows;

import javax.sql.DataSource;

/**
 * @author zhoujunlin
 * @date 2024-11-06-13:32
 */
public class H2Operate {

    private static final DataSource DATA_SOURCE = DSFactory.create(SettingContext.getSetting("h2.setting")).getDataSource();
    private static final Db DB = DbUtil.use(DATA_SOURCE);

    @SneakyThrows
    public static void main(String[] args) {
        //ddl();
        //dml();
        dql();
    }


    @SneakyThrows
    private static void ddl() {
        DB.execute(
                "CREATE TABLE IF NOT EXISTS db_conf (\n" +
                        "    id INT AUTO_INCREMENT PRIMARY KEY, \n" +
                        "    db_name VARCHAR(255), \n" +
                        "    db_url VARCHAR(255),\n" +
                        "    db_username VARCHAR(64),\n" +
                        "    db_password VARCHAR(128)\n" +
                        ")");
    }

    @SneakyThrows
    private static void dml() {
//        DB.insert(Entity.create("db_conf")
//                .set("db_name", "test")
//                .set("db_url", "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&useUnicode=true&autoReconnect=true&failOverReadOnly=false&useAffectedRows=true")
//                .set("db_username", "test")
//                .set("db_password", "test"));
//
//        DB.update(Entity.create().set("db_username", "test").set("db_password", "test"),
//                Entity.create().setTableName("db_conf").set("id", "1"));
    }

    @SneakyThrows
    private static void dql() {
        System.out.println(DB.query("select * from db_conf where id = ? ", 1));
    }

}

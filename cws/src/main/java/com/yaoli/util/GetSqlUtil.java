package com.yaoli.util;

import com.yaoli.common.obsoleted.SysSpringContextUtil;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

/**
 * Created by will on 2016/11/18.
 */
public class GetSqlUtil {

    public static void printSql(String mapperStatement, Map<String,String> parameters){
        SqlSessionFactory s = SysSpringContextUtil.getSessionFactory();
        Configuration configuration = s.getConfiguration();
        MappedStatement ms = configuration.getMappedStatement(mapperStatement);
        BoundSql boundSql = ms.getBoundSql(parameters); // pass parameters for the SQL statement
        System.out.println(boundSql.getSql());
    }
}

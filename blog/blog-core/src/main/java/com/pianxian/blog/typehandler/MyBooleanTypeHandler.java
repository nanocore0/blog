package com.pianxian.blog.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义 Boolean 类型处理器
 * JavaType Boolean
 * jdbcType int
 */
public class MyBooleanTypeHandler implements TypeHandler<Boolean> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        // 把 true 设置为 1, false 设置为 0
        ps.setInt(i, parameter ? 1 : 0);
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        int isTure = rs.getInt(columnName);
        return isTure == 1;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        int isTrue = rs.getInt(columnIndex);
        return isTrue == 1;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int isTrue = cs.getInt(columnIndex);
        return isTrue == 1;
    }
}

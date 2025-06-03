package beilaya.javademo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public final class SqlSessionUtil {
    private static final SqlSessionFactory SQLSESSION_FACTORY;
    private static final ThreadLocal<SqlSession> SQLSESSION_HOLDER = new ThreadLocal<>();

    static {
        try {
            SQLSESSION_FACTORY = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = SQLSESSION_HOLDER.get();
        if (sqlSession == null) {
            sqlSession = SQLSESSION_FACTORY.openSession();
            SQLSESSION_HOLDER.set(sqlSession);
        }
        return sqlSession;
    }

    public static void commit() {
        SqlSession sqlSession = SQLSESSION_HOLDER.get();
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    public static void rollback() {
        SqlSession sqlSession = SQLSESSION_HOLDER.get();
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }

    public static void close() {
        SqlSession sqlSession = SQLSESSION_HOLDER.get();
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}

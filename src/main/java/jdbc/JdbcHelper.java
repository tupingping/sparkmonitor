package jdbc;

import conf.ConfigurationManager;
import constant.Constants;
import logger.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @File : JdbcHelper.java
 * @Author: tupingping
 * @Date : 2019/7/2
 * @Desc :
 */
public class JdbcHelper {
    static {
        try{
            Class.forName(ConfigurationManager.getString(Constants.JDBC_DRIVER_NAME));
        }catch (Exception e){
            //TODO: handle exception
            LogUtil.getInstance().error("get jdbc driver failed.", e);
        }
    }

    private static JdbcHelper instance = null;
    private LinkedList<Connection> datasource = new LinkedList<Connection>();

    private JdbcHelper(){
        Integer dataSourceSize = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
        String username = ConfigurationManager.getString(Constants.JDBC_USER);
        String password = ConfigurationManager.getString(Constants.JDBC_PASSWORD);
        String url = ConfigurationManager.getString(Constants.JDBC_URL);

        try{
            for (int i = 0; i < dataSourceSize; i++){
                Connection connection = DriverManager.getConnection(url, username, password);
                datasource.push(connection);
            }

        }catch (Exception e){
            LogUtil.getInstance().error("build the connect pool failed.", e);
        }
    }

    public Connection getConnection(){
        while (datasource.size() == 0){
            try{
                Thread.sleep(10);
            }catch (Exception e) {
                //TODO:
            }
        }
        return datasource.poll();
    }

    public static JdbcHelper getInstance(){
        synchronized (JdbcHelper.class){
            if(instance == null){
                instance = new JdbcHelper();
            }
        }
        return instance;
    }

    public int executeUpdate(String sql, Object[] params){
        int rt = 0;
        Connection connection = getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            rt = preparedStatement.executeUpdate();
        }catch (Exception e){
            LogUtil.getInstance().error("sql excuteUpdate failed.", e);
        }finally {
            if(connection != null){
                datasource.push(connection);
            }
        }

        return rt;
    }

    public static interface QueryCallback{
        public void process(ResultSet rs) throws Exception;
    }

    public int executeQuery(String sql, Object[] params, QueryCallback callback){
        int rt = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            callback.process(resultSet);
        }catch (Exception e){
            LogUtil.getInstance().error("sql excuteQuery failed.", e);
        }finally {
            if(connection != null){
                datasource.push(connection);
            }
        }

        return rt;
    }

    public int executeUpdateBatch(String sql, List<Object[]> paramsList){
        int rt = 0;
        //TODO:
        Connection connection = getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < paramsList.size(); i++){
                for (int j = 0; j < paramsList.get(i).length; j++) {
                    preparedStatement.setObject(j + 1, paramsList.get(i)[j]);
                }
                preparedStatement.addBatch();

                if(i % 1000 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }

            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

        }catch (Exception e){
            LogUtil.getInstance().error("sql executeUpdateBatch failed.", e);
            try{
                connection.rollback();
            }catch (Exception ex){
                LogUtil.getInstance().error("sql executeUpdateBatch rollback failed.", ex);
            }
        }finally {
            if(connection != null){
                datasource.push(connection);
            }
        }
        return rt;
    }
}

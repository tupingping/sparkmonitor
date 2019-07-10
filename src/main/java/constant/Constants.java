package constant;

/**
 * @File : Constants.java
 * @Author: tupingping
 * @Date : 2019/7/2
 * @Desc :
 */
public interface Constants {
    // jdbc connect info
    String JDBC_DRIVER_NAME = "jdbc.driver.name";
    String JDBC_DATASOURCE_SIZE = "jdbc.source.size";
    String JDBC_URL = "jdbc.url";
    String JDBC_USER = "jdbc.user";
    String JDBC_PASSWORD = "jdbc.password";

    // http connect info
    String HTTP_CONNECT_SIZE = "http.connect.size";
    String HTTP_TIME_OUT = "http.timeout";
    String HTTP_VALIDATE_INACTIVITY = "http.validate.inactivity";

    //spark
    String SPARKSQL_SERVER_IP = "sparksql.server.ip";
    String SPARKSQL_SERVER_PORT = "sparksql.server.port";

    // spark url
    String SPARK_APPLICATIONS = "spark.application.info";
    String SPARK_JOBS = "spark.job.info";
    String SPARK_STAGES = "spark.stage.info";
}

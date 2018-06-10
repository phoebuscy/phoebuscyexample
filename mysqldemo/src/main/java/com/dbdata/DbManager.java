package com.dbdata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbManager
{
    private static Logger LogApp = LogManager.getLogger("applog");
    private final String dbname = "twsdb";
    private String userName = "root";
    private String password = "try258TRY";
    private Connection connection;

    private static DbManager instance = new DbManager();


    public static void main(String[] args)
    {
        DbManager dbManager = getInstance();

        List<Integer> reqidLst = new ArrayList<Integer>();
        for(int i = 0; i < 10000; i++)
        {
            int reqid = dbManager.queryReqID();
            reqidLst.add(reqid);
        }

        int a = 1;
    }


    private DbManager()
    {
        initDb();
    }

    public static DbManager getInstance()
    {
        return instance;
    }

    private void initDb()
    {
        Connection mysqlConn = connectMySql(userName, password);
        createTwsDb(mysqlConn, dbname);  // 如果不存在则创建TwsDb数据库
        connection = connectDB(dbname, userName, password);
        createTables(connection);  // 创建各种表
        createProcedure(connection);
    }

    // 创建数据库的各种表
    private void createTables(Connection connection)
    {
        createQueryIDTable(connection);

    }

    // 创建数据库的存储过程
    private void createProcedure(Connection connection)
    {
        createQueryReqIdProc(connection);
    }

    private void createTwsDb(Connection mysqlConn, String dbname)
    {
        if (mysqlConn != null)
        {
            try
            {
                Statement statement = mysqlConn.createStatement();
                String hrappSQL = "CREATE DATABASE  IF NOT EXISTS " + dbname;  // 加上IF NOT EXISTS就算数据库已经存在，把原来的覆盖掉了
                int ret = statement.executeUpdate(hrappSQL);
                statement.close();
                mysqlConn.close();
            }
            catch (SQLException ex)
            {
                LogApp.error("DbManager createTwsDb failed");
                ex.printStackTrace();
            }
        }
    }

    /**
     * 连接到数据库
     */
    private Connection connectDB(String dbname, String userName, String password)
    {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            LogApp.error("add mysql jdbc driver failed");  // 找不到驱动！
            e.printStackTrace();
        }
        try
        {
            connection = DriverManager.getConnection(url, userName, password);
            if (connection != null)
            {
                LogApp.info("connection successful");
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            LogApp.error("connection fail");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 连接到数据库
     */
    private Connection connectMySql(String userName, String password)
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            LogApp.error("add mysql jdbc driver failed");  // 找不到驱动！
            e.printStackTrace();
        }
        try
        {
            conn = DriverManager.getConnection(url, userName, password);
            if (conn != null)
            {
                LogApp.info("connection successful");
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            LogApp.error("connection fail");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 创建查询id表
     *
     * @param connection
     */
    private void createQueryIDTable(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                //加载驱动
                Class.forName("com.mysql.jdbc.Driver");
                //链接到数据库
                String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC";
                //获取对象
                Statement stmt = connection.createStatement();
                String delTableSqlStr = "drop table if exists queryidtable;";
                stmt.execute(delTableSqlStr);

                //插入记录到数据库中
                String sqlstr = "create table queryidtable(reqid int(1));";
                int ret = stmt.executeUpdate(sqlstr);

                String sqlInsertData = "insert into queryidtable values(100000000)";
                stmt.executeUpdate(sqlInsertData);


                System.out.println("创建成功！");
                stmt.close();
            }
            catch (Exception e)
            {
                System.out.println("创建失败！或已经存在该表格" + e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建查询reqid的存储过程
     * @param connection
     */
    private void createQueryReqIdProc(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                Statement statement = connection.createStatement();
                String sqlStr = "create procedure queryReqIDProc(out id int)" +
                                "BEGIN " +
                                "update queryidtable set reqid=reqid+1;" +
                                "select reqid into id from queryidtable;" +
                                "END ";
                statement.executeUpdate(sqlStr);
            }
            catch (SQLException e)
            {
                LogApp.error("DbManager createQueryReqIdProc failed");
                e.printStackTrace();
            }
        }
    }

    public int queryReqID()
    {
        int retid = 0;
        if(connection != null)
        {
            try
            {
                CallableStatement cs = connection.prepareCall("{call queryReqIDProc(?)}");
                cs.registerOutParameter(1, Types.INTEGER);
                cs.execute();
                retid = cs.getInt(1);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        return retid;
    }


}

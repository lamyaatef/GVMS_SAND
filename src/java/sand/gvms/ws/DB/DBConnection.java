package sand.gvms.ws.DB;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;






public class DBConnection
{

  private static Connection con = null;
  
  
 private static String strCon = "";
   private static String userName = "";
   private static String password = "";




    static {
    IniEditor dbParams = new IniEditor();

    try
    {
        String x = new File("GVMS/DB_Config.ini").getAbsolutePath();
//          String x = new File("DB_Config.ini").getAbsolutePath();

        System.out.println("ini path is "+x);
        dbParams.load(x);

        strCon = dbParams.get("dbgvms-prod", "Connection");
                        userName = dbParams.get("dbgvms-prod", "userName");
                        password = dbParams.get("dbgvms-prod", "password");                        

        System.out.println("x is "+x);
    }
    catch(Exception e)
    {
       
    }

    }  
  
  public static synchronized void cleanResultSet(ResultSet rs)
  {

               Statement st = null;
                  try
                  {
                	  if (rs!=null)st = rs.getStatement();
                	  st.close();
                    rs.close();
                  }
                  catch (SQLException e)
                  {
                	  rs=null;
                	  st=null;
                  }
                  finally
                  {
                	  rs=null;
                	  st=null; 
                  }
               

  }
  

  public static synchronized void closeConnections(Connection con){

      if (con!=null)
      {
            try {
                con.close();
                con = null;
            } catch (SQLException ex) {
                
            }
      }

  }
  public static synchronized Connection getConnection()
    throws SQLException
  { 
    
 java.util.Properties connAttr = new Properties();
     connAttr.setProperty("user", userName);
     connAttr.setProperty("password", password);
    try
      {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(strCon, connAttr); 
      }
    catch (Exception objExp)
      {
        objExp.printStackTrace();
      }

    return con;
  }

  
  
  

}
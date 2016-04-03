//  1.  History information.
    /*
    ** FILE: SMSDbHelper.java
    **
    ** ABSTRACT:
    **   Maintains the connection to Db
    **
    ** DOCUMENTS:
    **
    ** AUTHOR:
    **   Ashraf Fouad Ayoub - AF
    **
    ** CREATION DATE:
    **   18/04/2001
    **
    ** NOTES:
    **
    ** HISTORY:
    ** 000 - Apr 18 - AF - Creation
    ** 001 - Apr 30 - AF - Added javadoc @exception clause
    **
    */

//  2.  Package identifier.

package sand.gvms.ws.DB;

import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;


/**
 *  This Class maintains the connection to Db
 *
 *  @author Ashraf Fouad
 */

public class SMSDbHelper extends Object
{
    public SMSDbHelper()
    {
    }

    /**
     *  This function takes the database parameters as an input parameters
     *  and return a DB connection using these parameters
     *
     *  @param szjdbcDriver is the jdbc driver
     *  @param szdbUrl is the DB URL
     *  @param szuserName is the DB user
     *  @param szpassWord is the DB password
     *
     *  @return Connection to the DB
     *
     *  @Exception throws Exception of getting connection
     */
    public static Connection getDbConnection( )
        throws Exception
    {
        // this function is updated due to the use of OC4J Data sources
        // By Hassan Thabet Hassan At 2/7/2002
        /*
        Driver d = (Driver)Class.forName(szjdbcDriver).newInstance();
        return DriverManager.getConnection( szdbUrl, szuserName, szpassWord );
        */
       // InitialContext ic = new InitialContext();
        //DataSource ds = (DataSource)ic.lookup("jdbc:PooledEbillSMSDS");
        //return ds.getConnection();
    	
    	Connection objectCon = null;
        try 
        {
        	String szjdbcDriver="oracle.jdbc.driver.OracleDriver";
        	String szdbUserName="sdbx";
        	String szdbPassWord="sdbx";
        	String szdbUrl="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.11.123.10)(PORT=1521)))(CONNECT_DATA=(SID=CRMDB)(SERVER=DEDICATED)))";
        	
       	 System.out.println("step 1 SMScon is "+szdbUrl);
       	 System.out.println("step 2 SMScon is "+szdbUserName);
       	 System.out.println("step 3 SMScon is "+szdbPassWord);
       	 Class.forName(szjdbcDriver);
            

          objectCon =  DriverManager.getConnection(szdbUrl,szdbUserName,szdbPassWord);
          System.out.println("here to return conn ejb");
          
          return objectCon;
        } catch (Exception ex) 
        {
        
          ex.printStackTrace();
                    System.out.println("here to return null");
          return null;
        } 
    }
    

    /**
     *  This function is used to cleanup a callable statment
     *
     *  @param callableStatment is the callable statment to be cleaned, and is
     *         set to null after cleaning
     *
     *  @exception if the callable statement won't close
     */
    public static void cleanCallableStatement(
                            CallableStatement callableStatment )
        throws SQLException
    {
        if ( callableStatment != null ) {
            callableStatment.close();
            callableStatment = null;
        }
    }

    /**
     *  This function is used to cleanup a prepared statment
     *
     *  @param preparedStatment is the prepared statment to be cleaned, and is
     *         set to null after cleaning
     *
     *  @exception if the prepared statement won't close
     */
    public static void cleanPreparedStatement(
                            PreparedStatement preparedStatment )
        throws SQLException
    {
        if ( preparedStatment != null ) {
            preparedStatment.close();
            preparedStatment = null;
        }
    }

    /**
     *  This function is used to cleanup a result set
     *
     *  @param resultSet is the result set to be cleaned, and is set to null
     *         after cleaning
     *
     *  @exception if the result set won't close
     */
    public static void cleanResultSet( ResultSet resultSet )
        throws SQLException
    {
        if ( resultSet != null ) {
            resultSet.close();
            resultSet = null;
        }
    }

    /**
     *  This function is used to cleanup a connection
     *
     *  @param connection is the connection to be cleaned, and is set to null
     *         after cleaning
     *
     *  @exception if the connection won't close
     */
    public static void cleanConnection( Connection connection )
        throws SQLException
    {
        connection.close();
        // this function is updated due to the use of OC4J Data sources
        // i Comment the connection = null line
        // By Hassan Thabet Hassan At 2/7/2002

        //connection = null;
    }
}

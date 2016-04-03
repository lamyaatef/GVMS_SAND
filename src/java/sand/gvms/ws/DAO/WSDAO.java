/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sand.gvms.ws.DAO;

import sand.gvms.ws.DB.DBConnection;
import sand.gvms.ws.DB.SMSDbHelper;
import sand.gvms.ws.DB.IniEditor;
import sand.gvms.ws.DTO.CSLogsDTO;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleTypes;
import sand.gvms.ws.InterfaceKey.InterfaceKeys;

/**
 *
 * @author mabdelaal
 */
public class WSDAO {

    public static void insertLogs(String dial, String gift_id, String userName, String password, String comment, int method, int statusId) {

        StringBuilder SQL = new StringBuilder();

        SQL.append(" Insert into GVMS_WS_LOGS(CAMPAIGN_ID, STATUS_ID, TYPE_ID, ACTION_DATE, COMMENTS,DIAL_NUMBER,GIFT_ID)Values");
        SQL.append(" (nvl((select campaign_id from GVMS_SYSTEM_SERVER where SERV_USER_NAME='").append(userName).append("' and SERV_PASSWORD='").append(password).append("'),0),");
        SQL.append(statusId == InterfaceKeys.DB_STATUS_SUCCESS ? InterfaceKeys.DB_STATUS_SUCCESS : InterfaceKeys.DB_STATUS_FAIL);
        SQL.append(",");
        SQL.append(method == InterfaceKeys.DB_TYPE_GET_GIFTS ? InterfaceKeys.DB_TYPE_GET_GIFTS : InterfaceKeys.DB_TYPE_ISSUE_GIFT);
        SQL.append(",sysdate,'user name is ");
        SQL.append(checkNullOrEmptyValue(userName));
        SQL.append(" & password is ");
        SQL.append(checkNullOrEmptyValue(password));
        SQL.append(" & comment is ");
        SQL.append(checkNullOrEmptyValue(comment));
        SQL.append("','");
        SQL.append(checkNullOrEmptyValue(dial));
        SQL.append("','");
        SQL.append(checkNumericValue(gift_id));
        SQL.append("')");
        System.out.println(SQL);
        Connection conn = null;
        Statement st = null;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            st.executeUpdate(SQL.toString());

        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());


            try {
                conn.close();
                st.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
            }

            conn = null;
            st = null;
        }


    }

    public static String insertDialGenerate(String dial, String gift_id, String userName, String password) {

        int countOfGeneration = 1;
        PreparedStatement ps = null;
        int validateInsertion = 0;
        Statement st = null;
        ResultSet rs = null;
        String getCamId = "(select campaign_id from GVMS_SYSTEM_SERVER where SERV_USER_NAME='" + userName + "' and SERV_PASSWORD='" + password + "')";
        String randomNumber = "";
        Connection conn = null;
        int countOfExecution = 0;
        String sqlRandomNumber = "select round(dbms_random.value(10000000, 99999999)) ran from dual";
        StringBuffer SQL = new StringBuffer(
                "insert into GVMS_current_voucher(current_voucher_ID,campaign_id,voucher_status_id,gift_id,CURRENT_VOUCHER_END_DATE,current_voucher_dial_number, current_voucher_number,GIFT_TYPE_ID,USER_TYPE_ID,USER_ID,VOUCHER_EXPIRY_DATE,GIFT_VALUE) values (GVMS_CURRENT_VOUCHER_SEQ_ID.nextval, ");
        SQL.append(getCamId);
        SQL.append(",2,");
        SQL.append(gift_id);
        SQL.append(" ,sysdate,");
        SQL.append(dial);
        SQL.append(",?,(select GIFT_TYPE_ID from GVMS_GIFT where gift_id=");
        SQL.append(gift_id);
        SQL.append(")");
        SQL.append(",'0','0',");
        SQL.append("(SYSDATE + (nvl( (select VOUCHER_EXPIRE_DAYS from GVMS_CAMPAIGN  where CAMPAIGN_ID=");
        SQL.append(getCamId);
        SQL.append(") ,");
        SQL.append(getParamterFromIni("DefaultExpireVoucherDays"));
        SQL.append(")))");
        SQL.append(",(select GIFT_VALUE from GVMS_GIFT where GIFT_ID= ");
        SQL.append(gift_id);
        SQL.append(")");
        SQL.append(")");


        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL.toString());
            st = conn.createStatement();
            for (int i = 0; i < countOfGeneration; i++) {


                rs = st.executeQuery(sqlRandomNumber);
                if (rs.next()) {
                    randomNumber = rs.getString(1);
                }
                DBConnection.cleanResultSet(rs);
                // System.out.println("randomNumber is   "+randomNumber);
                System.out.println("insert voucher sql is   " + SQL);
                ps.setString(1, randomNumber);



                validateInsertion = ps.executeUpdate();
                if (validateInsertion > 0) {

                
                    CSLogsDTO cslog = new CSLogsDTO();
                    cslog.setDail_number(dial);
                    cslog.setUser_name("Any");
                    cslog.setVoucher_number(randomNumber);
                    cslog.setCampaign_id(getCamId);
                    insertLog(conn, gift_id, getCamId, randomNumber, dial);
                    
                    insertSMS(conn, cslog , gift_id);
                    
//               if (SMSStatus==-2)
//            	   return SMSStatus;
                }

                countOfExecution += validateInsertion;

            }
            try {
                DBConnection.closeConnections(conn);
                ps.close();


            } catch (SQLException e1) {
                // TODO Auto-generated catch block
            }


        } catch (Exception e) {

            System.out.println("Exception is " + e.getMessage());
            DBConnection.cleanResultSet(rs);
            try {
                conn.close();
                ps.close();


            } catch (SQLException e1) {
                // TODO Auto-generated catch block
            }

            conn = null;
            ps = null;
            st = null;
            rs = null;
            if (e.getMessage().toLowerCase().contains("unique")) {
                if (countOfExecution == 0) {
                    countOfExecution = countOfGeneration;
                }

                System.out.println("here in second execution for unique voucher");
                insertDialGenerate(dial, gift_id, userName, password);
            }
            return "";

        }

        return randomNumber;

    }
    

    public static int insertSMS(Connection con, CSLogsDTO cslogs, String giftid) throws Exception {

       System.out.println("gift id = "+ giftid);
       try
       {    
           String smsid = null;
            Connection  conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT sms_id FROM gvms_gift WHERE gift_id =" + giftid; 
            
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {
                smsid = rs.getString("sms_id");
                
            }
            rs.close();
            
            if ( smsid == null || smsid.compareTo("")==0)
            {
                sql = " SELECT * FROM gvms_sms_templates WHERE default_sms = 1 ";
            }
            else
            {
                sql = "SELECT * FROM gvms_sms_templates WHERE sms_id = " + smsid ;
            }
            rs = st.executeQuery( sql);
            String smsText = "";
            if (rs.next())
            {
                smsText = rs.getString ("sms_text_english");
            }
            smsText = smsText.replace("#1", cslogs.getVoucher_number());
                   
           
             st.executeUpdate("insert into GVMS_SMS values(GVMS_CS_SMS_SEQ_ID.nextval,'Mobinil', '" + cslogs.getDail_number() + "', '" + smsText + "', sysdate )");
              st.close();
        return insertInSMSTable(cslogs.getDail_number(), smsText);
   
            
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return 0 ; 
   }
    public static int insertLog(Connection con, String giftId,String campId, String vouNum , String dialNumber) throws Exception {


        StringBuilder sql = new StringBuilder("Insert into GVMS_CS_LOGS (");
        sql.append(" ID, SYSTEM_USER_ID, ACTION_TEXT_ID, ACTION_DATE, GIFT_ID,  ");
        sql.append("CAMPAIGN_ID, VOUCHER_NUMBER, DAIL_NUMBER, USER_TYPE_ID, USER_ID,  ");
        sql.append("ACCOUNT_NUMBER, REDEMPTION_DATE) ");
        sql.append("Values ");
        sql.append("(GVMS_CS_LOGS_SEQ_ID.nextval, -1, 1, sysdate, '").append(giftId).append("',  ");
        sql.append(campId).append(", '").append(vouNum).append("', '").append(dialNumber).append("', 0, 0,  ");
        sql.append("'', NULL) ");

        System.out.println("sql for logging is "+sql);
        Statement st = con.createStatement();
        int result = st.executeUpdate(sql.toString());
        st.close();
        return result;
    }

    public static String getGifts(String userName, String password) {
        String sql = "select campaign_id,GVMS_GET_GIFTS(campaign_id) giftsArr from GVMS_SYSTEM_SERVER where SERV_USER_NAME='" + userName + "' and SERV_PASSWORD='" + password + "'";
        Connection conn = null;
        String returnValue;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                returnValue = rs.getString("giftsArr").replaceAll("-", "|");
            } else {
                returnValue = null;
            }

        } catch (Exception e) {
            returnValue = null;
        } finally {
            try {
                DBConnection.closeConnections(conn);
                DBConnection.cleanResultSet(rs);
                st.close();
            } catch (Exception e) {
            }


        }

        return returnValue;


    }

    
    
    
    
    public static int insertInSMSTable(String szreceiverMSISDN,
            String szmessageText) {
        System.out.println("SMS String isssssssssssssssssssssssssssss " + szmessageText);
//        int myint = -1;
        int myint = -2;
        try {

         Connection e = null;
            String strUsername = "ebill";
            String strPassword = "llibepass";
            byte iSMSBatchID = 111;
            e = SMSDbHelper.getDbConnection();
            String sql = "{call ? := xeno_gvms_pkg.AddCustomPendingSMSToBatch(?,?,?)}";
            CallableStatement stat = e.prepareCall(sql);
            szreceiverMSISDN = szreceiverMSISDN.startsWith("0")?szreceiverMSISDN.substring(1):szreceiverMSISDN;
            stat.registerOutParameter(1, 2);
            stat.setInt(2, iSMSBatchID);
            stat.setString(3, szmessageText);
            stat.setString(4, szreceiverMSISDN);
            stat.execute();
            myint = stat.getInt(1);
        } catch (Exception e) {e.printStackTrace();
        return -2;

        }


        return myint;
    }

    public static String getParamterFromIni(String paramter) {
        IniEditor dbParams = new IniEditor();
        String dd = "";
        try {
            String x = new File("GVMS/DB_Config.ini").getAbsolutePath();
            //          String x = new File("DB_Config.ini").getAbsolutePath();

            System.out.println("ini path is " + x);
            dbParams.load(x);

            dd = dbParams.get("dbgvms-prod", paramter);

            System.out.println("x is " + x);
        } catch (Exception e) {
        }
        return dd;

    }

    private static String checkNullOrEmptyValue(String value) {
        if (value == null || value.compareTo("") == 0) {
            return "";
        } else {
            return value;
        }
    }

    private static String checkNumericValue(String value) {
        String checkNull = checkNullOrEmptyValue(value);
        if (checkNull.compareTo("''") == 0) {
            return "";
        } else {
            try {
                Integer.parseInt(value);
                return value;
            } catch (Exception e) {
                return "";
            }
        }

    }
    public static int getVoucherExpiry(Connection con, String campaginQueryId) throws SQLException {
        int voucherExpiryDays = 0;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select nvl(VOUCHER_EXPIRE_DAYS," + getParamterFromIni("DefaultExpireVoucherDays") + ") expire_days from GVMS.GVMS_CAMPAIGN where campaign_id=" + campaginQueryId);
        if (rs.next()) {
            voucherExpiryDays = rs.getInt("expire_days");
        } else {
            voucherExpiryDays = 30;
        }
        try {
            rs.close();
            st.close();
        } catch (Exception e) {
        }
        return 0;
    }
}

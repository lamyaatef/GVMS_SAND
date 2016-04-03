/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sand.gvms.ws.InterfaceKey;

/**
 *
 * @author mabdelaal
 */
public class InterfaceKeys {

    /*----------------------Functions--------------------------*/

    public static final String FUNCTION_GET_GIFTS = "getGifts";
    public static final String FUNCTION_ISSUE_GIFT = "issueGift";

    public static final int DB_STATUS_SUCCESS = 1;
    public static final int DB_STATUS_FAIL = 2;


    public static final int DB_TYPE_GET_GIFTS= 1;
    public static final int DB_TYPE_ISSUE_GIFT= 2;

    public static final int getGiftMethod = 1;
    public static final int issueGiftMethod = 2;



    /*----------------------Flags--------------------------*/

    public static final int FOUNGING_SUCCESS_FOR_BASIC_PARAMETERS = 1;
    public static final int INVALID_USER_NAME = -1;
    public static final int INVALID_PASSWORD = -2;
    public static final int INVALID_GIFT_ID = -3;
    public static final int INVALID_DIAL_NUMBER = -4;
    public static final int INVALID_DIAL_NUMBER_FORMATE = -7;
    public static final int INVALID_GIFT_UNDER_CAMPAIGN = -5;
    public static final int USER_NAME_AND_PASSWORD_NOT_MATCH = -6;
    


/*----------------------Messages--------------------------*/

    public static final String FOUNGING_SUCCESS_FOR_BASIC_PARAMETERS_MESSAGE = "all_paramters_done";

    public static final String INVALID_USER_NAME_MSG = "Missing or invalid user name.";
    public static final String INVALID_PASSWORD_MSG = "Missing or invalid password.";
    public static final String INVALID_GIFT_ID_MSG = "Missing or invalid gift id.";
    public static final String INVALID_DIAL_NUMBER_MSG = "Missing or invalid dial number.";
    public static final String INVALID_DIAL_NUMBER_FORMATE_MSG = "invalid dial number format.";
    public static final String INVALID_GIFT_UNDER_CAMPAIGN_MSG = "Invalid gift id under campaign.";
    public static final String USER_NAME_AND_PASSWORD_NOT_MATCH_MSG = "Invalid user name and password.";
    



}

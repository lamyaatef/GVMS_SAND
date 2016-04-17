/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sand.gvms.ws.validation;

import javax.servlet.http.HttpServletRequest;
import sand.gvms.ws.DAO.WSDAO;
import sand.gvms.ws.InterfaceKey.InterfaceKeys;

/**
 *
 * @author mabdelaal
 */
public class ParametersValidation {

    public static String checkParametersInPassed(int method, String userName, String password, String giftId, String dialNumber) {




        return checkParametersValue(method, userName, password, giftId, dialNumber);
    }

    private static String checkParametersValue(int method, String userName, String password, String giftId, String dialNumber) {
        int returnCheckVal = 0;
        returnCheckVal = checkParametersValue(userName, InterfaceKeys.INVALID_USER_NAME);
        if (returnCheckVal != 0) {
            return returnMessage(InterfaceKeys.INVALID_USER_NAME);
        }
        returnCheckVal = checkParametersValue(password, InterfaceKeys.INVALID_PASSWORD);
        if (returnCheckVal != 0) {
            return returnMessage(InterfaceKeys.INVALID_PASSWORD);
        }
        String giftStr = WSDAO.getGifts(userName, password);
        if (giftStr == null) {
            return returnMessage(InterfaceKeys.USER_NAME_AND_PASSWORD_NOT_MATCH);
        } else if (method == InterfaceKeys.getGiftMethod) {
            return giftStr;
        }

        if (method == InterfaceKeys.issueGiftMethod) {

            returnCheckVal = checkParametersValue(giftId, InterfaceKeys.INVALID_GIFT_ID);
            if (returnCheckVal != 0) {
                return returnMessage(InterfaceKeys.INVALID_GIFT_ID);
            }
            returnCheckVal = checkParametersValue(dialNumber, InterfaceKeys.INVALID_DIAL_NUMBER);
            if (returnCheckVal != 0) {
                return returnMessage(InterfaceKeys.INVALID_DIAL_NUMBER);
            }
            if (!isGiftUnderCampaign(giftStr, giftId)) {
                return returnMessage(InterfaceKeys.INVALID_GIFT_UNDER_CAMPAIGN);
            }
            if (!isDialNumber(dialNumber)) {
                return returnMessage(InterfaceKeys.INVALID_DIAL_NUMBER_FORMATE);
            }
        }

        return InterfaceKeys.FOUNGING_SUCCESS_FOR_BASIC_PARAMETERS_MESSAGE;
    }

    private static int checkParametersValue(String parameters, int returnedValue) {
        if (parameters == null || parameters.compareTo("") == 0) {
            return returnedValue;
        } else {
            return 0;
        }

    }

    public static String returnMessage(int value) {
        switch (value) {
            case (InterfaceKeys.INVALID_USER_NAME): {
                return InterfaceKeys.INVALID_USER_NAME_MSG;
            }
            case (InterfaceKeys.INVALID_PASSWORD): {
                return InterfaceKeys.INVALID_PASSWORD_MSG;
            }
            case (InterfaceKeys.INVALID_GIFT_ID): {
                return InterfaceKeys.INVALID_GIFT_ID_MSG;
            }
            case (InterfaceKeys.INVALID_DIAL_NUMBER): {
                return InterfaceKeys.INVALID_DIAL_NUMBER_MSG;
            }
            case (InterfaceKeys.USER_NAME_AND_PASSWORD_NOT_MATCH): {
                return InterfaceKeys.USER_NAME_AND_PASSWORD_NOT_MATCH_MSG;
            }
            case (InterfaceKeys.INVALID_GIFT_UNDER_CAMPAIGN): {
                return InterfaceKeys.INVALID_GIFT_UNDER_CAMPAIGN_MSG;
            }
            case (InterfaceKeys.INVALID_DIAL_NUMBER_FORMATE): {
                return InterfaceKeys.INVALID_DIAL_NUMBER_FORMATE_MSG;
            }
            default: {
                return InterfaceKeys.FOUNGING_SUCCESS_FOR_BASIC_PARAMETERS_MESSAGE;
            }
        }


    }

    public static boolean isDialNumber(String dialNumber) {
        try {
            Double.parseDouble(dialNumber);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isGiftUnderCampaign(String giftsString, String giftId) {
        System.out.println("gift String "+giftsString+" gift Id "+giftId);
        boolean isGiftUnderCampaignFlag = false;
        //String[] giftsArr = giftsString.split(",");
       // String comGiftId = "";
        /*for (String giftStr : giftsArr) {
            comGiftId = giftStr.substring(0, giftStr.lastIndexOf("|"));
            System.out.println("comGiftId "+comGiftId+" gift id "+giftId);
            if (comGiftId.compareTo(giftId)==0) {
                isGiftUnderCampaignFlag = true;
                break;
            }
        }*/
        if (giftsString.contains(giftId))
            isGiftUnderCampaignFlag=true;
        return isGiftUnderCampaignFlag;
    }

    public static void main(String[] args) {
        String dd = "10-Gift S2 4,9-Gift S2 3,8-Gift S2 2,7-Gift S2 1,2-Gift S2";
        String[] giftsArr = dd.split(",");
        String comGiftId = "";
        for (String giftStr : giftsArr) {
            comGiftId = giftStr.substring(0, giftStr.lastIndexOf("-"));
            System.out.println(comGiftId);
            if (comGiftId.compareTo("555") == 0) {

                break;
            }
        }
    }
}

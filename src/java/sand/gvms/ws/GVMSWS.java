/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sand.gvms.ws;

import sand.gvms.ws.DAO.WSDAO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import sand.gvms.ws.InterfaceKey.InterfaceKeys;
import sand.gvms.ws.validation.ParametersValidation;

/**
 *
 * @author Owner
 */
@WebService()
public class GVMSWS {

    /**
     * Issue Gift Operation
     */
    @WebMethod(operationName = "issueGift")
    public String issueGift(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "giftId") String giftId, @WebParam(name = "dialNumber") String dialNumber) {
        System.out.println(username + " " + password + " " + giftId + " " + dialNumber);

        String returnVal = ParametersValidation.checkParametersInPassed(2, username, password, giftId, dialNumber);
        boolean isSuccess = false;
        if (returnVal.compareTo(InterfaceKeys.FOUNGING_SUCCESS_FOR_BASIC_PARAMETERS_MESSAGE) == 0) {
            WSDAO.insertDialGenerate(dialNumber, giftId, username, password);
            isSuccess = true;
        }
        WSDAO.insertLogs(dialNumber, giftId, username, password, returnVal, InterfaceKeys.DB_TYPE_ISSUE_GIFT, isSuccess ? InterfaceKeys.DB_STATUS_SUCCESS : InterfaceKeys.DB_STATUS_FAIL);

        return returnVal;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getGifts")
    public String getGifts(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password) {
        String returnVal = ParametersValidation.checkParametersInPassed(1, userName, password, "", "");
        System.out.println("The get gift return "+returnVal);
        WSDAO.insertLogs("", "", userName, password, returnVal == null ? "" : returnVal, InterfaceKeys.DB_TYPE_GET_GIFTS,
                returnVal != null && returnVal.compareTo("") != 0 ? InterfaceKeys.DB_STATUS_SUCCESS : InterfaceKeys.DB_STATUS_FAIL);
        return returnVal;
    }
//    @WebMethod(operationName = "returnArray")
//    public java.lang.String[][] returnArray(@WebParam(name = "organizationInfo") String[][] organizationInfo){
//        organizationInfo[0][0]="dddd";
//        organizationInfo[0][1]="dddd";
//        return organizationInfo;
//    }

    public static void main(String [] args)
    {
    GVMSWS ws = new GVMSWS();
//    ws.getGifts("s1", "s1");
//    ws.getGifts("ss", "ss");
//    ws.getGifts(null, null);
//    ws.getGifts("s1", null);
    ws.issueGift("s1", "s1", "3", "125566998");
    }
}

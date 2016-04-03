
package sand.gvms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "issueGift", namespace = "http://ws.gvms.sand/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issueGift", namespace = "http://ws.gvms.sand/", propOrder = {
    "username",
    "password",
    "giftId",
    "dialNumber"
})
public class IssueGift {

    @XmlElement(name = "username", namespace = "")
    private String username;
    @XmlElement(name = "password", namespace = "")
    private String password;
    @XmlElement(name = "giftId", namespace = "")
    private String giftId;
    @XmlElement(name = "dialNumber", namespace = "")
    private String dialNumber;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 
     * @param username
     *     the value for the username property
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @param password
     *     the value for the password property
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getGiftId() {
        return this.giftId;
    }

    /**
     * 
     * @param giftId
     *     the value for the giftId property
     */
    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDialNumber() {
        return this.dialNumber;
    }

    /**
     * 
     * @param dialNumber
     *     the value for the dialNumber property
     */
    public void setDialNumber(String dialNumber) {
        this.dialNumber = dialNumber;
    }

}


package sand.gvms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getGifts", namespace = "http://ws.gvms.sand/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGifts", namespace = "http://ws.gvms.sand/", propOrder = {
    "userName",
    "password"
})
public class GetGifts {

    @XmlElement(name = "userName", namespace = "")
    private String userName;
    @XmlElement(name = "password", namespace = "")
    private String password;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 
     * @param userName
     *     the value for the userName property
     */
    public void setUserName(String userName) {
        this.userName = userName;
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

}

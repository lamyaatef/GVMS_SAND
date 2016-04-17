package sand.gvms.ws.DTO;

import java.sql.Timestamp;

public class CSLogsDTO
{
   public final String DELETED="5";
   public final String NEW="4";
   public final String EXPIRED="3";
   public final String UNREDEEMED="2";
   public final String REDEEMED="1";
   private String id;
   private String system_user_id;
   private String cs_name;
   private String action_text_id;
   private Timestamp action_date;
   private Timestamp redemprion_date;
   private String gift_id;
   private String campaign_id;
   private String campaign_name;
   private String gift_name;
   private String action_text;
   private String voucher_number;
   private String dail_number ;
   private String account_number ;
   private String user_type_id ;
   private String user_type_name ;
   private String user_id ;
   private String user_name ;
   private String gift_money_value; 
   
   
   public String getAccount_number()
   {
      return account_number;
   }
   public void setAccount_number(String account_number)
   {
      this.account_number = account_number;
   }
   public String getUser_type_id()
   {
      return user_type_id;
   }
   public void setUser_type_id(String user_type_id)
   {
      this.user_type_id = user_type_id;
   }
   public String getUser_type_name()
   {
      return user_type_name;
   }
   public void setUser_type_name(String user_type_name)
   {
      this.user_type_name = user_type_name;
   }
   public String getUser_id()
   {
      return user_id;
   }
   public void setUser_id(String user_id)
   {
      this.user_id = user_id;
   }
   public String getUser_name()
   {
      return user_name;
   }
   public void setUser_name(String user_name)
   {
      this.user_name = user_name;
   }
   private String voucherStatus;
   private Boolean blockUnredeemedButton;
   private Boolean blockCancelButton;
   private Boolean blockSendButton;
   
   public String getId()   
   {
      return id;
   }
   public void setId(String id)
   {
      this.id = id;
   }
   public String getSystem_user_id()
   {
      return system_user_id;
   }
   public void setSystem_user_id(String system_user_id)
   {
      this.system_user_id = system_user_id;
   }
   public String getAction_text_id()
   {
      return action_text_id;
   }
   public void setAction_text_id(String action_text_id)
   {
      this.action_text_id = action_text_id;
   }
   public Timestamp getAction_date()
   {
      return action_date;
   }
   public void setAction_date(Timestamp action_date)
   {
      this.action_date = action_date;
   }
   public String getGift_id()
   {
      return gift_id;
   }
   public void setGift_id(String gift_id)
   {
      this.gift_id = gift_id;
   }
   public String getCampaign_id()
   {
      return campaign_id;
   }
   public void setCampaign_id(String campaign_id)
   {
      this.campaign_id = campaign_id;
   }
   public String getVoucher_number()
   {
      return voucher_number;
   }
   public void setVoucher_number(String voucher_number)
   {
      this.voucher_number = voucher_number;
   }
   public String getDail_number()
   {
      return dail_number;
   }
   public void setDail_number(String dail_number)
   {
      this.dail_number = dail_number;
   }
   public void setCampaign_name(String campaign_name)
   {
      this.campaign_name = campaign_name;
   }
   public String getCampaign_name()
   {
      return campaign_name;
   }
   public void setGift_name(String gift_name)
   {
      this.gift_name = gift_name;
   }
   public String getGift_name()
   {
      return gift_name;
   }
   public void setAction_text(String action_text)
   {
      this.action_text = action_text;
   }
   public String getAction_text()
   {
      return action_text;
   }
   public void setCs_name(String cs_name)
   {
      this.cs_name = cs_name;
   }
   public String getCs_name()
   {
      return cs_name;
   }
   public void setVoucherStatus(String voucherStatus)
   {
      this.voucherStatus = voucherStatus;
   }
   public String getVoucherStatus()
   {
      return voucherStatus;
   }
   public void setBlockUnredeemedButton(Boolean blockUnredeemedButton)
   {
      this.blockUnredeemedButton = blockUnredeemedButton;
   }
   public Boolean getBlockUnredeemedButton()
   {
      return blockUnredeemedButton;
   }
   public void setBlockCancelButton(Boolean blockCancelButton)
   {
      this.blockCancelButton = blockCancelButton;
   }
   public Boolean getBlockCancelButton()
   {
      return blockCancelButton;
   }
   public void setBlockSendButton(Boolean blockSendButton)
   {
      this.blockSendButton = blockSendButton;
   }
   public Boolean getBlockSendButton()
   {
      return blockSendButton;
   }
   public void setRedemprion_date(Timestamp redemprion_date)
   {
      this.redemprion_date = redemprion_date;
   }
   public Timestamp getRedemprion_date()
   {
      return redemprion_date;
   }

    /**
     * @return the gift_money_value
     */
    public String getGift_money_value() {
        return gift_money_value;
    }

    /**
     * @param gift_money_value the gift_money_value to set
     */
    public void setGift_money_value(String gift_money_value) {
        this.gift_money_value = gift_money_value;
    }
   
}

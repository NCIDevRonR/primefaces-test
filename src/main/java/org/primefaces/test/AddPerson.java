package org.primefaces.test;

import java.io.Serializable;
//import com.nciinc.ssda.entities.AccGrp;
//import com.nciinc.ssda.dao.PersonFastAddDAO;
//import com.nciinc.ssda.util.SSDAException;
//import com.nciinc.ssda.util.StringFixes;
import java.util.Date;
//import javax.ejb.EJB;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
//import org.apache.shiro.SecurityUtils;
//import org.slf4j.LoggerFactory;

@Named("addPerson")
@SessionScoped
public class AddPerson implements Serializable {

    private static final long serialVersionUID = 1L;

//    @EJB
//    private transient PersonFastAddDAO pfaEJBean;
//    private final String userName = SecurityUtils.getSubject().getPrincipal().toString();
//    private final String sysId = SecurityUtils.getSubject().getSession().getAttribute("sysId").toString();
//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddPerson.class.getName());
    private String lastname;
    private String firstname;
    private String middlename;
    private String email;
    private String rankid;
    private String homephone;
    private String workphone;
    private String fiveDigitPin;
    private String nameOfUser;
    private String description;
    private String logcd;
    private String gcssusrname;
    private String accgrpid1;
    private String accgrpid2;
    private String accgrpid3;
    private String accgrpid4;

    public AddPerson() {
        this.logcd = "Y";
    }

    public void resetValues() {
        lastname = null;
        firstname = null;
        middlename = null;
        email = null;
        rankid = null;
        homephone = null;
        workphone = null;
        fiveDigitPin = null;
        nameOfUser = null;
        description = null;
        logcd = "Y";
        gcssusrname = null;
        accgrpid1 = null;
        accgrpid2 = null;
        accgrpid3 = null;
        accgrpid4 = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middlename;
    }

    public AccessGroup[] getItems() {
        AccessGroup[] result = new AccessGroup[4];
        result[0] = new AccessGroup("yellow", "0");
        result[1] = new AccessGroup("green", "1");
        result[2] = new AccessGroup("red", "2");
        result[3] = new AccessGroup("blue", "3");
        return result;
    }

    public void setMiddleName(String middlename) {
        this.middlename = middlename;
    }

    public String getRankId() {
        return rankid;
    }

    public void setRankId(String rankid) {
        this.rankid = rankid;
    }

    public String getHomePhone() {
        return homephone;
    }

    public void setHomePhone(String homephone) {
        this.homephone = homephone;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public void setWorkPhone(String workphone) {
        this.workphone = workphone;
    }

    public String getFiveDigitPin() {
        return fiveDigitPin;
    }

    public void setFiveDigitPin(String fiveDigitPin) {
        this.fiveDigitPin = fiveDigitPin;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogCd() {
        return logcd;
    }

    public void setLogCd(String logcd) {
        this.logcd = logcd;
    }

    public String getGcssUsrName() {
        return gcssusrname;
    }

    public void setGcssUsrName(String gcssusrname) {
        this.gcssusrname = gcssusrname;
    }

    public String getAccGrpId1() {
        return accgrpid1;
    }

    public void setAccGrpId1(String accgrpid1) {
        this.accgrpid1 = accgrpid1;
    }

    public String getAccGrpId2() {
        return accgrpid2;
    }

    public void setAccGrpId2(String accgrpid2) {
        this.accgrpid2 = accgrpid2;
    }

    public String getAccGrpId3() {
        return accgrpid3;
    }

    public void setAccGrpId3(String accgrpid3) {
        this.accgrpid3 = accgrpid3;
    }

    public String getAccGrpId4() {
        return accgrpid4;
    }

    public void setAccGrpId4(String accgrpid4) {
        this.accgrpid4 = accgrpid4;
    }

//    public void create() {
// 
//        try{
//            if (getAccGrpId2()==null)setAccGrpId2("0");
//            if (getAccGrpId3()==null)setAccGrpId3("0");
//            if (getAccGrpId4()==null)setAccGrpId4("0");
//            pfaEJBean.insert("sbt_esc_fast_add", sysId, userName, StringFixes.UNKNOWN, new Date(), getLastName(), getMiddleName(), 
//                    getFirstName(), getRankId(), getHomePhone(), getWorkPhone(), getFiveDigitPin(), getNameOfUser(), getDescription(), 
//                    getLogCd(), getAccGrpId1(), getAccGrpId2(), getAccGrpId3(), getAccGrpId4(), getEmail());
//            
//        }catch(SSDAException ex){
//            LOGGER.debug(StringFixes.SSDA_EXCEPTION);
//            FacesContext.getCurrentInstance().addMessage("pfaForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(),ex.getMessage()));
//            FacesContext.getCurrentInstance().validationFailed();
//        }   
//    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//    public List<AccGrp> getItems() {
//        return pfaEJBean.getData(StringFixes.storedProc.SST_ESC_ACCGRP.getProcName(), sysId, userName, StringFixes.UNKNOWN, 0);
//    }
    public void reset() {
        resetValues();
    }
}

package org.primefaces.test;

public class AccessGroup {
    private String accGrpNm;
    private String accGrpId;

    public AccessGroup(String name, String id) {
        accGrpId = id;
        accGrpNm = name;
    }
    
    public String getAccGrpNm() {
        return accGrpNm;
    }

    public void setAccGrpNm(String accGrpNm) {
        this.accGrpNm = accGrpNm;
    }

    public String getAccGrpId() {
        return accGrpId;
    }

    public void setAccGrpId(String accGrpId) {
        this.accGrpId = accGrpId;
    }
    
}

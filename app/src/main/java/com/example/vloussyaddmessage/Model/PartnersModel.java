package com.example.vloussyaddmessage.Model;

public class PartnersModel {
    public String idPartners;
    public String namePartners;
    public String numPartners;
    public String userPartners;
    public String conditionPartners;
    public String partnersCoin;
    public String partnersCoinIndex;

    public PartnersModel() {
    }

    public String getPartnersCoinIndex() {
        return partnersCoinIndex;
    }

    public void setPartnersCoinIndex(String partnersCoinIndex) {
        this.partnersCoinIndex = partnersCoinIndex;
    }

    public PartnersModel(String idPartners, String namePartners, String numPartners, String userPartners, String conditionPartners, String partnersCoin, String partnersCoinIndex ) {
        this.idPartners = idPartners;
        this.namePartners = namePartners;
        this.numPartners = numPartners;
        this.userPartners = userPartners;
        this.conditionPartners = conditionPartners;
        this.partnersCoin = partnersCoin;
        this.partnersCoinIndex = partnersCoinIndex;
    }

    public String getIdPartners() {
        return idPartners;
    }

    public void setIdPartners(String idPartners) {
        this.idPartners = idPartners;
    }

    public String getNamePartners() {
        return namePartners;
    }

    public void setNamePartners(String namePartners) {
        this.namePartners = namePartners;
    }

    public String getNumPartners() {
        return numPartners;
    }

    public void setNumPartners(String numPartners) {
        this.numPartners = numPartners;
    }

    public String getUserPartners() {
        return userPartners;
    }

    public void setUserPartners(String userPartners) {
        this.userPartners = userPartners;
    }

    public String getConditionPartners() {
        return conditionPartners;
    }

    public void setConditionPartners(String conditionPartners) {
        this.conditionPartners = conditionPartners;
    }

    public String getPartnersCoin() {
        return partnersCoin;
    }

    public void setPartnersCoin(String partnersCoin) {
        this.partnersCoin = partnersCoin;
    }
}

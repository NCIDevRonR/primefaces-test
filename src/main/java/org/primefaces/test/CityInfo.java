/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.test;

public class CityInfo {
    private int id;
    private String zipCode;
    private String cityName;
    private String stateName;
    private String stateAbbrev;
    private String county;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CityInfo() {

    }
    
    
    public CityInfo(int id, String zipCode, String cityName, String stateName, String stateAbbrev, String county) {
        this.id = id;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.stateName = stateName;
        this.stateAbbrev = stateAbbrev;
        this.county = county;
    }
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
    
    
    
}

package org.primefaces.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@Named("cityInfoController")
@SessionScoped
public class CityInfoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CityInfo> items = null;
    private CityInfo selected;

    public CityInfoController() {
        //Default constructor is intentionally empty.
    }

    public CityInfo getSelected() {
        return selected;
    }

    public void setSelected(CityInfo selected) {
        this.selected = selected;
    }

    public void clearItems() {
        items = null;
    }

    public List<CityInfo> getItems() {
//        System.out.println("Starting getItems");
        if ((items == null) || (!FacesContext.getCurrentInstance().isPostback())) {
            items = getCityInfo();
        }
        return items;
    }

    public void doNothing() {
        
    }


    private List<CityInfo> getCityInfo() {
        List<CityInfo> listOfCities = new ArrayList<>();

        try {

            List<String> listOfStrings = readInFile("/resources/US.txt");
            int firstFewCities = 0;
            int eachId = 0;
            for (String eachLine : listOfStrings) {
                eachId++;
                String fields[] = eachLine.split("\t");
                String zipCode = fields[1];
                String cityName = fields[2];
                String stateName = fields[3];
                String stateAbbrev = fields[4];
                String county = fields[5];
                if (!stateName.isEmpty()) {
                    CityInfo cityInfo = new CityInfo(eachId, zipCode, cityName, stateName, stateAbbrev, county);
                    listOfCities.add(cityInfo);
                    if (firstFewCities < 300) {
//                        System.out.println(cityInfo.getCityName() + ",  " + cityInfo.getStateName() + ",  "
//                                + cityInfo.getStateAbbrev() + ",  " + cityInfo.getCounty() + ",  " + cityInfo.getZipCode());
                        firstFewCities++;
                    }
                }
            }
            System.out.println(listOfCities.size() + " cities.");
            return listOfCities;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //Get city info from a HUGE text file.
        return listOfCities;
    }
    
   
    private List<String> readInFile(String filePath) throws IOException {
        // list that holds strings of a file
        List<String> listOfStrings
                = new ArrayList<>();

        InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filePath);
        // load data from file
        BufferedReader bf = new BufferedReader(
                new InputStreamReader(is));

        // read entire line as string
        String line = bf.readLine();

        // checking for end of file
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        // closing bufferreader object
        bf.close();

        return listOfStrings;
    }

}

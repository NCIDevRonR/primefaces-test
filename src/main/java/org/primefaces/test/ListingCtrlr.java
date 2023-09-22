package org.primefaces.test;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

//Added for viewing PDF Files.
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;

// "Scoped annotations, and the behavior I'm seeing with each.
//import javax.faces.bean.RequestScoped;      //Apparently deprecated.  Doesn't allow user to select a listing to display.
//import javax.faces.bean.SessionScoped;      //Apparently deprecated.  Doesn't allow user to select a listing to display.
import javax.enterprise.context.RequestScoped; //The viewer dialog appears, with no report PDF displayed.
import javax.enterprise.context.SessionScoped; //Only the first requested report keeps appearing i the viewer.

@Named("listingCtrlr")
@SessionScoped
public class ListingCtrlr implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String SELECT_LISTING = "selectListing";

    private static final String FILE_SEPARATOR = File.separator;
    private static final String LISTING_WORK_PATH = "C:" + FILE_SEPARATOR + "pdf_test" + FILE_SEPARATOR;
    private static final String LISTING_TEMPLATES_PATH = LISTING_WORK_PATH + "templates" + FILE_SEPARATOR;
    private static final String LISTING_OUTPUT_PATH = LISTING_WORK_PATH + "output" + FILE_SEPARATOR;
    private static final String FILENAME_MONTY = "Monticello";
    private static final String FILENAME_MOUNT = "MountVernon";
    private static final String FILENAME_DELTA = "DeltaHouse";
    private static String PDF_TemplatePath = ""; // Path including filename.
    private static String PDF_OutputPath = ""; // Path including filename, with timestamp.
    private StreamedContent streamedContent; // Added for streaming PDF files.
    private boolean listingGenerated = false;
    private String selectedListing = null;

    public boolean getListingGenerated() {
        return listingGenerated;
    }

    public String getListingPath() {
        return LISTING_WORK_PATH;
    }

    public void btnDisplay(ActionEvent actionEvent) throws IOException {
        // Displays the selected house listing.

        String whichListing = getSelectedListing();
        System.out.println("Selected " + whichListing + ".");
        Map<String, String> parms = new HashMap<>();
        if (whichListing != null) {
            parms.put("selListing", whichListing);
        }

        listingSequence(parms);
    }

    private void listingSequence(Map<String, String> parms) throws IOException {
        runReport(parms);
    }

    private void runReport(Map<String, String> parms) throws IOException {
        streamedContent = null;

        listingGenerated = execReport(parms);
        System.out.println("Listing generated?  " + listingGenerated);
        if (listingGenerated) {
            createStream(PDF_OutputPath);
            // I have this poll object call in the app, and it seems to be still working to
            // display the dialog.
            // It didn't work for this demo.
            // PrimeFaces.current().executeScript("PF('poll').start();");
            addMessage(SELECT_LISTING, "House Listing is created!");
            //I added the following two lines to get the viewer dialog to appear.
//            PrimeFaces current = PrimeFaces.current();
//            current.executeScript("PF('listingViewer').show();");            
        }
    }

    // The poll object calls this to stop itself after running once amd opening the
    // dialog.
    // Removed it in this demo since the two lines I added open the dialog.
    // public void stopPoll() {
    // PrimeFaces.current().executeScript("PF('poll').stop();");
    // }

    public void addMessage(String fieldName, String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(fieldName, message);
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        return timestamp;
    }

    public boolean execReport(Map<String, String> parms) throws IOException {
        boolean result = true;
//        if (selectedListing == null) {
//            return false;
//        }

        String timeStamp = getCurrentTimestamp();

        //Copy selected listing template file to output folder with the current timestamp.
        if (selectedListing.matches("listingMonticello")) {
            PDF_TemplatePath = LISTING_TEMPLATES_PATH + FILENAME_MONTY + ".pdf";
            PDF_OutputPath = LISTING_OUTPUT_PATH + FILENAME_MONTY + timeStamp + ".pdf";
        }
        else if (selectedListing.matches("listingMtVernon")) {
            PDF_TemplatePath = LISTING_TEMPLATES_PATH + FILENAME_MOUNT + ".pdf";
            PDF_OutputPath = LISTING_OUTPUT_PATH + FILENAME_MOUNT + timeStamp + ".pdf";
        }
        else if (selectedListing.matches("listingDelta")) {
            PDF_TemplatePath = LISTING_TEMPLATES_PATH + FILENAME_DELTA + ".pdf";
            PDF_OutputPath = LISTING_OUTPUT_PATH + FILENAME_DELTA + timeStamp + ".pdf";
        }

        File templateFile = new File(PDF_TemplatePath);

        File outputFile = new File(PDF_OutputPath);

        try {
            if (templateFile.exists()) {
                copyFileUsingStreams(templateFile, outputFile);
            }
        } catch (IOException e) {
            result = false;
        }

        return result;
    }

    private static void copyFileUsingStreams(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public String getSelectedListing() {
        return selectedListing;
    }

    public void setSelectedListing(String selListing) {
        selectedListing = selListing;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public String getFilePath() {
        return PDF_OutputPath;
    }

    private StreamedContent createStream(String filePath) throws IOException {
        String simpleFilename = filePath.substring(filePath.lastIndexOf(FILE_SEPARATOR) + 1);
        streamedContent = getFileSystemPDF(filePath, simpleFilename);
        return streamedContent;
    }

    private StreamedContent getFileSystemPDF(String filePath, String simpleFilename) throws IOException {
        StreamedContent result = null;
        try {
            result = DefaultStreamedContent.builder()
                    .stream(() -> {
                        try {
                            return new FileInputStream(filePath);
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .contentType("application/pdf")
                    .name(simpleFilename)
                    .build();
            return result;

        } catch (final Exception e) {
            return result;
        }

    }

    // refill the stream
    public void refreshStream() throws IOException {
        createStream(PDF_OutputPath);
    }

    public String generateRandomIdForNotCaching() {
        return java.util.UUID.randomUUID().toString();
    }

}

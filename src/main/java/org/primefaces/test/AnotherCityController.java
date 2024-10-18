package org.primefaces.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

@Named("anotherCityController")
@SessionScoped
public class AnotherCityController extends CityInfoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CityInfo> items = null;
    private List<CityInfo> selectedRows = new ArrayList<>();
//    private CityInfo selectedItem;

    public AnotherCityController() {
        //Default constructor is intentionally empty.
    }

    @PostConstruct
    public void init() {

    }

    public void onRowSelect(SelectEvent<CityInfo> event) {
        selectedRows.clear(); // Clear previous selections
        selectedItem = event.getObject();
        selectedRows.add(selectedItem); // Add the selected row to the list
    }

    public void onRowUnselect(SelectEvent<CityInfo> event) {
        selectedRows.clear(); // Clear previous selections
        selectedItem = null;
    }

    public boolean hasSelectedItem() {
        return this.selectedItem != null;
    }

    public CityInfo getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(CityInfo selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<CityInfo> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(List<CityInfo> selectedRows) {
        this.selectedRows = selectedRows;
    }

    public void prepareCreate() {
        selectedItem = new CityInfo();
//        selectedRows.add(selectedItem);
    }

    public void prepareEdit() {

    }

    public void prepareDelete() {

    }

//    public void clearItems() {
//        items = null;
//    }
    public void clearSelectedRows() {
        this.selectedItem = null;
        if (this.selectedRows != null) {
            this.selectedRows.clear();
        }
    }

    public void onEditDialogClose() {
        if (selectedItem != null && !this.selectedRows.contains(selectedItem)) {
            this.selectedRows.add(selectedItem);
        }
    }

    public void saveEdit() {
        // Save logic here
        update();
        this.clearItems();
        keepSelected(selectedRows, selectedItem, "CityInfoListForm:datalist");
    }

    public void cancelEdit() {
        // Cancel logic here
        keepSelected(selectedRows, selectedItem, "CityInfoListForm:datalist");
    }

    public void handleCloseEdit(CloseEvent event) {
        keepSelected(selectedRows, selectedItem, "CityInfoListForm:datalist");
    }

    public void create() {

        save();
        this.selectedRows.clear();

        items = null;// Invalidate list of items to trigger re-query.

    }

    public void update() {

        save();

        items = null;// Invalidate list of items to trigger re-query.

    }

    @Override
    public void destroy() {
        super.destroy();

        clearSelectedRows();    // Remove selection

        items = null;// Invalidate list of items to trigger re-query.

    }

    protected void keepSelected(List<CityInfo> selectedRows, CityInfo selectedItem, String formAndDataTable) {
        if (selectedItem != null && !selectedRows.contains(selectedItem)) {
            selectedRows.clear();
            selectedRows.add(selectedItem);
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(formAndDataTable);

    }

    
}

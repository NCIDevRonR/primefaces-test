/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.test;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginBean {
    private final String INDEX_URL = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/mainpage.xhtml";

    public void login() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String subjectDN = request.getHeader("MYAPP_X-CertSubj");
        System.out.println("subjectDN = " + subjectDN);
        if (subjectDN == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error", "SubjectDN is null. Please try again."));
            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Welcome! " + subjectDN));
        FacesContext.getCurrentInstance().getExternalContext().redirect(INDEX_URL);
    }
}

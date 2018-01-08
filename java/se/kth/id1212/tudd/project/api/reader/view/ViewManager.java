/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.tudd.project.api.reader.view;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import se.kth.id1212.tudd.project.api.reader.controller.ReaderFacade;

@Named("viewManager")
@RequestScoped
public class ViewManager {

    private String result;

    @EJB
    private ReaderFacade controller;

    public void gameTime() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String timeScale = request.getParameter("form:timeScale");
            String account_id = new String(request.getParameter("form:account_id"));
            result = controller.gameTime(account_id, timeScale);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }
    
    public String[] getTimes(){
        return controller.timeScales;
    }

    public String getResult() {
        return result;
    }

}

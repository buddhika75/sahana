/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divudi.bean.lab;

import com.divudi.entity.lab.Investigation;
import com.divudi.entity.lab.InvestigationValidator;
import com.divudi.entity.lab.InvestigationValidaterComponent;
import com.divudi.facade.InvestigationItemValidatorFacade;
import com.divudi.facade.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author pasan
 */
@Named
@SessionScoped
public class InvestigationValidatorComponentController implements Serializable {

    /**
     * Creates a new instance of InvestigationValueComponentController
     */
    public InvestigationValidatorComponentController() {
    }

    Investigation currentInvestigation;
    InvestigationValidaterComponent current;
    InvestigationValidator currentValidator;
    private List<InvestigationValidator> investigationItemValidators;
    @EJB
    private InvestigationItemValidatorFacade investigationItemValidatorFacade;
    private String newValidatorName;

    
    

    public void addNewValidator() {
        if(currentInvestigation==null){
            JsfUtil.addErrorMessage("Select an investigation");
            return;
        }
        currentValidator = new InvestigationValidator();
        currentValidator.setName(newValidatorName);
        currentValidator.setItem(currentInvestigation);
        getInvestigationItemValidatorFacade().create(currentValidator);
        listItemValidator();
        setNewValidatorName(null);
    }

    public void listItemValidator() {
        investigationItemValidators = new ArrayList<>();
        String sql;
        sql = "select i from InvestigationValidator i where "
                + " i.retired=false ";
        investigationItemValidators = getInvestigationItemValidatorFacade().findBySQL(sql);
    }

    public void setCurrentInvestigation(Investigation currentInvestigation) {
        this.currentInvestigation = currentInvestigation;
    }

    
    
    public Investigation getCurrentInvestigation() {
        return currentInvestigation;
    }

    public InvestigationValidaterComponent getCurrent() {
        return current;
    }

    public void setCurrent(InvestigationValidaterComponent current) {
        this.current = current;
    }

    public List<InvestigationValidator> getInvestigationItemValidators() {
        return investigationItemValidators;
    }

    public void setInvestigationItemValidators(List<InvestigationValidator> investigationItemValidators) {
        this.investigationItemValidators = investigationItemValidators;
    }

    public InvestigationItemValidatorFacade getInvestigationItemValidatorFacade() {
        return investigationItemValidatorFacade;
    }

    public void setInvestigationItemValidatorFacade(InvestigationItemValidatorFacade investigationItemValidatorFacade) {
        this.investigationItemValidatorFacade = investigationItemValidatorFacade;
    }

    public String getNewValidatorName() {
        return newValidatorName;
    }

    public void setNewValidatorName(String newValidatorName) {
        this.newValidatorName = newValidatorName;
    }

    public InvestigationValidator getCurrentValidator() {
        return currentValidator;
    }

    public void setCurrentValidator(InvestigationValidator currentValidator) {
        this.currentValidator = currentValidator;
    }

    
    
    
    
    
}
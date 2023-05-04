/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Kazi
 */
public class MedRestock implements Serializable{
    private static final long serialVersionUID = 13L;
    
    private String medName;
    private int Quantity;
    private boolean ordered;

    public MedRestock(String medName, int Quantity, boolean ordered) {
        this.medName = medName;
        this.Quantity = Quantity;
        this.ordered = ordered;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
    
    
}

package com.vikram.recyclerviewwithcheckboxupdating;

import java.io.Serializable;

/**
 * Created by vikram on 22/01/16.
 */
public class Units implements Serializable {

    private static final long serialVersionUID = 1L;
    private String StrUnitName;
    private int NoOfquestions;
    private boolean isSelected;

    public Units() {
    }

    public Units(String strUnitName, int noOfquestions, boolean isSelected) {
        StrUnitName = strUnitName;
        NoOfquestions = noOfquestions;
        this.isSelected = isSelected;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStrUnitName() {
        return StrUnitName;
    }

    public void setStrUnitName(String strUnitName) {
        StrUnitName = strUnitName;
    }

    public int getNoOfquestions() {
        return NoOfquestions;
    }

    public void setNoOfquestions(int noOfquestions) {
        NoOfquestions = noOfquestions;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

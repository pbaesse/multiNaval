package main.iface;

import javax.swing.JPanel;

public class Cell {
    private JPanel button;
    private boolean isClicked;
    private boolean isShotted;
    private String cellName;

    public Cell() { 
        button = new JPanel();
        isClicked = false;
        isShotted = false;
    }

    public void setCellName(String name) {
        this.cellName = name;
    }
    
    public String getCellName() {
        return cellName;
    }
    
    public JPanel getButton() {
        return button;
    }

    public void setButton(JPanel button) {
        this.button = button;
    }
    
    public Boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }
    
    public Boolean isShotted() {
        return isShotted;
    }
    public void setShotted(boolean isShotted) {
        this.isShotted = isShotted;
    }
}
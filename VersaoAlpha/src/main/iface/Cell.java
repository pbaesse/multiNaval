package main.iface;

import javax.swing.JButton;

public class Cell {
    private JButton button = new JButton();
    private Boolean booleano = false, booleanTiro = false;

    public Cell() { 
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
    
    public Boolean getBooleano() {
        return booleano;
    }

    public void setBooleano(Boolean booleano) {
        this.booleano = booleano;
    }
    
    public Boolean getBooleanTiro() {
        return booleanTiro;
    }
    public void setBooleanTiro(Boolean booleanTiro) {
        this.booleanTiro = booleanTiro;
    }
}

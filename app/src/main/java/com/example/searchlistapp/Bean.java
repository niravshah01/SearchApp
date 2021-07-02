package com.example.searchlistapp;

public class Bean {
    String decimal,binary,hexa;

    public Bean(String decimal,String binary, String hexa){
        this.decimal = decimal;
        this.binary = binary;
        this.hexa = hexa;
    }

    public String getDecimal(){
        return decimal;
    }

    public void setDecimal(String decimal){
        this.decimal=decimal;
    }

    public String getBinary(){
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    public String getHexa() {
        return hexa;
    }

    public void setHexa(String hexa) {
        this.hexa = hexa;
    }
}

package com.cqliving.cloud.online.question.dto;

import javax.xml.bind.annotation.XmlElement;

public class Coord {
    String coordX;
    String coordY;
    
    @XmlElement(name = "CoordX")  
    public String getCoordX() {
        return coordX;
    }
    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }
    @XmlElement(name = "CoordY")  
    public String getCoordY() {
        return coordY;
    }
    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }
    
}

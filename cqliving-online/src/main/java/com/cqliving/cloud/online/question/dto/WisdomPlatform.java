package com.cqliving.cloud.online.question.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "wisdomPlatform")
public class WisdomPlatform {
    String reportnum;
    String recSource;
    String eventtypeid;
    String maintypeid;
    String subtypeid;
    String recDesc;
    String address;
    Coord coord;
    String reporterName;
    String reporterMobile;
    Medias medias;
    
    @XmlElement(name = "Reportnum")  
    public String getReportnum() {
        return reportnum;
    }
    public void setReportnum(String reportnum) {
        this.reportnum = reportnum;
    }
    
    @XmlElement(name = "RecSource")  
    public String getRecSource() {
        return recSource;
    }
    public void setRecSource(String recSource) {
        this.recSource = recSource;
    }
    
    @XmlElement(name = "Eventtypeid")  
    public String getEventtypeid() {
        return eventtypeid;
    }
    public void setEventtypeid(String eventtypeid) {
        this.eventtypeid = eventtypeid;
    }
    
    @XmlElement(name = "Maintypeid")  
    public String getMaintypeid() {
        return maintypeid;
    }
    public void setMaintypeid(String maintypeid) {
        this.maintypeid = maintypeid;
    }
    
    @XmlElement(name = "Subtypeid")  
    public String getSubtypeid() {
        return subtypeid;
    }
    public void setSubtypeid(String subtypeid) {
        this.subtypeid = subtypeid;
    }
    
    @XmlElement(name = "RecDesc")  
    public String getRecDesc() {
        return recDesc;
    }
    public void setRecDesc(String recDesc) {
        this.recDesc = recDesc;
    }
    
    @XmlElement(name = "Address")  
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    @XmlElement(name = "Coord")  
    public Coord getCoord() {
        return coord;
    }
    public void setCoord(Coord coord) {
        this.coord = coord;
    }
    
    @XmlElement(name = "ReporterName")  
    public String getReporterName() {
        return reporterName;
    }
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
    
    @XmlElement(name = "ReporterMobile")
    public String getReporterMobile() {
        return reporterMobile;
    }
    public void setReporterMobile(String reporterMobile) {
        this.reporterMobile = reporterMobile;
    }
    
    @XmlElement(name = "medias")
    public Medias getMedias() {
        return medias;
    }
    public void setMedias(Medias medias) {
        this.medias = medias;
    }
}
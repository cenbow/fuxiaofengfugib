package com.cqliving.cloud.online.question.dto;

import javax.xml.bind.annotation.XmlElement;

public class Medias {
    String mediaType;
    String mediaName;
    String mediaPath;
    
    @XmlElement(name = "MediaType")
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    @XmlElement(name = "MediaName")
    public String getMediaName() {
        return mediaName;
    }
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
    
    @XmlElement(name = "MediaPath")
    public String getMediaPath() {
        return mediaPath;
    }
    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
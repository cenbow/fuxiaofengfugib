
package com.ysmall.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.9
 * 2016-03-29T10:29:55.333+08:00
 * Generated source version: 2.5.9
 */

@WebFault(name = "SQLException", targetNamespace = "http://service.ysmall.com/")
public class SQLException_Exception extends java.lang.Exception {
    
    private com.ysmall.service.SQLException sqlException;

    public SQLException_Exception() {
        super();
    }
    
    public SQLException_Exception(String message) {
        super(message);
    }
    
    public SQLException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLException_Exception(String message, com.ysmall.service.SQLException sqlException) {
        super(message);
        this.sqlException = sqlException;
    }

    public SQLException_Exception(String message, com.ysmall.service.SQLException sqlException, Throwable cause) {
        super(message, cause);
        this.sqlException = sqlException;
    }

    public com.ysmall.service.SQLException getFaultInfo() {
        return this.sqlException;
    }
}

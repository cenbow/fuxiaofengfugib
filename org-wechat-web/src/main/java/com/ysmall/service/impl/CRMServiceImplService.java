package com.ysmall.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import com.ysmall.service.CRMService;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.9
 * 2016-03-29T10:29:55.386+08:00
 * Generated source version: 2.5.9
 * 
 */
@WebServiceClient(name = "CRMServiceImplService", 
                  wsdlLocation = "http://114.255.45.138:8888/JinYuanCRMInterface/CRMService?wsdl",
                  targetNamespace = "http://impl.service.ysmall.com/") 
public class CRMServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.service.ysmall.com/", "CRMServiceImplService");
    public final static QName CRMServiceImplPort = new QName("http://impl.service.ysmall.com/", "CRMServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://114.255.45.138:8888/JinYuanCRMInterface/CRMService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CRMServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://114.255.45.138:8888/JinYuanCRMInterface/CRMService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CRMServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CRMServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CRMServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CRMServiceImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CRMServiceImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CRMServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns CRMService
     */
    @WebEndpoint(name = "CRMServiceImplPort")
    public CRMService getCRMServiceImplPort() {
        return super.getPort(CRMServiceImplPort, CRMService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CRMService
     */
    @WebEndpoint(name = "CRMServiceImplPort")
    public CRMService getCRMServiceImplPort(WebServiceFeature... features) {
        return super.getPort(CRMServiceImplPort, CRMService.class, features);
    }

}

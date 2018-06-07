/**
 * MainActionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nci.dcs.webservices.dxpt.wsdl;

public class MainActionServiceLocator extends org.apache.axis.client.Service implements com.nci.dcs.webservices.dxpt.wsdl.MainActionService {

    public MainActionServiceLocator() {
    }


    public MainActionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MainActionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsdlTest
    private java.lang.String wsdlTest_address = "http://10.204.0.9:8080/dxpt/services/wsdlTest";

    public java.lang.String getwsdlTestAddress() {
        return wsdlTest_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsdlTestWSDDServiceName = "wsdlTest";

    public java.lang.String getwsdlTestWSDDServiceName() {
        return wsdlTestWSDDServiceName;
    }

    public void setwsdlTestWSDDServiceName(java.lang.String name) {
        wsdlTestWSDDServiceName = name;
    }

    public com.nci.dcs.webservices.dxpt.wsdl.MainAction getwsdlTest() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsdlTest_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsdlTest(endpoint);
    }

    public com.nci.dcs.webservices.dxpt.wsdl.MainAction getwsdlTest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nci.dcs.webservices.dxpt.wsdl.WsdlTestSoapBindingStub _stub = new com.nci.dcs.webservices.dxpt.wsdl.WsdlTestSoapBindingStub(portAddress, this);
            _stub.setPortName(getwsdlTestWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsdlTestEndpointAddress(java.lang.String address) {
        wsdlTest_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nci.dcs.webservices.dxpt.wsdl.MainAction.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nci.dcs.webservices.dxpt.wsdl.WsdlTestSoapBindingStub _stub = new com.nci.dcs.webservices.dxpt.wsdl.WsdlTestSoapBindingStub(new java.net.URL(wsdlTest_address), this);
                _stub.setPortName(getwsdlTestWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsdlTest".equals(inputPortName)) {
            return getwsdlTest();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.204.0.9:8080/dxpt/services/wsdlTest", "MainActionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.204.0.9:8080/dxpt/services/wsdlTest", "wsdlTest"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsdlTest".equals(portName)) {
            setwsdlTestEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

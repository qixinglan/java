package com.nci.dcs.webservices.dxpt.wsdl;

public class MainActionProxy implements com.nci.dcs.webservices.dxpt.wsdl.MainAction {
  private String _endpoint = null;
  private com.nci.dcs.webservices.dxpt.wsdl.MainAction mainAction = null;
  
  public MainActionProxy() {
    _initMainActionProxy();
  }
  
  public MainActionProxy(String endpoint) {
    _endpoint = endpoint;
    _initMainActionProxy();
  }
  
  private void _initMainActionProxy() {
    try {
      mainAction = (new com.nci.dcs.webservices.dxpt.wsdl.MainActionServiceLocator()).getwsdlTest();
      if (mainAction != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mainAction)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mainAction)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mainAction != null)
      ((javax.xml.rpc.Stub)mainAction)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.nci.dcs.webservices.dxpt.wsdl.MainAction getMainAction() {
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    mainAction.main(args);
  }
  
  public java.lang.Object getConnection() throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction.getConnection();
  }
  
  public java.lang.String[][] getMessageList(java.lang.String phoneNumber, java.lang.String content, java.lang.String requestid, java.lang.String sendpersonid, java.lang.String isDingShi, java.lang.String startDate, java.lang.String endDate, int pageNum, int pageSize) throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction.getMessageList(phoneNumber, content, requestid, sendpersonid, isDingShi, startDate, endDate, pageNum, pageSize);
  }
  
  public java.lang.String sendMessage(java.lang.String phoneNumber, java.lang.String receivePerson, java.lang.String content, java.lang.String requestid, java.lang.String isDingShi, java.lang.String sendDate, java.lang.String sendPerson, java.lang.String sendpersonid) throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction.sendMessage(phoneNumber, receivePerson, content, requestid, isDingShi, sendDate, sendPerson, sendpersonid);
  }
  
  public java.lang.String updateMessage(java.lang.String id, java.lang.String phoneNumber, java.lang.String content, java.lang.String sendtime, java.lang.String status, java.lang.String requestid, java.lang.String sendpersonid, java.lang.String sendPerson) throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction.updateMessage(id, phoneNumber, content, sendtime, status, requestid, sendpersonid, sendPerson);
  }
  
  public java.lang.String zhibanSendMessage(java.lang.String phoneNumber, java.lang.String requestid, java.lang.String sendDate, java.lang.String sendPerson, java.lang.String sendpersonid, java.lang.String dutyType) throws java.rmi.RemoteException{
    if (mainAction == null)
      _initMainActionProxy();
    return mainAction.zhibanSendMessage(phoneNumber, requestid, sendDate, sendPerson, sendpersonid, dutyType);
  }
  
  
}
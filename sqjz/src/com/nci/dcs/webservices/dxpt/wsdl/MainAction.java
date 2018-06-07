/**
 * MainAction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nci.dcs.webservices.dxpt.wsdl;

public interface MainAction extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public java.lang.Object getConnection() throws java.rmi.RemoteException;
    public java.lang.String[][] getMessageList(java.lang.String phoneNumber, java.lang.String content, java.lang.String requestid, java.lang.String sendpersonid, java.lang.String isDingShi, java.lang.String startDate, java.lang.String endDate, int pageNum, int pageSize) throws java.rmi.RemoteException;
    public java.lang.String sendMessage(java.lang.String phoneNumber, java.lang.String receivePerson, java.lang.String content, java.lang.String requestid, java.lang.String isDingShi, java.lang.String sendDate, java.lang.String sendPerson, java.lang.String sendpersonid) throws java.rmi.RemoteException;
    public java.lang.String updateMessage(java.lang.String id, java.lang.String phoneNumber, java.lang.String content, java.lang.String sendtime, java.lang.String status, java.lang.String requestid, java.lang.String sendpersonid, java.lang.String sendPerson) throws java.rmi.RemoteException;
    public java.lang.String zhibanSendMessage(java.lang.String phoneNumber, java.lang.String requestid, java.lang.String sendDate, java.lang.String sendPerson, java.lang.String sendpersonid, java.lang.String dutyType) throws java.rmi.RemoteException;
}

package com.supra.common;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.WebFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.stereotype.Component;

import com.ctc.wstx.util.ExceptionUtil;



@Component
@WebFault
public class SoapHandler implements SOAPHandler<SOAPMessageContext> {
	
	
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }
    
    public boolean handleMessage(SOAPMessageContext context) {
        
        
        
        return true;
    }
    
    public boolean handleFault(SOAPMessageContext context) {
    	
    	
    	SOAPMessage soapMessage =  context.getMessage();
    	SOAPPart soapPart = soapMessage.getSOAPPart();
    	SOAPEnvelope soapEnvelope;
    	
		try {
			
			soapEnvelope = soapPart.getEnvelope();
			SOAPBody soapBody = soapEnvelope.getBody();
	    	SOAPFault soapFault = soapBody.getFault();
	    	
	    	String faultCode = "0";
	    	String faultMessage = "Server Error";
	    	
	    	if(soapFault != null){
		    	
		    	String faultMsg = soapFault.getFaultString();
		    	
		    	if(faultMsg != null && !faultMsg.isEmpty()){
		    		
		    		String faultMsgs[] = faultMsg.split("-");
	    			System.out.println("SOAP FAULT : "+soapFault.getFaultString());
	    			faultCode = "0";
	    			faultMessage = "Server Error";
		    	}
	    	}
	    	
	    	soapFault.setFaultCode(faultCode);
	    	soapFault.setFaultString(faultMessage);
	    	
		} catch (Exception e) {
			System.out.println("SOAPException : "+ e);
			e.printStackTrace();
		}
    	
        return true;
    }

    public void close(MessageContext context) {
    }
}
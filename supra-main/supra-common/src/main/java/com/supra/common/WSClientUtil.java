package com.supra.common;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WSClientUtil {
	
	/*public SOAPEnvelope includeDatNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("dat","http://ws.wso2.org/dataservice");
		
		return envelope;
	}
	
	public SOAPEnvelope includeTemNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("tem","http://tempuri.org");
		
		return envelope;
	}
	
	public SOAPEnvelope includeEntNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ent","http://schemas.microsoft.com/dynamics/2006/02/documents/EntityKeyList");
		
		return envelope;
	}
	
	public SOAPEnvelope includeEnt1Namespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ent1","http://schemas.microsoft.com/dynamics/2006/02/documents/EntityKey");
		
		return envelope;
	}
	
	public SOAPEnvelope includePaymentConfirmNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ae","http://ae.gov.trf.ffu.inq.ws.PaymentConfirmation");
		
		return envelope;
	}
	
	public SOAPEnvelope includeCreateTransNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ws","http://ws.trs.rta.ae");
		
		return envelope;
	}
	
	public SOAPEnvelope includeLockTransNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ae","http://ae.rta.trs.LockTransactionForPaymentService");
		
		return envelope;
	}
	
	public SOAPEnvelope includeGeneralInquiryNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("gi","http://gi.client.ws.common.esrv.rta.ae");
		
		return envelope;
	}
	
	public SOAPEnvelope includePersonInfoNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("ae","http://ae.gov.trf.inq.ws.PersonDetailsService");
		
		return envelope;
	}
	
	public SOAPEnvelope includeESBAuthHeader(SOAPEnvelope envelope, String username, String password) throws Exception{
        DateFormat utcFormatSSS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.ENGLISH);
       
        SOAPHeader soapHeader = envelope.getHeader();
        SOAPElement securityElem = soapHeader.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        securityElem.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        securityElem.addChildElement("Timestamp", "wsu");
        SOAPElement usernameToken = securityElem.addChildElement("UsernameToken", "wsse");
        usernameToken.addChildElement("Username", "wsse").addTextNode(username);
       
        SOAPElement passwordElem = usernameToken.addChildElement("Password", "wsse");
        passwordElem.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        passwordElem.addTextNode(password);
        usernameToken.addChildElement("Created", "wsu").addTextNode(utcFormatSSS.format(new Date()));       
        
        return envelope;
    }
	
	public SOAPEnvelope includeTrafficAuthHeader(SOAPEnvelope envelope, String username, String password) throws Exception{
       
        SOAPHeader soapHeader = envelope.getHeader();
        
        soapHeader.addHeaderElement(envelope.createName("username")).addTextNode(username);
        soapHeader.addHeaderElement(envelope.createName("password")).addTextNode(password);
        
        return envelope;
    }
	
	public String postXML(String requestXML, String endPoint, String soapAction,String timeout) throws Exception{
        HttpPost httppost = new HttpPost(endPoint);
        StringEntity stringentity = new StringEntity(requestXML,"UTF-8");
        stringentity.setChunked(true);
        httppost.setEntity(stringentity);
        httppost.addHeader("Accept" , "text/xml");
        httppost.addHeader("Content-Type" , "text/xml");
        httppost.addHeader("SOAPAction",soapAction);
        
            RequestConfig reqConfig = RequestConfig.custom()
        		.setConnectTimeout(Integer.parseInt(timeout)*1000)
        		.setSocketTimeout(Integer.parseInt(timeout)*1000)
        		.build();
        HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(reqConfig).build();
        HttpResponse response = null;
        
        try{
        	
        	response = httpclient.execute(httppost);
        	
        }catch(SSLPeerUnverifiedException e){
        	
        	e.printStackTrace();
        	
        }catch(HttpHostConnectException e){
        	
        	//ExceptionUtil.throwException(ExceptionBusinessConstants.WS_CLIENT_DOWN, ExceptionUtil.EXCEPTION_BUSINESS);
        	e.printStackTrace();
        }
        
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
        
    }
	
	public Document getResponseAsDocument(String response) throws Exception{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(response));
        Document doc = db.parse(is);
        
        return doc;
	}
	
    public String getElementValue(Element element,String childTagName){
    	
    	NodeList list =  element.getElementsByTagName(childTagName);
    	
    	if(list != null && list.getLength() > 0 && list.item(0).getFirstChild()!=null){
    		
    		Node node = list.item(0);
    		
    		if(node != null){
    			
    			return node.getFirstChild().getNodeValue();
    		}
    		
    		return null;
    		
    	}else{
    		
    		return null;
    	}
    }*/
	
	public String callServiceByAxis(SOAPEnvelope request, String endpoint, String soapAction, String timeout) throws Exception{
		
		//CommonUtil.printJavaHomeAndTrustStore();
		
		Service service = new Service();
        Call call=(Call) service.createCall();
        call.setTargetEndpointAddress(endpoint);
        call.setSOAPActionURI(soapAction);
        call.setTimeout((Integer.parseInt(timeout))*1000);
        SOAPEnvelope response = null;
        System.out.println("Timeout="+call.getTimeout());
    	response = (SOAPEnvelope) call.invoke(request);
    	return response.toString();
	}
	
	
/*	public SOAPEnvelope includeSoapNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("soap","http://soap.ae.dubaipolice.com/");
		
		return envelope;
	}
	
	public SOAPEnvelope includeBlockServiceNamespace(SOAPEnvelope envelope)throws SOAPException{
		
		envelope.addNamespaceDeclaration("ae","http://ae.gov.trf.vhl.ws.BlockService");
		
		return envelope;
	}
	public SOAPEnvelope includeTibcoNamespace(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("sch","http://www.tibco.com/schemas/TransactionService_BW_SRC_V2/SharedResources/XMLSchema/Schema.xsd");
		
		return envelope;
	}
	
public SOAPEnvelope includeSoapNamespaceAccidentReport(SOAPEnvelope envelope) throws SOAPException{
		
		envelope.addNamespaceDeclaration("soap","http://soap.dubaipolice.ae/");
		
		return envelope;
	}*/
}
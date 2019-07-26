package com.supra.soap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctc.wstx.util.ExceptionUtil;
import com.supra.common.CommonConstants;
import com.supra.dto.TestDTO;
import com.supra.dto.TestSoapDTO;
import com.supra.dto.lost.LostFound;
import com.supra.dto.lost.dp.ItemReferenceResponse;
import com.supra.dto.lost.dp.LostAndFoundResponseDTO;
import com.supra.dto.lost.dp.LostItemApplicationDTO;
import com.supra.service.TestService;





@Component("testEndpoint")
@WebService
@SOAPBinding(style=Style.RPC, use=Use.LITERAL)
public class TestEndpoint {
	
	
	
	@Autowired
	TestService testService;
	
	@Resource
	WebServiceContext ctx;
	
	
	@WebMethod(action="testRequest")
	public void testRequest(@WebParam(name="testRequest") TestSoapDTO testSoapDTO,
								   	   @WebParam(name = "ReferenceNo", mode = WebParam.Mode.OUT) Holder<Long> refNoHolder) throws Exception{
		
		TestDTO testDTO = new TestDTO();
		
		MessageContext mctx = ctx.getMessageContext();
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        String username = "";
        String password = "";
        
        if(userList!=null){
        	//get username
        	username = userList.get(0).toString();
        }
        	
        if(passList!=null){
        	//get password
        	password = passList.get(0).toString();
        }
		
		BeanUtils.copyProperties(testSoapDTO, testDTO);
	
		try{
			//Thread.sleep(20000l);
			
			refNoHolder.value = 6548971l;
			refNoHolder.value = testService.saveTest(testDTO);
			
		}catch(Exception ex){
			
			throw ex;
		}
	}
		@WebMethod(action="pagnationHibernate")
		public void listPagnation(@WebParam(name="testRequest") TestSoapDTO testSoapDTO,
									   	   @WebParam(name = "ReferenceNo", mode = WebParam.Mode.OUT) Holder<Long> refNoHolder) throws Exception{
			
			TestDTO testDTO = new TestDTO();
			
			MessageContext mctx = ctx.getMessageContext();
			Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
	        List userList = (List) http_headers.get("Username");
	        List passList = (List) http_headers.get("Password");

	       
			BeanUtils.copyProperties(testSoapDTO, testDTO);
			Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getFromDate());  
			Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getToDate());
			testDTO.setFromDate(fromDate);
			testDTO.setToDate(toDate);
			try{
				//Thread.sleep(20000l);
				testService.getList(testDTO);
				refNoHolder.value = 6548971l;
				refNoHolder.value = 3l;
				
			}catch(Exception ex){
				
				throw ex;
			}

	}
		
	@WebMethod(action="lostAndFound")
	public void lostAndFound(@WebParam(name="lostAndFound") LostFound lf,
									   	   @WebParam(name = "ReferenceNo", mode = WebParam.Mode.OUT) Holder<Integer> refNoHolder) throws Exception{
			
			TestDTO testDTO = new TestDTO();
			
			MessageContext mctx = ctx.getMessageContext();
			Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
	        List userList = (List) http_headers.get("Username");
	        List passList = (List) http_headers.get("Password");

	       
			//BeanUtils.copyProperties(testSoapDTO, testDTO);
			//Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getFromDate());  
			//Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getToDate());
			//testDTO.setFromDate(fromDate);
			//.setToDate(toDate);
			try{
				//Thread.sleep(20000l);
				Integer id=	testService.saveLostFoundSerial(lf);
				refNoHolder.value = id;
				
			}catch(Exception ex){
				
				throw ex;
			}

	}	
	
	@WebMethod(action="lostAndFoundUpdated")
	public void lostAndFoundUpdated(@WebParam(name="lostAndFound")LostItemApplicationDTO lostItemAppDTO,
									   	   @WebParam(name = "ReferenceNo", mode = WebParam.Mode.OUT) Holder<Integer> refNoHolder) throws Exception{
			
			TestDTO testDTO = new TestDTO();
			
			MessageContext mctx = ctx.getMessageContext();
			Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
	        List userList = (List) http_headers.get("Username");
	        List passList = (List) http_headers.get("Password");

	       
			//BeanUtils.copyProperties(testSoapDTO, testDTO);
			//Date fromDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getFromDate());  
			//Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(testSoapDTO.getToDate());
			//testDTO.setFromDate(fromDate);
			//.setToDate(toDate);
			try{
				//Thread.sleep(20000l);
				Integer id=	testService.saveLostFoundSerialUpdated(lostItemAppDTO);
				refNoHolder.value = id;
				
			}catch(Exception ex){
				
				throw ex;
			}

	}	
	
	@WebMethod(action="registerLostItem")
	public void registerLostItem(@WebParam(name="ReferenceNo") Long refno,
							 @WebParam(name = "ReferenceNo", mode = WebParam.Mode.OUT) Holder<String> refNoHolder,
							 @WebParam(name = "ItemReference", mode = WebParam.Mode.OUT) Holder<ItemReferenceResponse> itemReferenceResHolder,
							 @WebParam(name = "responseCode", mode = WebParam.Mode.OUT) Holder<Integer> respCodeHolder,
							 @WebParam(name = "responseDesc", mode = WebParam.Mode.OUT) Holder<String> respDescHolder) throws Exception{
		
		Long refNo=null;
		try{
			LostAndFoundResponseDTO response = testService.registerLostItem(refno);
			if(response!=null){
				ItemReferenceResponse itemRefRes=new ItemReferenceResponse();
				itemRefRes.setItemReference(response.getSubItemRefNos());
				refNoHolder.value=response.getRefNo();
				itemReferenceResHolder.value=itemRefRes;
				respCodeHolder.value = CommonConstants.SUCCESS_CODE;
				respDescHolder.value = CommonConstants.SUCCESS_DESC;	
			}else{
				respCodeHolder.value = CommonConstants.FAILURE_CODE;
				respDescHolder.value = CommonConstants.FAILURE_DESC;
			}
		}catch(Exception e){
			//ExceptionUtil.logDTOStackTrace("registerLostItem", e, refno);
			throw e;
		}
				
	}
	
	
	@WebMethod(action="callProcedure")
	public void callStoreProcedure() throws Exception{
		
		Long refNo=null;
		try{
			
			testService.callStoreProcedure();
		}catch(Exception e){
			//ExceptionUtil.logDTOStackTrace("registerLostItem", e, refno);
			throw e;
		}
				
	}

}


















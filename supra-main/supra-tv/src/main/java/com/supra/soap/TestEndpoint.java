package com.supra.soap;

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

import com.supra.dto.TestDTO;
import com.supra.dto.TestSoapDTO;
import com.supra.service.CommonSupraService;
import com.supra.service.TestService;





@Component("testEndpoint")
@WebService
@SOAPBinding(style=Style.RPC, use=Use.LITERAL)
public class TestEndpoint {
	
	
	
	@Autowired
	TestService testService;
	
	@Autowired
	CommonSupraService commonService;
	
	@Resource
	WebServiceContext ctx;
	
	
	@WebMethod(action="testRequest")
	public void testRequest(@WebParam(name="testRequest") TestSoapDTO testSoapDTO,
								   	   @WebParam(name = "result", mode = WebParam.Mode.OUT) Holder<Long> refNoHolder) throws Exception{
		
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
			System.out.println("ctx=======>"+ctx);
			System.out.println("commonService=======>"+commonService);
			System.out.println("testService=======>"+testService);
			refNoHolder.value = 6548971l;
			refNoHolder.value = testService.saveTest(testDTO);
			
			System.out.println("refNoHolder.value=="+refNoHolder.value);
			
			
		}catch(Exception ex){
			
			throw ex;
		}
	}
	

}
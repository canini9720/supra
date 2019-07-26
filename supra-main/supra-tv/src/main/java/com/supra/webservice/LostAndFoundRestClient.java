package com.supra.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.text.MessageFormat;
import com.supra.common.CommonConstants;
import com.supra.common.CommonUtil;
import com.supra.dto.lost.dp.LostItemApplicationDTO;


@Component
//@DependsOn("ApplicationProperties")
public class LostAndFoundRestClient {
	
private String endpoint = "";
 	
 	private Integer internalTimeout;
 	
 	private String username = "";
	
	private String password = "";
 	
 	@PostConstruct
 	public void initialize(){
 		//endpoint = ApplicationProperties.props.get("esb.host")+ApplicationProperties.props.get("createLostItem.endpoint");
 		
 		endpoint ="http://demo.gfi.in:8080/dp-lnf-api/api/v1/lostItem/createLostItem";
 		internalTimeout =  6*1000;
 		
 	}
 	
 	
 	public String createLostItem(LostItemApplicationDTO dto) throws Exception{
 		String response =null;
 		try {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	 		requestFactory.setConnectTimeout(internalTimeout);
	 		requestFactory.setReadTimeout(internalTimeout);
	 		RestTemplate restTemplate = new RestTemplate(requestFactory);
	 		HttpHeaders headers = new HttpHeaders();
			
	        
	        
	 		MediaType mType = new MediaType("application", "json", Charset.forName("UTF-8"));
	 	    headers.setContentType(mType);
	 		HttpEntity<String> request = null; 
	
	 		String json=buildjson(dto);
			request = new HttpEntity<String>(json,headers);
	 		
	 		response = restTemplate.postForObject(endpoint, request, String.class);
	 		
	 		System.out.println("normal response="+response);
	 		
	 		System.out.println("==============================");
	 		//postJsonWithHttpParams(json);
	 	//	System.out.println(response.getStatusLine().getStatusCode());
		}  catch (Exception e) {
			System.out.println("Exp occured===");
			//e.printStackTrace();
			throw e;
		}
 		return response;
 		
 	}
 	
 	public void postJsonWithHttpParams(String json) throws URISyntaxException, UnsupportedEncodingException, IOException {

 	    //add the http parameters you wish to pass
 	    List<NameValuePair> postParameters = new ArrayList<>();
 	    postParameters.add(new BasicNameValuePair("access_token", "cb29469a-7fa7-4fed-a3d3-48ce8dfc713c"));
 	    //postParameters.add(new BasicNameValuePair("param2", "param2_value"));

 	    //Build the server URI together with the parameters you wish to pass
 	    URIBuilder uriBuilder = new URIBuilder(endpoint);
 	    uriBuilder.addParameters(postParameters);

 	    HttpPost postRequest = new HttpPost(uriBuilder.build());
 	    postRequest.setHeader("Content-Type", "application/json");

 	    //this is your JSON string you are sending as a request
 	
 	   String yourJsonString =json;
 	    //pass the json string request in the entity
 	   org.apache.http.HttpEntity entity = new ByteArrayEntity(yourJsonString.getBytes("UTF-8"));
 	    postRequest.setEntity(entity);


 	    RequestConfig defaultRequestConfig = RequestConfig.custom()
 	            .setSocketTimeout(25000)
 	            .setConnectTimeout(25000)
 	            .setConnectionRequestTimeout(25000)
 	            .build();

 	    // Build the http client.
 	    CloseableHttpClient httpclient = HttpClients.custom()
 	            .setDefaultRequestConfig(defaultRequestConfig)
 	            .build();

 	    CloseableHttpResponse response = httpclient.execute(postRequest);

 	    //Read the response
 	    String responseString = "";

 	    int statusCode = response.getStatusLine().getStatusCode();
 	    String message = response.getStatusLine().getReasonPhrase();

 	   org.apache.http.HttpEntity responseHttpEntity = response.getEntity();

 	    InputStream content = responseHttpEntity.getContent();

 	    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
 	    String line;

 	    while ((line = buffer.readLine()) != null) {
 	        responseString += line;
 	    }

 	    //release all resources held by the responseHttpEntity
 	    EntityUtils.consume(responseHttpEntity);

 	    //close the stream
 	    response.close();
 	    
 	    System.out.println("responseString="+responseString);

 	  
 	}
 	
 	String buildjson(final LostItemApplicationDTO dto) throws java.text.ParseException{
 		StringBuilder jsonReq=new StringBuilder();
 		Gson gson=new Gson();
		jsonReq.append(CommonConstants.OP_BRACE+"\n");
		jsonReq.append("\"description\"").append(CommonConstants.COLON).append("\""+dto.getLostItemDTO().getDescription()+"\"").append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
			String lstDateFromStr=CommonUtil.getFormattedDatePattern((dto.getLostItemDTO().getLostDate()+" 00:00:00"), CommonConstants.DATE_ddMMyyyyHHmmss,CommonConstants.DATE_MMddyyyy_HH_MM);
		jsonReq.append("\"lstDateFrom\"").append(CommonConstants.COLON).append("\""+lstDateFromStr+"\"").append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		jsonReq.append("\"remarkEn\"").append(CommonConstants.COLON).append("\""+dto.getLostItemDTO().getRemarks()+"\"").append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		jsonReq.append("\"type\"").append(CommonConstants.COLON).append("\"submit\"").append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		
		jsonReq.append("\"category\"").append(CommonConstants.COLON).append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(dto.getLostItemDTO().getCategoryId()).append(CommonConstants.CL_BRACE).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		jsonReq.append("\"subCategory\"").append(CommonConstants.COLON).append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(dto.getLostItemDTO().getSubCategoryId()).append(CommonConstants.CL_BRACE).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		
			String mainAttrValues=gson.toJson( dto.getLostItemDTO().getAttrValues());
		jsonReq.append(CommonConstants.QUOTE).append("attrValues").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(mainAttrValues).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
			String mainAttachements=gson.toJson( dto.getLostItemDTO().getAttachmentDTO());
		jsonReq.append(CommonConstants.QUOTE).append("attachments").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(mainAttachements).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
			String mainBase64Images=gson.toJson( dto.getLostItemDTO().getImageDTO());
		jsonReq.append(CommonConstants.QUOTE).append("images").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(mainBase64Images).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		
			String mainLocation=gson.toJson( dto.getLostItemDTO().getLocationDTO());
		jsonReq.append(CommonConstants.QUOTE).append("locations").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(mainLocation).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		
		
		StringBuilder  lostOrFoundBystr=new StringBuilder();
		lostOrFoundBystr.append(CommonConstants.QUOTE).append("lostOrFoundBy").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
			.append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("email").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
			.append(CommonConstants.QUOTE).append(dto.getEmail()).append(CommonConstants.QUOTE).append(CommonConstants.COMMA);
		lostOrFoundBystr.append(CommonConstants.QUOTE).append("emiratesId").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
		.append(dto.getEmiratesId()).append(CommonConstants.COMMA);
		lostOrFoundBystr.append(CommonConstants.QUOTE).append("firstName").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
		.append(CommonConstants.QUOTE).append("Maninsh").append(CommonConstants.QUOTE).append(CommonConstants.COMMA);
		lostOrFoundBystr.append(CommonConstants.QUOTE).append("mobileNo").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
		.append(CommonConstants.QUOTE).append("656658989898").append(CommonConstants.QUOTE).append(CommonConstants.COMMA);
		
		lostOrFoundBystr.append(CommonConstants.QUOTE).append("nationality").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
		.append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append("131").append(CommonConstants.COMMA)
		.append(CommonConstants.QUOTE).append("name").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(CommonConstants.QUOTE).append("ALGERIA")
		.append(CommonConstants.QUOTE).append(CommonConstants.CL_BRACE).append(CommonConstants.CL_BRACE).append(CommonConstants.COMMA);
		
		jsonReq.append(lostOrFoundBystr).append(CommonConstants.NXT_LINE);
		
		StringBuilder  subItemstr=new StringBuilder();
		String subItemAttrValues=gson.toJson( dto.getLostItemDTO().getListSubItems().get(0).getAttrValues());

		subItemstr.append(CommonConstants.QUOTE).append("subItems").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(CommonConstants.SQ_OP_BRACE).append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("attrValues").append(CommonConstants.QUOTE).append(CommonConstants.COLON)
		.append(subItemAttrValues).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		
			String subItemAttachements= gson.toJson( dto.getLostItemDTO().getListSubItems().get(0).getAttachmentDTO());
			StringBuilder sbsubImages=new StringBuilder();
			for(int i=0;i<dto.getLostItemDTO().getListSubItems().size();i++){
				sbsubImages.append(gson.toJson( dto.getLostItemDTO().getListSubItems().get(i).getImageDTO()));
			}
			//subItemstr.append(sbsubImages.toString());
		subItemstr.append(CommonConstants.QUOTE).append("category").append(CommonConstants.QUOTE).append(CommonConstants.COLON)	.append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(dto.getLostItemDTO().getListSubItems().get(0).getCategoryId()).append(CommonConstants.CL_BRACE).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE)  ;                 
		subItemstr.append(CommonConstants.QUOTE).append("description").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(CommonConstants.QUOTE).append(dto.getLostItemDTO().getListSubItems().get(0).getDescription()).append(CommonConstants.QUOTE).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE)  ;      
		subItemstr.append(CommonConstants.QUOTE).append("attachments").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(subItemAttachements).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE)  ;
		subItemstr.append(CommonConstants.QUOTE).append("images").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(sbsubImages.toString()).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE)  ;
		
		subItemstr.append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(0).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		subItemstr.append(CommonConstants.QUOTE).append("remarkEn").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(gson.toJson(dto.getLostItemDTO().getListSubItems().get(0).getRemarks())).append(CommonConstants.COMMA).append(CommonConstants.NXT_LINE);
		subItemstr.append(CommonConstants.QUOTE).append("subCategory").append(CommonConstants.QUOTE).append(CommonConstants.COLON)	.append(CommonConstants.OP_BRACE).append(CommonConstants.QUOTE).append("id").append(CommonConstants.QUOTE).append(CommonConstants.COLON).append(dto.getLostItemDTO().getListSubItems().get(0).getSubCategoryId()).append(CommonConstants.CL_BRACE).append(CommonConstants.NXT_LINE)  ;                 
		subItemstr.append(CommonConstants.CL_BRACE).append(CommonConstants.SQ_CL_BRACE);
		jsonReq.append(subItemstr);
		jsonReq.append(CommonConstants.CL_BRACE);
		
		System.out.println(jsonReq.toString());
 		return jsonReq.toString();
 	}
 	
 	

}

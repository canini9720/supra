package com.supra.implementation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supra.common.implementation.CommonDAO;
import com.supra.common.implementation.CommonService;
import com.supra.dto.TestDTO;
import com.supra.dto.lost.LostFound;
import com.supra.dto.lost.dp.LostAndFoundResponseDTO;
import com.supra.dto.lost.dp.LostItemApplicationDTO;
import com.supra.dto.lost.dp.LostSubItemDTO;
import com.supra.dto.lost.dp.AttachmentDTO;
import com.supra.dto.lost.dp.BaseItemAttachmentDTO;
import com.supra.dto.lost.dp.ImageDTO;
import com.supra.model.DipCommVWEntity;
import com.supra.model.LostFoundAttachEntity;
import com.supra.model.LostFoundSerialEntity;
import com.supra.model.SupraLogEntity;
import com.supra.model.SupraLogEntityDefault;
import com.supra.model.TestEntity;
import com.supra.service.TestService;
import com.supra.task.TvAsyncTask;
import com.supra.webservice.LostAndFoundRestClient;


@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO testDAO;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	TvAsyncTask tvAsyncTask;
	
	@Autowired
	private LostAndFoundRestClient lostAndFoundRestClient;

	
	@Override
	public Long saveTest(TestDTO dto) throws Exception {
		
		
		SupraLogEntityDefault supraLogDefault=new SupraLogEntityDefault();
		supraLogDefault.setLog("This default is log");
		commonService.saveSupraLogWithDefaultTransaction(supraLogDefault);
		
		TestEntity testEntity=new TestEntity();
		testEntity.setName("Tester45");
		
		SupraLogEntity supraLog=new SupraLogEntity();
		supraLog.setLog("This is log");
		commonService.saveSupraLog(supraLog);
		System.out.println("supra log entry entity saved");
		
		testDAO.saveTest(testEntity,dto);
		System.out.println("Test entity saved");
		tvAsyncTask.doTvAsyncTask();
		return testEntity.getId();
	}
	
	@Async("specificTaskExecutor")
	public void doAsyc() throws InterruptedException{
		Thread.sleep(15000l);
	}

	@Override
	public List<DipCommVWEntity> getList(TestDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return testDAO.getList(dto);
	}

	@Override
	public Integer saveLostFoundSerial(LostFound dto) throws Exception {
		// TODO Auto-generated method stub
		//LostFoundSerialEntity lfEntity=new LostFoundSerialEntity();
		//BeanUtils.copyProperties(dto, testDTO);
		
		return testDAO.saveLostFoundSerial(dto);
	}
	
	void validateLocation(LostItemApplicationDTO dto){
		String area="";
		Double latitude=null;
		Double longitude=null;
		if(dto.getLostItemDTO().getLocationDTO()!=null && dto.getLostItemDTO().getLocationDTO().get(0)!=null
				&& dto.getLostItemDTO().getLocationDTO().get(0).getAreaName()!=null ){
			area=dto.getLostItemDTO().getLocationDTO().get(0).getAreaName();
			if(area.equals("?")){
				area="";
			}
		}
		
		if(dto.getLostItemDTO().getLocationDTO()!=null && dto.getLostItemDTO().getLocationDTO().get(0)!=null
				&& dto.getLostItemDTO().getLocationDTO().get(0).getLatitude()!=null){
			latitude=dto.getLostItemDTO().getLocationDTO().get(0).getLatitude();
		}
		if(dto.getLostItemDTO().getLocationDTO()!=null && dto.getLostItemDTO().getLocationDTO().get(0)!=null
				&& dto.getLostItemDTO().getLocationDTO().get(0).getLongitude()!=null){
			longitude=dto.getLostItemDTO().getLocationDTO().get(0).getLongitude();
		}
		
			
		System.out.println("-------------------------------------");
		System.out.println("area="+area);
		System.out.println("latitude="+latitude);
		System.out.println("longitude="+longitude);
			
			
		if(area.equals("") &&(
				latitude==null && longitude==null)){
			System.out.println("Areaname should not empty");
		}else{
			if(!area.isEmpty()){
				return;
			}
			if(latitude==null || longitude==null){
				System.out.println("latitude or longitude should not empty");
			}
		}
		
	}

	@Override
	public Integer saveLostFoundSerialUpdated(LostItemApplicationDTO dto) throws Exception {
		validateLocation(dto);
		return testDAO.saveLostFoundSerialUpdated(dto);
		//return 32;
	}

	@Override
	public LostAndFoundResponseDTO registerLostItem(Long referenceNo) throws Exception {
		LostAndFoundResponseDTO responseDTO =null;
		//LostAndFoundServiceImplUtil.validateRegisterLostItem(referenceNo);
		LostFoundSerialEntity entity = testDAO.validateRefno(referenceNo);
		
		
		byte[] serialObj = entity.getLfObject();
		ObjectInputStream objectIn = null;
		if (serialObj != null){
			objectIn = new ObjectInputStream(new ByteArrayInputStream(serialObj));
			Object deSerializedObject = objectIn.readObject();
			
			LostItemApplicationDTO dtoObj=(LostItemApplicationDTO)deSerializedObject;
			//System.out.println("final lf from db="+dtoObj);
			dtoObj=addAttachemnts(dtoObj);
			tojson(dtoObj);
			String responseJson=lostAndFoundRestClient.createLostItem(dtoObj);
			if(responseJson!=null){
				ObjectMapper mapper = new ObjectMapper();
				//String st="{\"refNo\": \"L-C659680\",\"dpRefNo\": null,\"message\": \"Lost Item saved successfully\",\"subItem refNos\": \"L-C659680-1\",\"L-C659680-2\",\"L-C659680-3\",\"lost item id\": 680,\"status\": true}";
				//responseJson=st;
				
				Map<String,Object> map = mapper.readValue(responseJson, Map.class);

				if(map!=null && map.get("status")!=null && (Boolean)map.get("status")){
					responseDTO = new LostAndFoundResponseDTO();
					responseDTO.setRefNo((String)map.get("refNo"));
					responseDTO.setDpRefNo((String)map.get("dpRefNo"));
					responseDTO.setMessage((String)map.get("message"));
					String[] commaSeparatedArr = ((String)map.get("subItem refNos")).split("\\s*,\\s*");
				    List<String> result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
					responseDTO.setSubItemRefNos(result);
					responseDTO.setLostItemId((Integer)map.get("lost item id"));
					responseDTO.setStatus((Boolean)map.get("status"));
					
					//entity.setLostItemId(responseDTO.getLostItemId().longValue());
					//entity.setLostItemRefNo(responseDTO.getRefNo());
					//int up=commonService.updateAppCompletedByCustomer(referenceNo);
					
					
				}
			}

			System.out.println("map reso="+responseDTO);
		}
		
		return responseDTO;
	}
	
	

	void tojson(LostItemApplicationDTO dtoObj){
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try { 
	  
	            // get Oraganisation object as a json string 
	            String jsonStr = Obj.writeValueAsString(dtoObj); 
	  
	            // Displaying JSON String 
	            //System.out.println("\n\njsonStr="+jsonStr); 
	        } 
	  
	        catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	}
	
	private LostItemApplicationDTO addAttachemnts(LostItemApplicationDTO dtoObj) throws Exception {
		Map<Long,LostFoundAttachEntity> mapAttach=testDAO.getAttachments(dtoObj.getReferenceNo());
		
		if(dtoObj!=null && null !=dtoObj.getLostItemDTO() && null!=dtoObj.getLostItemDTO().getAttachmentDTO()){
			/*dtoObj.getLostItemDTO().getAttachmentDTO().forEach(x->{
				BaseItemAttachmentDTO base=x.getBaseItemAttachment();
				if(base!=null & null!=base.getTempId()){
					LostFoundAttachEntity attachEntity=mapAttach.get(base.getTempId().longValue());
					ImageDTO imageDto=new ImageDTO();
					imageDto.setAttachmentBase64("base1");
					imageDto.setFileName(attachEntity.getAttachName());
					imageDto.setMimeType(attachEntity.getAttachType());
					dtoObj.getLostItemDTO().seti
				}
			});*/
			List<ImageDTO> mainImageDtoList=new ArrayList<ImageDTO>();
			for(AttachmentDTO mainAttach:dtoObj.getLostItemDTO().getAttachmentDTO()){
				BaseItemAttachmentDTO base=mainAttach.getBaseItemAttachment();
				if(base!=null & null!=base.getTempId()){
					LostFoundAttachEntity attachEntity=mapAttach.get(base.getTempId().longValue());
					if(attachEntity!=null){
						ImageDTO imageDto=new ImageDTO();
						imageDto.setAttachmentBase64("base1");
						imageDto.setFileName(attachEntity.getAttachName());
						imageDto.setMimeType(attachEntity.getAttachType());
						mainImageDtoList.add(imageDto);
					}
					
				}
			}
			dtoObj.getLostItemDTO().setImageDTO(mainImageDtoList);
			
			for(int i=0;i<dtoObj.getLostItemDTO().getListSubItems().size();i++){
				LostSubItemDTO subItem=dtoObj.getLostItemDTO().getListSubItems().get(i);
				List<ImageDTO> subImageDtoList=new ArrayList<ImageDTO>();
					for(int j=0;j<subItem.getAttachmentDTO().size();j++){
						
						AttachmentDTO subAttach=subItem.getAttachmentDTO().get(j);
						
						BaseItemAttachmentDTO subBase=subAttach.getBaseItemAttachment();
						if(subBase!=null & null!=subBase.getTempId()){
							LostFoundAttachEntity attachEntity=mapAttach.get(subBase.getTempId().longValue());
							if(attachEntity!=null){
								ImageDTO imageDto=new ImageDTO();
								imageDto.setAttachmentBase64("sub1");
								imageDto.setFileName(attachEntity.getAttachName());
								imageDto.setMimeType(attachEntity.getAttachType());
								subImageDtoList.add(imageDto);
							}
							
						
						}
				}
					System.out.println(subImageDtoList+"size="+subImageDtoList.size());
					subItem.setImageDTO(subImageDtoList);
					subImageDtoList=null;
				
			}
			
			
		}
		
		return dtoObj;
	}

	@Override
	public void callStoreProcedure() throws Exception {
		testDAO.callStoreProcedure();
		
	}
	

}
















package com.supra.common.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.supra.model.SMSInternalAlertEntity;
import com.supra.model.SMSTextMsgEntity;

@Repository
public class CommonDAOImpl extends JdbcCommonDao implements CommonDAO {

	
	@Override
	public List<SMSTextMsgEntity> getAllSMSText() throws Exception {
		List<SMSTextMsgEntity> allSMS = this.getEm().createQuery("SELECT e FROM SMSTextMsgEntity e").getResultList();
		return allSMS;
	}
	
	@Override
	public SMSInternalAlertEntity smsAlert(long serviceId, String msgCode) throws Exception {
		SMSInternalAlertEntity entity=null;
		//String sql="Select  A from SMSTextMsgEntity L, SMSInternalAlertEntity A  where L.msgCode =:msgCode AND "+
			//	" A.serviceId =:serviceId";
		/*String sql="  from SMSInternalAlertEntity T1   "+   
		" INNER JOIN  SMSTextMsgEntity T2 ON T1.msgCode=T2.msgCode "+    
		"where T1.serviceId = 10";*/
		String sql="  from SMSInternalAlertEntity T1   "+   
				" INNER JOIN  SMSTextMsgEntity T2 ON T1.msgCode=T2.msgCode  "+    
				"where T1.serviceId =:serviceId and T1.msgCode=:msgCode";
		List<Object> list= this.getEm().createQuery(sql)
				.setParameter("msgCode", msgCode)
				.setParameter("serviceId", serviceId)
				.getResultList();
		if(list!=null && !list.isEmpty()){
			
			if(list.get(0)!=null){
				Object[] ety=(Object[])list.get(0);
				System.out.println("T1="+ety[0]);
			}
			if(list.get(0)!=null){
				Object[] ety=(Object[])list.get(0);
				if(ety[1]!=null){
					System.out.println("T2="+ety[1]);	
				}
				
			}
			
		}
		return entity;
	}


}












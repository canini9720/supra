package com.supra.implementation;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.hibernate.internal.util.SerializationHelper;
import org.springframework.stereotype.Repository;

import com.supra.common.implementation.JdbcCommonDao;
import com.supra.dto.TestDTO;
import com.supra.dto.lost.LostFound;
import com.supra.dto.lost.dp.LostItemApplicationDTO;
import com.supra.exception.BusinessException;
import com.supra.model.DipCommVWEntity;
import com.supra.model.LostFoundAttachEntity;
import com.supra.model.LostFoundSerialEntity;
import com.supra.model.TestEntity;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TestDAOImpl extends JdbcCommonDao implements TestDAO{

	//@Override
	public void saveTest(TestEntity entity,TestDTO dto) throws Exception {
		if(dto.getEmiratesID().longValue()==5l){
			throw new BusinessException("busin exp");			
			//throw new Exception("busin exp");
		}

		this.getEm().persist(entity);
		System.out.println("tested");
	}

	@Override
	public List<DipCommVWEntity> getList(TestDTO dto) throws Exception {
		/*Query query = this.getEm().createQuery("From DipCommVWEntity");
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<DipCommVWEntity> fooList = fooList = query.list();*/
		
		CriteriaBuilder criteriaBuilderObj = this.getEm().getCriteriaBuilder();

		
		// Making The Query Object From The 'CriteriaBuilder' Instance
		CriteriaQuery<Object> queryObj = criteriaBuilderObj.createQuery();
		
		Root<DipCommVWEntity> from = queryObj.from(DipCommVWEntity.class);

		// Step #1 - Displaying All Records
		System.out.println("\n! Display All Records For The 'Employee' Table !\n");
		CriteriaQuery<Object> selectQuery = queryObj.select(from);
		
		//queryObj.where(criteriaBuilderObj.equal(from.get("refNo"), dto.getReferenceNo()));
		//criteriaBuilderObj.b
		
		/*List<Predicate> restrictions = new ArrayList<>();
		  restrictions.add(criteriaBuilderObj.between(from.get("crDate"), dto.getFromDate(), dto.getToDate()));
		  restrictions.add(from.get("refNo"),Long.valueOf(dto.getReferenceNo()));
		  criteria.where(restrictions.toArray(new Predicate[restrictions.size()]));*/
		
		queryObj.where(criteriaBuilderObj.and(criteriaBuilderObj.equal(from.get("refNo"), dto.getReferenceNo()),
				criteriaBuilderObj.equal(from.get("dpUserId"), dto.getDpUserId()),
				criteriaBuilderObj.between((Expression) from.get("crDate").as(Date.class), dto.getFromDate(), dto.getToDate())));
		 
		TypedQuery<Object> typedQuery = this.getEm().createQuery(selectQuery);
		
		
		
		typedQuery.setFirstResult(dto.getStartPage()-1);
		typedQuery.setMaxResults(20);
		List<Object> employeeList = typedQuery.getResultList();
	
		return null;
	}

	@Override
	public Integer saveLostFoundSerial(LostFound dto) throws Exception {
		
		byte[] bytes = SerializationHelper.serialize( dto );
		LostFoundSerialEntity entity=new LostFoundSerialEntity();
		entity.setId(dto.getId());
		entity.setLfObject(bytes);
		this.getEm().persist(entity);
		Integer primid=entity.getId();
		List qy=this.getEm().createQuery("select s from LostFoundSerialEntity s where s.id = ?1").setParameter(1, primid).getResultList();

		if(qy!=null){
			LostFoundSerialEntity reobj=(LostFoundSerialEntity)qy.get(0);
			//System.out.println(reobj);
			byte[] buf = reobj.getLfObject();
			ObjectInputStream objectIn = null;
			if (buf != null){
				objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
				Object deSerializedObject = objectIn.readObject();
				
				LostFound finalobj=(LostFound)deSerializedObject;
				//System.out.println("final lf from db="+finalobj);
			}

			
		}
		return entity.getId();
	}

	@Override
	public Integer saveLostFoundSerialUpdated(LostItemApplicationDTO dto) throws Exception {
		byte[] bytes = SerializationHelper.serialize( dto );
		LostFoundSerialEntity entity=new LostFoundSerialEntity();
		Random r = new Random();
		int low = 1;
		int high = 10;
		int f = (r.nextInt(high-low) + low);
		System.out.println("rand="+f);
		entity.setId(dto.getReferenceNo().intValue());
		entity.setLfObject(bytes);
		this.getEm().persist(entity);
		Integer primid=entity.getId();
		List qy=this.getEm().createQuery("select s from LostFoundSerialEntity s where s.id = ?1").setParameter(1, primid).getResultList();

		if(qy!=null){
			LostFoundSerialEntity reobj=(LostFoundSerialEntity)qy.get(0);
			//System.out.println(reobj);
			byte[] buf = reobj.getLfObject();
			ObjectInputStream objectIn = null;
			if (buf != null){
				objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
				Object deSerializedObject = objectIn.readObject();
				
				LostItemApplicationDTO finalobj=(LostItemApplicationDTO)deSerializedObject;
				//System.out.println("final lf from db="+finalobj);
			}

			
		}
		return entity.getId();
	}

	@Override
	public LostFoundSerialEntity validateRefno(Long refNo) {
		Integer in=refNo.intValue();
		List<LostFoundSerialEntity> list = this.getEm().createQuery("SELECT B FROM LostFoundSerialEntity B WHERE B.id = :refNo ", LostFoundSerialEntity.class)
				.setParameter("refNo", in)
				.getResultList();
	
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Map<Long,LostFoundAttachEntity> getAttachments(Long refNo)throws Exception {
		//Integer in=refNo.intValue();
		List<LostFoundAttachEntity> list = this.getEm().createQuery("SELECT B FROM LostFoundAttachEntity B WHERE B.refNo = :refNo ", LostFoundAttachEntity.class)
				.setParameter("refNo", refNo)
				.getResultList();
	
		if(list != null && !list.isEmpty()){
			Map<Long, LostFoundAttachEntity> result3 = list.stream().collect(Collectors.toMap(x->x.getId(), x->x));
			return result3;
		}else{
			return null;
		}
	}

	@Override
	public void callStoreProcedure() throws Exception {
		StoredProcedureQuery query = this.getEm().createStoredProcedureQuery("PKG_SWAT.SWAT_REQUEST_PROC")
				
				.registerStoredProcedureParameter(1,Long.class,ParameterMode.IN)
				.registerStoredProcedureParameter(2,Class.class,ParameterMode.REF_CURSOR)
				.setParameter(1, 1L);
				 
				query.execute();
				 
				List<Object[]> postComments = query.getResultList();
				System.out.println("postComments="+postComments);
 		
	}



	/*@Override
	public void saveTest(TestEntity entity) throws Exception {
		this.getEm().persist(entity);
		
	}*/


}






















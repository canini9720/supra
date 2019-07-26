package com.supra.dto.lost;


import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Main obj=new Main();
		LostFound lf=obj.constructLF();
	}
	LostFound constructLF(){
		String description="Lost at airport";
		Integer id=0;
		String lstDateFrom="04-17-2019 02:30";
		String lstDateTo="04-19-2019 05:30";
		String remarkEn="";
		String type="submit";
		
		
		//LnfSubcatAttribute[] arrLnf=new LnfSubcatAttribute[3];
		LnfSubcatAttribute lnf1=new LnfSubcatAttribute();
		LnfSubcatAttribute lnf2=new LnfSubcatAttribute();
		LnfSubcatAttribute lnf3=new LnfSubcatAttribute();
		lnf1.setId(196);
		lnf1.setName("Ring Type");
		lnf1.setAttrQues("What is the Ring Type ?");
		
		lnf2.setId(197);
		lnf2.setName("Condition");
		lnf2.setAttrQues("In which Condition the item is ?");
		
		lnf3.setId(195);
		lnf3.setName("Car Model");
		lnf3.setAttrQues("Which Car Model, choose from the following:");
		
		List<AttrValue> listAttrValue=new ArrayList<AttrValue>();
		AttrValue att1=new AttrValue();
		AttrValue att2=new AttrValue();
		AttrValue att3=new AttrValue();
		
		att1.setId(59);
		att1.setValue("round");
		att1.setLnfSubcatAttribute(lnf1);
		
		att2.setId(59);
		att2.setValue("old");
		att2.setLnfSubcatAttribute(lnf2);
		
		att3.setId(59);
		att3.setValue("Audi");
		att3.setLnfSubcatAttribute(lnf3);
		
		listAttrValue.add(att1);
		listAttrValue.add(att2);
		listAttrValue.add(att3);
		
		Category cat=new Category();
		cat.setId(6);
		cat.setName("Keys");
		cat.setIsSelected(false);
		
		BaseItemAttachment baseAttach1=new BaseItemAttachment();
		baseAttach1.setTempId(2655);
		baseAttach1.setFileName("1554969245030.jpg");
		baseAttach1.setMimeType("image/jpeg");
		
		Attachment attach=new Attachment();
		attach.setBaseItemAttachment(baseAttach1);
		
		List<Attachment> listAttach=new ArrayList<Attachment>();
		listAttach.add(attach);
		
		Location loc1=new Location();
		loc1.setAreaName("Airport Terminal 1, Arrival,Dubai - United Arab Emirates");
		loc1.setLatitude(25.249323000000004d);
		loc1.setLongitude(55.350674d);
		loc1.setRadius(3);
		
		List<Location> listLocation=new ArrayList<Location>();
		listLocation.add(loc1);
		
		
		Nationality nat=new Nationality();
		nat.setId(131);
		nat.setName("ALGERIA");
		LostOrFoundBy lostOrFoundBy=new LostOrFoundBy();
		lostOrFoundBy.setEmail("man@jd.ndn");
		lostOrFoundBy.setEmiratesId("646646595959959");
		lostOrFoundBy.setFirstName("Manish");
		lostOrFoundBy.setMobileNo("656658989898");
		lostOrFoundBy.setNationality(nat);
		
		SubCategory subCat=new SubCategory();
		subCat.setId(59);
		subCat.setName("Car Keys");
		
		LostFound lf=new LostFound();
		/*lf.setAttrValues(listAttrValue);
		lf.setCategory(cat);
		lf.setDescription(description);
		lf.setAttachments(listAttach);
		lf.setId(id);
		lf.setLocations(listLocation);
		lf.setLstDateFrom(lstDateFrom);
		lf.setLstDateTo(lstDateTo);
		lf.setLostOrFoundBy(lostOrFoundBy);
		lf.setRemarkEn(remarkEn);
		lf.setSubCategory(subCat);
		lf.setType(type);*/
		
		return lf;
		
		
		
	}

}

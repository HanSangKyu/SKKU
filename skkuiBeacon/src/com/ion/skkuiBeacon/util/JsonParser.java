package com.ion.skkuiBeacon.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ion.skkuiBeacon.bean.MyItem;

public class JsonParser {

	private static final int arraysize = 34;
	ArrayList<MyItem> array = new ArrayList<MyItem>();
	
	public ArrayList<MyItem> getItem(){
		
		try{
			JSONObject jsonObject = new JSONObject("{beacon:[" +
					"{key:\"1-1\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0241_1.jpg\", name:\"삼성학술정보관\",explain:\"건물번호 : 48\n규모 : 지하2층/지상7층 - 연면적 : 약 7,200㎡ - 공사기간 : 2006.11~2008.8 (22개월) - 신축내용 : 열람석 약 2,500석 규모 및 약 100만권의 소장\"}," +
					"{key:\"1-2\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/61.JPG\", name:\"제1공학관\",explain:\"건물번호 : 21, 22, 23\"}," +
					"{key:\"1-4\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/71.JPG\", name:\"제2공학관\",explain:\"건물번호 : 25, 26, 27\"}," +
					"{key:\"1-5\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/sandan.jpg\", name:\"산학협력센터\",explain:\"건물번호 : 85\"}," +
					"{key:\"1-6\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0224_1.jpg\", name:\"제1과학관\",explain:\"건물번호 : 31\"}," +
					"{key:\"1-7\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0224_1.jpg\", name:\"제2과학관\",explain:\"건물번호 : 32\"}," +
					"{key:\"1-8\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0225_1.jpg\", name:\"기초학문관\",explain:\"건물번호 : 51\"}," +
					"{key:\"1-9\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0226_1.jpg\", name:\"생명공학관\",explain:\"건물번호 : 61, 62\"}," +
					"{key:\"1-3\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0203_2.jpg\", name:\"의학관\",explain:\"건물번호 : 71\"}," +
					"{key:\"1-10\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/2.jpg\", name:\"약학관\",explain:\"건물번호 : 53\"}," +
					"{key:\"1-11\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/1229_4.jpg\", name:\"제약기술관\",explain:\"건물번호 : 84\"}," +
					"{key:\"1-12\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/IMG_78811.jpg\", name:\"화학관\",explain:\"건물번호 : 33\"}," +
					"{key:\"1-13\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/IMG_78811.jpg\", name:\"반도체관\",explain:\"건물번호 : 40\"}," +
					"{key:\"1-14\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/SNB10345.JPG\", name:\"제1종합연구동\",explain:\"건물번호 : 81\"}," +
					"{key:\"1-15\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0208_1.jpg\", name:\"제2종합연구동\",explain:\"건물번호 : 83\"}," +
					"{key:\"1-16\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/51.JPG\", name:\"공학십슬동\",explain:\"건물번호 : 20, 24, 28\"}," +
					"{key:\"1-17\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/img_0216_1.jpg\", name:\"생명공학실습동\",explain:\"건물번호 : 63, 64\"}," +
					"{key:\"1-18\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/41.JPG\", name:\"건축환경실험실\",explain:\"건물번호 : 30\"}," +
					"{key:\"1-19\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/91.JPG\", name:\"학생회관\",explain:\"건물번호 : 03\"}," +
					"{key:\"1-20\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0228_1.jpg\", name:\"복지회관\",explain:\"건물번호 : 04\"}," +
					"{key:\"1-21\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0202_1.jpg\", name:\"대강당\",explain:\"건물번호 : 70\"}," +
					"{key:\"1-22\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0229_2.jpg\", name:\"수성관\",explain:\"건물번호 : 05\"}," +
					"{key:\"1-23\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/101.JPG\", name:\"체육관\",explain:\"건물번호 : 72\"}," +
					"{key:\"1-24\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0239_1.jpg\", name:\"운용재\",explain:\"건물번호 : 49\"}," +
					"{key:\"1-25\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0240_1.jpg\", name:\"학군단\",explain:\"건물번호 : 89\"}," +
					"{key:\"1-26\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0233_1.jpg\", name:\"인관\",explain:\"건물번호 : 91\"}," +
					"{key:\"1-27\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0234_1.jpg\", name:\"의관\",explain:\"건물번호 : 92\"}," +
					"{key:\"1-28\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0235_1.jpg\", name:\"예관\",explain:\"건물번호 : 93\"}," +
					"{key:\"1-29\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0236_1.jpg\", name:\"지관\",explain:\"건물번호 : 95\"}," +
					"{key:\"1-30\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/kkk.jpg\", name:\"신관\",explain:\"건물번호 : 298\"}," +
					"{key:\"1-31\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0237_1.jpg\", name:\"게스트하우스\",explain:\"건물번호 : 96\"}," +
					"{key:\"1-32\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/21.JPG\", name:\"환경플랜트\",explain:\"건물번호 : 12\"}," +
					"{key:\"1-33\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/1229_5.jpg\", name:\"파워플랜트\",explain:\"건물번호 : 10\"}," +
					"{key:\"1-34\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/img_0213_1.jpg\", name:\"건축관리실\",explain:\"건물번호 : 16\"}" +
					"]}");
			JSONArray beacon = jsonObject.getJSONArray("beacon");
			
			for(int i=0;i<arraysize;i++)
			{
				array.add(new MyItem(beacon.getJSONObject(i).getString("key"), beacon.getJSONObject(i).getString("image"), beacon.getJSONObject(i).getString("name"), beacon.getJSONObject(i).getString("explain"), 100));
			}
		} catch(JSONException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
}

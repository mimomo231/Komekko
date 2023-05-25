package com.example.komekko;

import java.util.ArrayList;
import java.util.HashMap;

public class MakeVideoList {

	public static ArrayList< ArrayList<HashMap<String,String>> > rootArrayList;
	public static ArrayList< HashMap<String, String> > catArrayList;
	public static ArrayList< HashMap<String, String> > videoArrayList;
	public static HashMap<String, String> hashMap;


	//--------------------------------------------------------------------------------------------
	//them video id, title, description vao hashmap--> them vao video videoArrayList
	public static void addVideoItem(String video_id, String title, String desciption){
		hashMap = new HashMap<>();
		hashMap.put("vdo_id", video_id);
		hashMap.put("vdo_title", title);
		hashMap.put("vdo_desciption", desciption);
		videoArrayList.add(hashMap);
	}
	//========================================================================
	//tao danh sach phat video
	public static void createPlayListForVideo(String category_name, Integer drawable){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}
	//========================================================================

	//===============================
	public static void createCategoryForWebsite(String category_name, Integer drawable, String url){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		hashMap.put("url", url);
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}
	//========================================================================

	//===============================
	public static void createCategoryForPDF(String category_name, Integer drawable, String pdfAssetName){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		hashMap.put("pdfAssetName", pdfAssetName);
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}
	//hoc tu vung
	private static void createCategoryForLearning(String category_name, Integer drawable) {
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}

	//---------------------------------------------------->>>>>>
	//----------------------------------------------------
	public static void createMyAlbums(){

		rootArrayList = new ArrayList();
		catArrayList = new ArrayList<>();
		videoArrayList = new ArrayList<>();

		//=========================
		createCategoryForPDF("Text Book [PDF]", R.drawable.category_4, "english.pdf");
		//=========================


		//========================Video Item Category
		addVideoItem("cslyPxzMLWg", "Unit 1: Friend!", "English with tv series");
		addVideoItem("Nm_VZfo9fxA", "Unit 2: Big Bang Theory", "English with tv series");
		addVideoItem("HOC50hYce0U", "Unit 3: Learn English with Friends: Flirting with Police", "English with tv series");
		addVideoItem("aA4ejeSw0DY", "Unit 4: The Big Bang Theory", "English with tv series");
		addVideoItem("qtZRbKxmB5M", "Unit 5: Joey's Perverted Tailor (Friends)", "English with tv series");
		addVideoItem("Lllt-NcoleM", "Unit 6: Stu the Pot Smoking Teacher", "English with tv series");
		addVideoItem("UggVtj1hMy0", "Unit 7: Are My Boobs Getting Bigger?", "English with tv series");
		addVideoItem("969P2xFN-GU", "Unit 8: Ross' Beautiful Cousin!", "English with tv series");
		addVideoItem("G3qHtTsst7Q", "Unit 9: Joey's Thanksgiving Pants", "English with tv series");
		addVideoItem("_RFTcoqSA5M", "Unit 10: Monica's Boob Job", "English with tv series");
		addVideoItem("KC54i2KCi0w", "Unit 11: Seinfeld 'The Limo'", "English with tv series");
		addVideoItem("oAeKhvdprjE", "Unit 12: The Sexy Nanny", "English with tv series");
		addVideoItem("_RFTcoqSA5M", "Unit 13: Blowing up the Moon", "English with tv series");
		addVideoItem("gHvBLFYNzMc", "Unit 14: How I Met Your Mother (Barney's Look-Alike)", "English with tv series");
		addVideoItem("9xs2bUi-k_k", "Unit 15: The IT Crowd", "English with tv series");
		addVideoItem("OSvmxaisqLc", "Unit 16: The Big Picture and Become a Global Citizen", "English with tv series");
		addVideoItem("fXc7YcsQODE", "Unit 17: What Phoebe Finds in Her Soda", "English with tv series");
		addVideoItem("K3MwWC6Do-k", "Unit 18: Sheldon's Hot Sister", "English with tv series");
		addVideoItem("Efl8Z69zKLs", "Unit 19: Seinfeld", "English with tv series");
		addVideoItem("N0eHkFgIABk", "Unit 20: the Simpsons & Red Hot Chili Peppers", "English with tv series");
		addVideoItem("7fjXsV7P4wM", "Unit 21: Friends: Rachel's Sexy Dream", "English with tv series");
		addVideoItem("4S3BrpCJFt4", "Unit 22: English Fluency", "English with tv series");
		addVideoItem("HLBxx-rejmE", "Unit 23: How I Met Your Mother", "English with tv series");
		addVideoItem("APAJ7n0PQNA", "Unit 24: Game Of Thrones | Jon Snow Meets Daenerys", "English with tv series");
		addVideoItem("3U9l1q4IOnE", "Unit 25: Vocabulary for the Body & Doctors | Friends", "English with tv series");
		// tao danh sach phat video
		createPlayListForVideo("Class Lecture", R.drawable.category_1);

		//=========================
		createCategoryForLearning("Vocabulary", R.drawable.category_5);

		createCategoryForWebsite("Cambridge", R.drawable.category_2, "https://dictionary.cambridge.org/");

		//==========================================================================*

	}


}


package com.ibm.achievements.crud;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.achievements.model.Achievement;

public class RevenueInfluenceCRUD extends AchievementCRUD{

	@Override
	public boolean addAchievement(Achievement achievement,
			String achievementJson) {
		try {
			JSONObject jsonObject = new JSONObject(achievementJson) ;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean updateAchievement(JSONObject achievementJson) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAchievement(Achievement achievement) {
		// TODO Auto-generated method stub
		return null;
	}

}

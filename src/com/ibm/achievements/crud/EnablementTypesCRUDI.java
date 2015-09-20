package com.ibm.achievements.crud;

import org.json.JSONObject;

import com.ibm.achievements.model.Enablement;
import com.ibm.achievements.model.Mentorship;

public interface EnablementTypesCRUDI {
	public boolean addMentorship(Enablement enablement , JSONObject enablementJson) ; 
	public boolean updateEnablement(JSONObject jsonObject ) ; 
	public String getAchievement(String achievementId);
}



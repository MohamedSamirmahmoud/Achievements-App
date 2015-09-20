package com.ibm.achievements.crud;

import org.json.JSONObject;

import com.ibm.achievements.model.Mentorship;

public interface MentorshipTypesCRUDI {
	public boolean addMentorship(Mentorship mentorship , JSONObject mentorshipJson) ; 
	public boolean updateMentorship(JSONObject jsonObject ) ; 
	public String getAchievement(String achievementId);
}

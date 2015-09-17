package com.ibm.achievements.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class AchievementsApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
      Set<Class<?>> services = new HashSet<Class<?>>() ;
      services.add(AchievementManipulation.class) ;
      services.add(AchievementQuerying.class) ;
      services.add(AchievmentStatus.class) ;
      services.add(Authentication.class) ;
      services.add(AchievementShare.class);
      services.add(EmployeeQuering.class);
	  return services;
	}

}

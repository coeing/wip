import java.util.List;
import java.util.Map;

import models.Guest;

import com.avaje.ebean.Ebean;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;


public class Global extends GlobalSettings {

	  @Override
	  public void onStart(Application app) {
	    Logger.info("Application has started");
	    
	    this.loadInitialData();	    
	  }

	private void loadInitialData() {
		Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");		
		List<Object> guests = all.get("guests");
		if (guests != null &&
				Ebean.find(Guest.class).findRowCount() == 0)
		{
			Logger.info(String.format("Found %d guests", guests.size()));
			
			for	(int i = 0; i < guests.size(); ++i)
			{
				Logger.info(String.format("Found guest: %s", guests.get(i)));
			}
			Ebean.save(guests);			
		}
	}  
}

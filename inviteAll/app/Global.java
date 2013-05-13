import java.util.List;
import java.util.Map;

import models.Guest;
import models.GuestController;

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
		if (guests != null)
		{
			Logger.info(String.format("Found %d guests", guests.size()));
			
			for	(int i = 0; i < guests.size(); ++i)
			{
				Guest guest = (Guest) guests.get(i);
				Logger.info(String.format("Found guest: %s", guest));
				
				if (GuestController.findByName(guest.name, guest.lastName) != null)
				{
					continue;
				}

				// Check code.
				if (guest.code == null)
				{
					guest.code = GuestController.generateUniqueCode();
				}
				
				Ebean.save(guest);
			}
		}
	}  
}

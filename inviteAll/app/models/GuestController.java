package models;

import play.db.ebean.Model.Finder;
import play.mvc.Http.Context;

public class GuestController {

	public static Finder<Integer, Guest> finder = new Finder<Integer, Guest>(Integer.class, Guest.class);
	
	public Guest newGuest(String name, boolean isGroup)
	{
		Guest guest = new Guest();
		guest.name = name;
		guest.isGroup = isGroup;
		guest.save();
		return guest;
	}
	
	public static Guest findByCode(String code)
	{
		return finder.where().eq("code", code).findUnique();
	}

	public static Guest getLoggedInGuest(Context ctx) {
		return findByCode(ctx.session().get("code"));
	}
}

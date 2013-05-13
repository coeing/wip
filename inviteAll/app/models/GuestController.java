package models;

import java.util.List;

import helpers.CodeGenerator;
import play.db.ebean.Model.Finder;
import play.mvc.Http.Context;

public class GuestController {

	private static CodeGenerator generator = new CodeGenerator(10);

	public static Finder<Integer, Guest> finder = new Finder<Integer, Guest>(
			Integer.class, Guest.class);

	public Guest newGuest(String name, boolean isGroup) {
		Guest guest = new Guest();
		guest.name = name;
		guest.isGroup = isGroup;
		guest.save();
		return guest;
	}

	public static Guest findByCode(String code) {
		return finder.where().eq("code", code).findUnique();
	}

	public static Guest getLoggedInGuest(Context ctx) {
		return findByCode(ctx.session().get("code"));
	}

	public static Guest findByName(String firstName, String lastName) {
		return finder.where().eq("name", firstName).eq("last_name", lastName)
				.findUnique();
	}

	public static String generateUniqueCode() {
		String code = generator.nextCode();
		while (findByCode(code) != null) {
			code = generator.nextCode();
		}
		return code;
	}

	public static List<Guest> findGuests() {
		return finder.all();
	}
}

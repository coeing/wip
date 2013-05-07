package controllers;

import models.CodeFormData;
import models.Guest;
import models.GuestController;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	
    	Form<CodeFormData> form = Form.form(CodeFormData.class);
    	
        return ok(index.render(form));
    }
    
    public static Result login() {

    	Form<CodeFormData> form = Form.form(CodeFormData.class).bindFromRequest();
    	if (form.hasErrors())
    	{
    		return badRequest(index.render(form));
    	}
    	
    	CodeFormData data = form.get();
    	
    	// TODO: Login.
    	session().put("code", data.code);
    	
    	return redirect(routes.Application.welcome());
    }
    
    public static Result logout()
    {
    	session().clear();
    	return redirect(routes.Application.index());
    }
    
    public static Result welcome()
    {
    	Guest guest = GuestController.getLoggedInGuest(ctx());
    	if (guest == null)
    	{
    		return badRequest(error.render());
    	}
    	return ok(welcome.render(guest));
    }
    
    public static Result info()
    {
    	Guest guest = GuestController.getLoggedInGuest(ctx());
    	if (guest == null)
    	{
    		return badRequest(error.render());
    	}
    	return ok(info.render(guest));
    }
    
    public static Result decision()
    {
    	Guest guest = GuestController.getLoggedInGuest(ctx());
    	if (guest == null)
    	{
    		return badRequest(error.render());
    	}
    	return ok(decision.render(guest));
    }
    
    public static Result submitDecision()
    {
    	return ok("submitDecision");
    }
}

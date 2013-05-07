package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class CodeFormData {

	public String code;
	
	public Guest guest;
	
	public List<ValidationError> validate()
	{
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		// Check code.
		this.guest = GuestController.findByCode(this.code);
		if (this.guest == null)
		{
			errors.add(new ValidationError("code", "login.errors.invalid_code"));
		}
		
		return errors.isEmpty() ? null: errors;
	}
}

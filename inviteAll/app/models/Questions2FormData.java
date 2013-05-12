package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class Questions2FormData {

	public String bringsFood;

	private Decision bringsFoodValue;

	public String food;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		// Check answers.
		if (this.bringsFood == null) {
			errors.add(new ValidationError("bringsFood",
					"questions.errors.no_bringsFood"));
		} else {
			try {
				this.bringsFoodValue = Decision.valueOf(this.bringsFood);
			} catch (IllegalArgumentException e) {
				if (this.bringsFoodValue == null) {
					errors.add(new ValidationError("bringsFood",
							"error.invalid_value"));
				}
			}

			if (this.bringsFood.equals(Decision.YES.toString())
					&& (this.food == null || this.food.isEmpty())) {
				errors.add(new ValidationError("food",
						"questions.errors.no_food"));
			}
		}

		return errors.isEmpty() ? null : errors;
	}

	public void fill(Guest guest) {
		this.bringsFood = guest.bringsFood != null ? guest.bringsFood
				.toString() : null;
		this.food = guest.food;
	}

	public void update(Guest guest) {
		guest.bringsFood = this.bringsFoodValue;
		guest.food = this.food;
		guest.save();
	}
}

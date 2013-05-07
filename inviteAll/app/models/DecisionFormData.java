package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class DecisionFormData {

	public String decision;

	public Decision decisionValue;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		// Check decision.
		if (this.decision == null) {
			errors.add(new ValidationError("decision",
					"decision.errors.no_decision"));
		} else {
			try {
				this.decisionValue = Decision.valueOf(this.decision);
			} catch (IllegalArgumentException e) {
				if (this.decisionValue == null) {
					errors.add(new ValidationError("decision",
							"decision.errors.invalid_decision"));
				}
			}
		}

		return errors.isEmpty() ? null : errors;
	}
}

package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.ValidationError;

public class QuestionsFormData {

	public String bringsPartner;

	private Decision bringsPartnerValue;

	public String needsBed;

	private Decision needsBedValue;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		// Check answers.
		if (this.bringsPartner == null) {
			errors.add(new ValidationError("bringsPartner",
					"questions.errors.no_bringsPartner"));
		} else {
			try {
				this.bringsPartnerValue = Decision.valueOf(this.bringsPartner);
			} catch (IllegalArgumentException e) {
				if (this.bringsPartnerValue == null) {
					errors.add(new ValidationError("bringsPartner",
							"error.invalid_value"));
				}
			}
		}
		if (this.needsBed == null) {
			errors.add(new ValidationError("needsBed",
					"questions.errors.no_needsBed"));
		} else {
			try {
				this.needsBedValue = Decision.valueOf(this.needsBed);
			} catch (IllegalArgumentException e) {
				if (this.needsBedValue == null) {
					errors.add(new ValidationError("needsBed",
							"error.invalid_value"));
				}
			}
		}

		return errors.isEmpty() ? null : errors;
	}

	public void fill(Guest guest) {
		this.bringsPartner = guest.bringsPartner != null ? guest.bringsPartner
				.toString() : null;
		this.needsBed = guest.needsBed != null ? guest.needsBed.toString()
				: null;
	}

	public void update(Guest guest) {
		guest.bringsPartner = this.bringsPartnerValue;
		guest.needsBed = this.needsBedValue;
		guest.save();
	}
}

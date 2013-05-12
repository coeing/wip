package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import models.CodeFormData;
import models.Decision;
import models.DecisionFormData;
import models.Guest;
import models.GuestController;
import models.Questions2FormData;
import models.QuestionsFormData;
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

		Form<CodeFormData> form = Form.form(CodeFormData.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(index.render(form));
		}

		CodeFormData data = form.get();

		// TODO: Login.
		session().put("code", data.code);

		return redirect(routes.Application.welcome());
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.index());
	}

	public static Result welcome() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}
		return ok(welcome.render(guest));
	}

	public static Result info() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}
		return ok(info.render(guest));
	}

	public static Result decision() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		DecisionFormData data = new DecisionFormData();
		data.decision = guest.decision != null ? guest.decision.toString()
				: null;

		Form<DecisionFormData> form = Form.form(DecisionFormData.class).fill(
				data);

		return ok(decision.render(guest, form));
	}

	public static Result submitDecision() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		Form<DecisionFormData> form = Form.form(DecisionFormData.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(decision.render(guest, form));
		}

		DecisionFormData data = form.get();

		// Store decision value.
		guest.decision = data.decisionValue;
		guest.save();

		switch (data.decisionValue) {
		case YES: {
			return ok(decision_yes.render(guest));
		}
		case NO: {
			return ok(decision_no.render(guest));
		}
		case MAYBE: {
			return ok(decision_maybe.render(guest));
		}
		default:
			return badRequest(error.render());
		}

	}

	public static Result questions() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		// Check that guest answered decision with yes.
		if (guest.decision != Decision.YES) {
			return redirect(routes.Application.decision());
		}

		QuestionsFormData data = new QuestionsFormData();
		data.fill(guest);

		Form<QuestionsFormData> form = Form.form(QuestionsFormData.class).fill(
				data);

		return ok(questions.render(guest, form));
	}

	public static Result questions2() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		// Check that guest answered decision with yes.
		if (guest.decision != Decision.YES) {
			return redirect(routes.Application.decision());
		}

		Questions2FormData data = new Questions2FormData();
		data.fill(guest);

		Form<Questions2FormData> form = Form.form(Questions2FormData.class).fill(
				data);

		return ok(questions2.render(guest, form));
	}

	public static Result submitQuestions() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		Form<QuestionsFormData> form = Form.form(QuestionsFormData.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(questions.render(guest, form));
		}

		QuestionsFormData data = form.get();
		data.update(guest);

		return redirect(routes.Application.questions2());
	}

	public static Result submitQuestions2() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		Form<Questions2FormData> form = Form.form(Questions2FormData.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(questions2.render(guest, form));
		}

		Questions2FormData data = form.get();
		data.update(guest);

		return redirect(routes.Application.summary());
	}

	public static Result summary() {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		// Check that guest answered decision with yes.
		if (guest.decision != Decision.YES) {
			return redirect(routes.Application.decision());
		}

		return ok(summary.render(guest));
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	public static Result reminder(String extension) throws IOException {
		Guest guest = GuestController.getLoggedInGuest(ctx());
		if (guest == null) {
			return badRequest(error.render());
		}

		File reminderFile = Play.application().getFile(
				"public/misc/reminder.ics");
		if (reminderFile == null) {
			return badRequest("Reminder file not found");
		}
		String icsContent = readFile(reminderFile.getPath(),
				Charset.defaultCharset());
		response().setContentType("text/calendar");
		response().setHeader("Content-Disposition",
				"inline; filename=reminder." + extension);
		return ok(icsContent);
	}
}

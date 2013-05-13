package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import play.db.ebean.Model;

@Entity
public class Guest extends Model {

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", code=" + code
				+ ", isGroup=" + isGroup + ", decision=" + decision + "]";
	}

	/**
	 * Unique id.
	 */
	private static final long serialVersionUID = -2116916264603082665L;

	@Id
	public Integer id;

	public boolean isAdmin;

	public String name;

	public String lastName;
	
	public String email;

	@Column(unique = true)
	public String code;

	public boolean isGroup;

	public Decision decision;

	public Decision bringsPartner;

	public Decision needsBed;

	public Decision bringsFood;

	@Size(max = 255)
	public String food;

	public boolean gotMail;
}

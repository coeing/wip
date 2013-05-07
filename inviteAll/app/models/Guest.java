package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	public String name;
	
	@Column(unique=true)
	public String code;
	
	public boolean isGroup;
	
	public Decision decision;
}

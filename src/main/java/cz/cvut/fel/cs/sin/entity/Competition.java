package cz.cvut.fel.cs.sin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Competition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Size(min = 1, max = 25)
	private String name;

	@NotNull
	@ManyToOne
	private Person president;

	public void setId(long id) {
		this.id = id;
	}

	public Person getPresident() {
		return president;
	}

	public void setPresident(Person president) {
		this.president = president;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getLeader() {
		return leader;
	}

	public void setLeader(Person leader) {
		this.leader = leader;
	}

	@NotNull
	@ManyToOne
	private Person leader;
}

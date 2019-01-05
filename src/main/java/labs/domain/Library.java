package labs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "findLibraryByName", query = "select b from Library b where b.name = :name")})
@Table(name = "LIBRARY")
public class Library implements Serializable {

	private long id;
	private String name;
	private String address;
	private List<Book> ownedBooks;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "OWNED_BOOKS")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "library")
	public List<Book> getOwnedBooks() {
		return ownedBooks;
	}

	public void setOwnedBooks(List<Book> ownedBooks) {
		this.ownedBooks = ownedBooks;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass() && id != -1)
				? id == (((Library) other).getId())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return (int) id;
	}
}

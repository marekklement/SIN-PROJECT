package labs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "findAuthorByName", query = "select b from Publisher b where b.name = :name")})
@Table(name = "PUBLISHER")
public class Publisher implements Serializable {

	private long id;
	private List<Book> booksPublished;
	private List<Author> authorsSigned;
	private String name;
	private String address;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToMany(mappedBy = "publishers")
	public List<Book> getBooksPublished() {
		return booksPublished;
	}

	public void setBooksPublished(List<Book> booksPublished) {
		this.booksPublished = booksPublished;
	}

	@ManyToMany(mappedBy = "publishers")
	public List<Author> getAuthorsSigned() {
		return authorsSigned;
	}

	public void setAuthorsSigned(List<Author> authorsSigned) {
		this.authorsSigned = authorsSigned;
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

	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass() && id != -1)
				? id == (((Publisher) other).getId())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return (int) id;
	}
}

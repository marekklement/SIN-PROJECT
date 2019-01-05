package labs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAuthorByFirstName", query = "select b from Author b where b.firstName = :name"),
		@NamedQuery(name = "findAuthorBySurname", query = "select b from Author b where b.surname = :name"),
		@NamedQuery(name = "findAuthorByFullName", query = "select b from Author b where b.surname = :surname and b.firstName = :firstname")
})
@Table(name = "AUTHOR")
public class Author implements Serializable {

	private long id = -1;
	private String email;
	private String firstName;
	private String surname;
	private List<Publisher> publishers;
	private List<Book> books;

	@Column(name = "BOOKS")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@ManyToMany
	@JoinTable(name = "AUTHORS_PUBLISHERS", joinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "ID"))
	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FIRST_NAME")
	@NotNull
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "SURNAME")
	@NotNull
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass() && id != -1)
				? id == (((Author) other).getId())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return (int) id;
	}
}

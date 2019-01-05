package labs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "findBookByName", query = "select b from Book b where b.name = :name"), @NamedQuery(name = "findBookByGenre", query = "select b from Book b where b.genre = :name")})
@Table(name = "BOOK")
public class Book implements Serializable {


	private String name;
	private LocalDate publicationDate;
	private String isbn;
	private String genre;
	private Author author;
	private Library library;
	private List<Publisher> publishers;

	@ManyToMany
	@JoinTable(name = "BOOKS_PUBLISHERS",
			joinColumns = 	@JoinColumn(name = "BOOK_ID", referencedColumnName = "ISBN"),
			inverseJoinColumns = @JoinColumn(name = "PUBLISHER_ID", referencedColumnName = "ID")
	)
	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIBRARY_ID")
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
	@NotNull
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Column(name = "NAME")
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PUBLICATION_DATE")
	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Id
	@Column(name = "ISBN")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "GENRE")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean equals(Object other) {
		return (other != null && getClass() == other.getClass() && isbn != null)
				? isbn.equals(((Book) other).getIsbn())
				: (other == this);
	}

	@Override
	public int hashCode() {
		return isbn.hashCode();
	}
}

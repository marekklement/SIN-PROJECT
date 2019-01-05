package labs.service;

public interface AddBookToLibraryService {

	boolean addBookToLibrary(long libraryUUID, String isbn);
}

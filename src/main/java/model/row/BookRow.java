package model.row;

import model.Book;

public class BookRow extends BaseRow<Book> {

    @ModelInfo(name = "Author")
    private String author;
    @ModelInfo(name = "Title")
    private String title;
    @ModelInfo(name = "Year")
    private String year;

    @Override
    public Book asModel() {
        return new Book(author, title, Integer.parseInt(year), null);
    }
}

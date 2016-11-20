
public class Book {

    private int id;
    private String book;
    private String author;
    private String gengre;
    private String cover;
    private int stock;
    private int price;

    public Book(int id, String book, String author, String gengre, String cover, int stock, int price) {
        this.id = id;
        this.book = book;
        this.author = author;
        this.gengre = gengre;
        this.cover = cover;
        this.stock = stock;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getGengre() {
        return gengre;
    }

    public String getCover() {
        return cover;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }
}

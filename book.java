public class book {
    String Title;
    String author;
    double price;

    book(String Title, String author, double price) {
        this.Title = Title;
        this.author = author;
        this.price = price;
    }

    void display() {
        System.out.println("Title: " + Title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }

    public static void main(String[] args) {
        book book1 = new book("Book Name", "Author Name", 500.50);
        book1.display();
    }
}

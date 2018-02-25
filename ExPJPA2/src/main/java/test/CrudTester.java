package test;

import entity.Book;
import entity.Ebook;
import entity.PaperBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author BenedikteEva
 */
public class CrudTester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExPJPA2PU");
    EntityManager em = emf.createEntityManager();

// Create: persist nonspecified books, ebooks and paperbooks to database Could've been more elegant
    public void addBook(Long isbn, String title, String author, double price, String Btype) {
        em.getTransaction().begin();
        Book b = new Ebook();
        b.setISBN(isbn);
        b.setTitle(title);
        b.setAuthor(author);
        b.setPrice(price);
        em.persist(b);
        em.getTransaction().commit();
    }

    public void addEbook(Long isbn, String title, String author, double price, String url, double sizeMB) {
        em.getTransaction().begin();
        Book e = new Ebook();
        e.setISBN(isbn);
        e.setTitle(title);
        e.setAuthor(author);
        e.setDownLoadURl(url);
        e.setPrice(price);
        e.setSizeMB(sizeMB);
        em.persist(e);
        em.getTransaction().commit();

    }

    public void addPbook(Long isbn, String title, String author, double price, int inStock, double shippingWeight) {
        em.getTransaction().begin();
        PaperBook p = new PaperBook();
        p.setISBN(isbn);
        p.setTitle(title);
        p.setAuthor(author);
        p.setPrice(price);
        p.setInStock(inStock);
        p.setShippingWeight(shippingWeight);
        em.persist(p);
        em.getTransaction().commit();

    }

// delete if we just delete a book it should cascade to the ebook or pbook entries Polymorph example
    public void removeBookByIsbn(long isbn) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Book b WHERE b.ISBN=" + isbn);
        query.executeUpdate();
        em.getTransaction().commit();
    }
//Update (price is probably reasonable here
//;
//Student student = entityManager.find(Student.class,251);B
//student.setLevel("L");
//entityManager.persist(student);

    public void updatePriceByIsbn(double price, long isbn) {
        em.getTransaction().begin();
        Book b = em.find(Book.class, isbn);
        b.setPrice(price);
        em.persist(b);
        em.getTransaction().commit();
    }

    public List<Book> fetchAllBooks() {

        Query q = em.createQuery("select b  from Book b");
        List<Book> books = q.getResultList();
        for (int i = 0; i < books.size(); i++) {
            System.out.println("Title " + (i + 1) + ": " + books.get(i).getTitle()+"  type: "
                    +books.get(i).getClass().getTypeName());
        }
        return books;

    }

    // made this mathod to get only ebooks
    public List<Book> fetchAllEBooks() {

        Query q = em.createQuery("select b from Ebook b ");
        List<Book> ebooks = q.getResultList();
        for (int i = 0; i < ebooks.size(); i++) {
            System.out.println("Title " + (i + 1) + ": " + ebooks.get(i).getTitle());
        }
        return ebooks;

    }
    
    
    public Book getBookByTitle(String title){
        Query q= em.createQuery("select b from Book b where b.title="+title);
        int bookId = q.getFirstResult();
        
        Book b = fetchAllBooks().get(q.getFirstResult());
        return b;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExPJPA2PU");
        EntityManager em = emf.createEntityManager();
        CrudTester ct = new CrudTester();
        try {

            ct.addEbook(9788799516520L, "Book of Magic in Practice", "BenedikteEva", 49.95, "https://www.saxo.com/dk/book-of-magic-in-practice_pdf_9788799516520", 1.75);
            ct.addEbook(9788792562623L, "Life of a Magician", "BenedikteEva", 39.95, "https://www.saxo.com/dk/life-of-a-magician-with-magical-contact-lenses_pdf_9788792562623", 1.40);
            ct.addEbook(9788740461626L, "Considerations of Magic - Philosophical, Personal and Historical ", "BenedikteEva", 10.00, "https://www.saxo.com/dk/considerations-of-magic-philosophical-personal-and-historical_pdf_9788740461626", 1.29);
            ct.addPbook(9781492819523L, "Considerations of Magic - Philosophical, Personal and Historical ", "BenedikteEva", 49.95, 9, 127.56);
            ct.updatePriceByIsbn(59.95, 9788799516520L);
            
            System.out.println("The next is to demonstrate polymorphism and cascade delete\n"
                    + "Please notice that the book from the fetchAllBooks list is removed from both\n"
                    + "the book list and the ebook list because ebook is a subclass of book");
            ct.fetchAllBooks();
            ct.fetchAllEBooks();
            ct.removeBookByIsbn(9788740461626L);
            ct.fetchAllBooks();
            ct.fetchAllEBooks();
            //adding the removed book back
            ct.addEbook(9788740461626L, "Considerations of Magic - Philosophical, Personal and Historical ", "BenedikteEva", 10.00, "https://www.saxo.com/dk/considerations-of-magic-philosophical-personal-and-historical_pdf_9788740461626", 1.29);
            
            System.out.println(ct.getBookByTitle("'Book of Magic in Practice'"));
        } finally {
            em.close();
        }
    }
}

package pl.whuzar;

import jakarta.persistence.*;

@Entity(name = "Book")
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(name = "sequence_book", sequenceName = "sequence_book", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_book")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "book", nullable = false, columnDefinition = "TEXT")
    private String BookName;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programmer_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "programmer_id_fk"))
    private Programmer programmer;

    public Book(String bookName, Programmer programmer) {
        BookName = bookName;
        this.programmer = programmer;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", BookName='" + BookName + '\'' +
                ", programmer=" + programmer +
                '}';
    }
}
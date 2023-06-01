package pl.whuzar;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Programmer")
@Table(name = "programmer", uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")})
public class Programmer {

    @Id
    @SequenceGenerator(name = "sequence_programmer", sequenceName = "sequence_programmer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_programmer")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "programmer", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ProgrammerJob programmerJob;

    @OneToMany(mappedBy = "programmer", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Company> companys = new ArrayList<>();

    @OneToMany(mappedBy = "programmer", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "programmer", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Votes> votes = new ArrayList<>();

    public Programmer(String first_name, String last_name, String email, Integer age) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.age = age;
    }

    public Programmer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProgrammerJob getProgrammerJob() {
        return programmerJob;
    }

    public void setProgrammerJob(ProgrammerJob programmerJob) {
        this.programmerJob = programmerJob;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> company) {
        this.companys = company;
    }

    public void addBook(Book book){
        if(!books.contains(book)){
            books.add(book);
            book.setProgrammer(this);
        }
    }

    public void removeBook(Book book){
        if(books.contains(book)){
            books.remove(book);
            book.setProgrammer(this);
        }
    }

    public void addCompany(Company company){
        if(!companys.contains(company)){
            companys.add(company);
            company.setProgrammer(this);
        }
    }

    public void removeCompany(Company company){
        if(companys.contains(company)){
            companys.remove(company);
            company.setProgrammer(this);
        }
    }

    public List<Votes> getVotes() {
        return votes;
    }

    public void addVote(Votes vote){
        if(!votes.contains(vote)){
            votes.add(vote);
        }
    }

    public void removeVote(Votes vote){
        votes.remove(vote);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
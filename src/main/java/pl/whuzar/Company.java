package pl.whuzar;

import jakarta.persistence.*;

@Entity(name = "Company")
@Table(name = "company", uniqueConstraints = { @UniqueConstraint(name = "programmer_unique", columnNames = "programmer_id") })
public class Company {

    @Id
    @SequenceGenerator(name = "sequence_company", sequenceName = "sequence_company")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_company")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programmer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "company_fk"))
    private Programmer programmer;

    public Company(String name, String address, Programmer programmer) {
        this.name = name;
        this.address = address;
        this.programmer = programmer;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

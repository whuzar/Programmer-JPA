package pl.whuzar;

import jakarta.persistence.*;

@Entity(name = "ProgrammerJob")
@Table(name = "programmer_job")
public class ProgrammerJob {

    @Id
    @SequenceGenerator(name = "sequence_programmer_job", sequenceName = "sequence_programmer_job", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_programmer_job")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "earnings", nullable = false)
    private Double earnings;

    @Column(name = "experience", nullable = false)
    private String experience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programmer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "programmer_id_fk"))
    private Programmer programmer;

    public ProgrammerJob(Double earnings, String experience, Programmer programmer) {
        this.earnings = earnings;
        this.experience = experience;
        this.programmer = programmer;
    }

    public ProgrammerJob() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }


    @Override
    public String toString() {
        return "ProgrammerJob{" +
                "id=" + id +
                ", earnings=" + earnings +
                ", experience='" + experience + '\'' +
                ", programmer=" + programmer +
                '}';
    }
}
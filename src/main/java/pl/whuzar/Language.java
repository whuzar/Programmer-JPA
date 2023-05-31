package pl.whuzar;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Language")
@Table(name = "language")
public class Language {

    @Id
    @SequenceGenerator(name = "sequence_language", sequenceName = "sequence_language", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_language")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name_language", nullable = false, columnDefinition = "TEXT")
    private String nameLanguage;

    @OneToMany(mappedBy = "language", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Votes> votes = new ArrayList<>();

    public Language(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public Language() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }

    public void setNameLanguage(String nameLanguage) {
        this.nameLanguage = nameLanguage;
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
        return "Language{" +
                "id=" + id +
                ", nameLanguage='" + nameLanguage + '\'' +
                ", votes=" + votes +
                '}';
    }
}

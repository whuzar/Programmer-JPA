package pl.whuzar;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Votes")
@Table(name = "votes")
public class Votes {

    @EmbeddedId
    private VotesId id;

    @ManyToOne
    @MapsId("programmerId")
    @JoinColumn(name = "programmer_id", foreignKey = @ForeignKey(name = "vote_programmer_fk"))
    private Programmer programmer;

    @ManyToOne
    @MapsId("languageId")
    @JoinColumn(name = "language_id", foreignKey = @ForeignKey(name = "vote_language_fk"))
    private Language language;

    @Column(name = "voted_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime votedAt;

    public Votes(VotesId id, Programmer programmer, Language language, LocalDateTime votedAt) {
        this.id = id;
        this.programmer = programmer;
        this.language = language;
        this.votedAt = votedAt;
    }

    public Votes(Programmer programmer, Language language, LocalDateTime votedAt) {
        this.programmer = programmer;
        this.language = language;
        this.votedAt = votedAt;
    }

    public Votes() {
    }

    public VotesId getId() {
        return id;
    }

    public void setId(VotesId id) {
        this.id = id;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }
}

package pl.whuzar;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VotesId implements Serializable {

    @Column(name = "programmer_id")
    private Long programmerId;

    @Column(name = "language_id")
    private Long languageId;

    public VotesId(Long programmerId, Long languageId) {
        this.programmerId = programmerId;
        this.languageId = languageId;
    }

    public VotesId() {
    }

    public Long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(Long programmerId) {
        this.programmerId = programmerId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long votesId) {
        this.languageId = votesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotesId votesId1 = (VotesId) o;
        return Objects.equals(programmerId, votesId1.programmerId) && Objects.equals(languageId, votesId1.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmerId, languageId);
    }
}

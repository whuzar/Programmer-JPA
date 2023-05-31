package pl.whuzar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {

    @Query(value = "SELECT p FROM Programmer p JOIN ProgrammerJob pj ON p.id = pj.programmer.id WHERE pj.experience = ?1")
    List<Programmer> takeOnlyExperienceProgrammer(String experience);

    @Query(value = "SELECT programmer.id, programmer.first_name, l.name_language, v.voted_at " +
            "FROM programmer JOIN votes v on programmer.id = v.programmer_id JOIN language l on l.id = v.language_id",
            nativeQuery = true)
    List<Object[]> showVotedLanguage();

    @Query(value = "SELECT p FROM Programmer p WHERE p.id = ?1")
    Optional<Programmer> showDeveloperById(Long id);

}

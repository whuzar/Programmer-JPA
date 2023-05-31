package pl.whuzar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(value = "SELECT name_language, COUNT(name_language) FROM language GROUP BY name_language ORDER BY COUNT(name_language) DESC", nativeQuery = true)
    List<Object[]> showTableOfTopLanguages();
}

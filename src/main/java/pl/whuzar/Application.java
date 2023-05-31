package pl.whuzar;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProgrammerRepository programmerRepository, LanguageRepository languageRepository){
        return args -> {

            addDeveloper(programmerRepository);

            //search developers by experience
            programmerRepository.takeOnlyExperienceProgrammer("Intern").forEach(System.out::println);

            //show the most used languages
            List<Object[]> resultsTableOf = languageRepository.showTableOfTopLanguages();

            for (Object[] result : resultsTableOf) {
                Map<String, Object> topLanguageMap = new LinkedHashMap<>();

                topLanguageMap.put("language_name", result[0]);
                topLanguageMap.put("count", result[1]);

                System.out.println(topLanguageMap);
            }

            //show developer when he voted
            List<Object[]> results = programmerRepository.showVotedLanguage();

            for (Object[] result : results) {
                Map<String, Object> votedLanguage = new LinkedHashMap<>();

                votedLanguage.put("id", result[0]);
                votedLanguage.put("first_name", result[1]);
                votedLanguage.put("name_language", result[2]);
                votedLanguage.put("voted_at", result[3]);

                System.out.println(votedLanguage);
            }

            //show developer if present
            programmerRepository.showDeveloperById(1L).ifPresent(System.out::println);

        };
    }

    private void addDeveloper(ProgrammerRepository programmerRepository){
        //developer
        Programmer programmer = new Programmer("Wojciech", "Huzar", "wojtekhuzar@gmail.com", 20);
        Programmer programmer2 = new Programmer("Karol", "Malo", "karolmalo@gmail.com", 35);
        Programmer programmer3 = new Programmer("Michał", "Polak", "michalpolak@gmail.com", 42);
        Programmer programmer4 = new Programmer("Tomasz", "Kuzik", "tomaszkuzik@gmail.com", 27);

        //information about developer
        programmer.setProgrammerJob(new ProgrammerJob(4000.00, "Intern", programmer));

        //developer's company
        programmer.addCompany(new Company("COI", "Aleje Jerozolimskie 132-136, 02-305 Warszawa", programmer));

        //add the book to the developer
        programmer.addBook(new Book("Clean code", programmer));
        programmer.addBook(new Book("Spring Data JPA", programmer));
        programmer.addBook(new Book("Introduction to Algorithms", programmer));

        //developer languages
        programmer.addVote(new Votes(new VotesId(1L, 1L), programmer, new Language("Java"), LocalDateTime.now().minusDays(18)));
        programmer.addVote(new Votes(new VotesId(1L, 2L), programmer, new Language("Python"), LocalDateTime.now().minusWeeks(16)));

        programmer2.addVote(new Votes(new VotesId(2L, 1L), programmer2, new Language("Java"), LocalDateTime.now().minusWeeks(6)));
        programmer3.addVote(new Votes(new VotesId(3L, 1L), programmer3, new Language("Java"), LocalDateTime.now().minusWeeks(2)));
        programmer4.addVote(new Votes(new VotesId(4L, 1L), programmer4, new Language("Java"), LocalDateTime.now().minusWeeks(7)));

        programmerRepository.saveAll(List.of(programmer, programmer2, programmer3, programmer4));
    }
}
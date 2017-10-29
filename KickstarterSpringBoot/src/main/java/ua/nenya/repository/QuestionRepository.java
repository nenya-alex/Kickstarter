package ua.nenya.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

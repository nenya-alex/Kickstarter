package ua.nenya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository <Answer, Long>{
    List<Answer> getAllByQuestionId(Long questionId);
}

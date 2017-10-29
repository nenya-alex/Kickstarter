package ua.nenya.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Answer;
import ua.nenya.domain.Question;
import ua.nenya.dto.AnswerDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface AnswerMapper {

    @Mapping(target = "questionId", source = "question.id")
    AnswerDTO answerToAnswerDTO(Answer answer);

    List<AnswerDTO> answersToAnswerDTOs(List<Answer> answers);

    @Mapping(target = "question", source = "questionId")
    Answer answerDtoToAnswer(AnswerDTO answerDTO);

    default Question questionFromId(Long questionId) {
        if (questionId == null) {
            return null;
        }
        Question question = new Question();
        question.setId(questionId);
        return question;
    }
}

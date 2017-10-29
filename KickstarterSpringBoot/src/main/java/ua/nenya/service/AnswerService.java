package ua.nenya.service;

import ua.nenya.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    List<AnswerDTO> getQuestionAnswers(Long questionId);

    AnswerDTO saveAnswer(AnswerDTO answerDTO);

}

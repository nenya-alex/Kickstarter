package ua.nenya.service;

import ua.nenya.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getProjectQuestions(Long projectId);

    QuestionDTO saveQuestion(QuestionDTO questionDTO);

}

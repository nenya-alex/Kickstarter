package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Answer;
import ua.nenya.dto.AnswerDTO;
import ua.nenya.mapper.AnswerMapper;
import ua.nenya.repository.AnswerRepository;
import ua.nenya.service.AnswerService;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerRepository answerRepository,
                             AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> getQuestionAnswers(Long questionId) {
        log.debug("Request to get all Answers of Question with id:" + questionId);
        List<Answer> answers = answerRepository.getAllByQuestionId(questionId);

        return answerMapper.answersToAnswerDTOs(answers);
    }

    @Override
    public AnswerDTO saveAnswer(AnswerDTO answerDTO) {
        log.debug("Request to create Answer:" + answerDTO);
        Answer answer = answerMapper.answerDtoToAnswer(answerDTO);
        Answer result = answerRepository.saveAndFlush(answer);
        return answerMapper.answerToAnswerDTO(result);
    }
}

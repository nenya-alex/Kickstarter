package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Project;
import ua.nenya.domain.Question;
import ua.nenya.dto.QuestionDTO;
import ua.nenya.exceptions.ProjectException;
import ua.nenya.mapper.QuestionMapper;
import ua.nenya.repository.ProjectRepository;
import ua.nenya.repository.QuestionRepository;
import ua.nenya.service.QuestionService;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    private final ProjectRepository projectRepository;

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               ProjectRepository projectRepository,
                               QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.projectRepository = projectRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getProjectQuestions(Long projectId) {
        log.debug("Request to get all Question of Project with id:" + projectId);
        Project project = projectRepository.findOne(projectId);
        if (project == null){
            throw new ProjectException("Project does not exist, id:" + project);
        }
        return questionMapper.questionsToQuestionDTOs(project.getQuestions());
    }

    @Override
    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        log.debug("Request to saveCategory Question:" + questionDTO);
        Question question = questionMapper.questionDtoToQuestion(questionDTO);
        Question result = questionRepository.saveAndFlush(question);
        return questionMapper.questionToQuestionDTO(result);
    }

}

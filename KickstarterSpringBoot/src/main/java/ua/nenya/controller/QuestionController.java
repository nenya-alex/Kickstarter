package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nenya.dto.QuestionDTO;
import ua.nenya.exceptions.QuestionException;
import ua.nenya.service.QuestionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/questions/{projectId}")
    public List<QuestionDTO> getQuestionsByProjectId(@PathVariable("projectId") Long projectId) {
        log.debug("REST request to get all Question of Project with id:" + projectId);
        return questionService.getProjectQuestions(projectId);
    }

    @PostMapping("/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        log.debug("REST request to create Question:" + questionDTO);
        if (questionDTO.getId() != null){
            throw new QuestionException("Question with not null id cannot be created, id: " + questionDTO.getId());
        }
        QuestionDTO result = questionService.saveQuestion(questionDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/questions")
    public ResponseEntity<QuestionDTO> updateQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        log.debug("REST request to update Question:" + questionDTO);
        if (questionDTO.getId() == null){
            createQuestion(questionDTO);
        }
        QuestionDTO result = questionService.saveQuestion(questionDTO);
        return ResponseEntity.ok().body(result);
    }

}

package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nenya.dto.AnswerDTO;
import ua.nenya.dto.QuestionDTO;
import ua.nenya.exceptions.AnswerException;
import ua.nenya.exceptions.QuestionException;
import ua.nenya.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController {

    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/answers/{questionId}")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable("questionId") Long questionId) {
        log.debug("REST request to get all Answers of Question with id:" + questionId);
        return answerService.getQuestionAnswers(questionId);
    }

    @PostMapping("/answers")
    public ResponseEntity<AnswerDTO> createAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        log.debug("REST request to create Answer:" + answerDTO);
        if (answerDTO.getId() != null){
            throw new AnswerException("Answer with not null id cannot be created, id: " + answerDTO.getId());
        }
        AnswerDTO result = answerService.saveAnswer(answerDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/answers")
    public ResponseEntity<AnswerDTO> updateQuestion(@Valid @RequestBody AnswerDTO answerDTO) {
        log.debug("REST request to update Answer:" + answerDTO);
        if (answerDTO.getId() == null){
            createAnswer(answerDTO);
        }
        AnswerDTO result = answerService.saveAnswer(answerDTO);
        return ResponseEntity.ok().body(result);
    }

}

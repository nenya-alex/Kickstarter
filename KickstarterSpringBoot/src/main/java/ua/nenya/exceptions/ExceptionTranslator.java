package ua.nenya.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(QuoteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processQuoteException(QuoteException e) {
        return new ErrorVM(e.getMessage());
    }

    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processCategoryException(CategoryException e) {
        return new ErrorVM(e.getMessage());
    }

    @ExceptionHandler(ProjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processProjectException(ProjectException e) {
        return new ErrorVM(e.getMessage());
    }

    @ExceptionHandler(RewardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processRewardException(RewardException e) {
        return new ErrorVM(e.getMessage());
    }

    @ExceptionHandler(QuestionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processQuestionException(QuestionException e) {
        return new ErrorVM(e.getMessage());
    }

    @ExceptionHandler(AnswerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processAnswerException(AnswerException e) {
        return new ErrorVM(e.getMessage());
    }

}

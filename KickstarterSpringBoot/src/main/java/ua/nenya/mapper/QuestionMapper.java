package ua.nenya.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Project;
import ua.nenya.domain.Question;
import ua.nenya.dto.QuestionDTO;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper {

    @Mapping(target = "projectId", source = "project.id")
    QuestionDTO questionToQuestionDTO(Question question);

    List<QuestionDTO> questionsToQuestionDTOs(Set<Question> questions);

    @Mapping(target = "project", source = "projectId")
    Question questionDtoToQuestion(QuestionDTO questionDTO);

    default Project projectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}

package ua.nenya.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Project;
import ua.nenya.domain.Reward;
import ua.nenya.dto.RewardDTO;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface RewardMapper {

    @Mapping(target = "projectId", source = "project.id")
    RewardDTO rewardToRewardDTO(Reward reward);

    List<RewardDTO> rewardsToRewardDTOs(Set<Reward> rewards);

    @Mapping(target = "project", source = "projectId")
    Reward rewardDtoToReward(RewardDTO rewardsDTO);

    default Project projectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}

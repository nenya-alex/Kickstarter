package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nenya.dto.RewardDTO;
import ua.nenya.service.RewardService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RewardController {

    private static final Logger log = LoggerFactory.getLogger(RewardController.class);

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/rewards/{rewardId}")
    public ResponseEntity<RewardDTO> getReward(@PathVariable("rewardId") Long rewardId) {
        log.debug("REST request to get Reward with id:"+rewardId);
        return ResponseEntity.ok().body(rewardService.getReward(rewardId));
    }

    @GetMapping("/rewards-of-project/{projectId}")
    public List<RewardDTO> getProjectRewards(@PathVariable("projectId") Long projectId) {
        log.debug("REST request to get all Rewards of Project with id:"+projectId);
        return rewardService.getProjectRewards(projectId);
    }
}

package live.switchly.api.controller;

import jakarta.validation.Valid;
import live.switchly.api.dto.CreateFlagRequest;
import live.switchly.api.model.Flag;
import live.switchly.api.service.FlagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class FlagController {

    private final FlagService featureFlagService;

    public FlagController(FlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @PostMapping("/projects/{projectId}/flags")
    @ResponseStatus(HttpStatus.CREATED)
    public Flag create(
            @PathVariable UUID projectId,
            @Valid @RequestBody CreateFlagRequest request) {

        return featureFlagService.create(projectId, request.key(), request.description());
    }

    @GetMapping("/projects/{projectId}/flags")
    public List<Flag> getByProject(@PathVariable UUID projectId) {
        return featureFlagService.getByProjectId(projectId);
    }

    @GetMapping("/projects/{projectId}/flags/{flagId}")
    public Flag getById(@PathVariable UUID flagId) {
        return featureFlagService.getById(flagId);
    }

    @PutMapping("/projects/{projectId}/flags/{flagId}")
    public Flag setEnabled(
            @PathVariable UUID flagId,
            @RequestParam boolean enabled) {

        return featureFlagService.setEnabled(flagId, enabled);
    }
    @DeleteMapping("/flags/{flagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID flagId) {
        featureFlagService.delete(flagId);
    }
}

package live.switchly.api.controller;

import jakarta.validation.Valid;
import live.switchly.api.dto.CreateProjectRequest;
import live.switchly.api.model.Project;
import live.switchly.api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orgs/{orgId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(
            @PathVariable UUID orgId,
            @Valid @RequestBody CreateProjectRequest request) {

        if (!orgId.equals(request.organizationId())) {
            throw new IllegalArgumentException(
                    "Path orgId must match body organizationId");
        }

        return projectService.create(orgId, request.name());
    }

    @GetMapping
    public List<Project> getByOrganization(@PathVariable UUID orgId) {
        return projectService.getByOrganizationId(orgId);
    }

    @GetMapping("/{projectId}")
    public Project getById(@PathVariable UUID projectId) {
        return projectService.getById(projectId);
    }
}

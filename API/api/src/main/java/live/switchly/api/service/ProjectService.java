package live.switchly.api.service;

import live.switchly.api.exception.NotFoundException;
import live.switchly.api.model.Project;
import live.switchly.api.repository.OrganizationRepository;
import live.switchly.api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            OrganizationRepository organizationRepository) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
    }

    public Project create(UUID organizationId, String name) {
        organizationRepository.findById(organizationId)
                .orElseThrow(() ->
                        new NotFoundException("Organization " + organizationId + " not found"));

        Project project = new Project(
                UUID.randomUUID(),
                organizationId,
                name
        );

        return projectRepository.save(project);
    }

    public Project getById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Project " + id + " not found"));
    }

    public List<Project> getByOrganizationId(UUID organizationId) {
        organizationRepository.findById(organizationId)
                .orElseThrow(() ->
                        new NotFoundException("Organization " + organizationId + " not found"));

        return projectRepository.findByOrganizationId(organizationId);
    }
}

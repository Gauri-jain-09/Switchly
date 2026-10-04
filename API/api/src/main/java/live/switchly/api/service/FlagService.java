package live.switchly.api.service;

import live.switchly.api.exception.NotFoundException;
import live.switchly.api.model.Flag;
import live.switchly.api.repository.FlagRepository;
import live.switchly.api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlagService {

    private final FlagRepository featureFlagRepository;
    private final ProjectRepository projectRepository;

    public FlagService(
            FlagRepository featureFlagRepository,
            ProjectRepository projectRepository) {
        this.featureFlagRepository = featureFlagRepository;
        this.projectRepository = projectRepository;
    }

    public Flag create(UUID projectId, String key, String description) {

        projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException("Project " + projectId + " not found"));

        featureFlagRepository.findByProjectIdAndKey(projectId, key)
                .ifPresent(existing -> {
                    throw new IllegalArgumentException(
                            "Feature flag with key '" + key + "' already exists");
                });

        Flag flag = new Flag(
                UUID.randomUUID(),
                projectId,
                key,
                description,
                false
        );

        return featureFlagRepository.save(flag);
    }

    public Flag getById(UUID id) {
        return featureFlagRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Feature flag " + id + " not found"));
    }

    public List<Flag> getByProjectId(UUID projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException("Project " + projectId + " not found"));

        return featureFlagRepository.findByProjectId(projectId);
    }

    public Flag setEnabled(UUID id, boolean enabled) {
        Flag flag = getById(id);
        flag.setEnabled(enabled);
        return featureFlagRepository.save(flag);
    }

    public void delete(UUID id) {
        Flag flag = getById(id);
        featureFlagRepository.deleteById(id);
    }
}
package live.switchly.api.model;

import java.util.UUID;

public class Flag {

    private final UUID id;
    private final UUID projectId;
    private final String key;

    private final String description;

    private boolean enabled;

    public Flag(UUID id, UUID projectId, String key, String description, boolean enabled) {
        this.id = id;
        this.projectId = projectId;
        this.key = key;

        this.description = description;

        this.enabled = enabled;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
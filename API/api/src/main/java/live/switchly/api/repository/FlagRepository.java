package live.switchly.api.repository;

import live.switchly.api.model.Flag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlagRepository {

    Flag save(Flag flag);

    Optional<Flag> findById(UUID id);
    void deleteById(UUID id);

    Optional<Flag> findByProjectIdAndKey(UUID projectId, String key);

    List<Flag> findByProjectId(UUID projectId);
}

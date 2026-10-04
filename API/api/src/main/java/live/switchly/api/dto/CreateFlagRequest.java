package live.switchly.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateFlagRequest(
        @NotBlank String key,
        String description
) {}
package pe.edu.coworkers.reviewservice.domain.model.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tenant {
    private Long id;
    private String occupation;
}

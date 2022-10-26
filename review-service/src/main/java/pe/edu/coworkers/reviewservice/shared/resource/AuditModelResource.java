package pe.edu.coworkers.reviewservice.shared.resource;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class AuditModelResource {
    public Date createdAt;
    public Date updatedAt;
}
package pe.edu.coworkers.reviewservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;

@FeignClient(name = "publication-service", decode404 = true, fallback = PublicationHystrixFallbackFactory.class)
public interface PublicationClient {
    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication);
    @GetMapping(value = "/api/v1/publications/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id);

    @PutMapping(value = "/api/v1/publications/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication);
}

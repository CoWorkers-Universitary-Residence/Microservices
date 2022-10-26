package pe.edu.coworkers.reviewservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;

import java.util.List;

@FeignClient(name = "publication-service")
@RequestMapping(value = "/api/v1/publications")
public interface PublicationClient {

    @GetMapping
    public ResponseEntity<List<Publication>> getPublications();

    @GetMapping(value = "/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id);
}

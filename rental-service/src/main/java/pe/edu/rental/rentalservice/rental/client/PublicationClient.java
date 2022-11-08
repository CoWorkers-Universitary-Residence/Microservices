package pe.edu.rental.rentalservice.rental.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.rental.rentalservice.rental.model.Publication;

@FeignClient(name = "publication-service", decode404 = true/*, fallback = PublicationHystrixFallbackFactory.class*/)
public interface PublicationClient {

    @GetMapping(path = "/api/v1/publications/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id);

    @PutMapping(path = "/api/v1/publications/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication);
}

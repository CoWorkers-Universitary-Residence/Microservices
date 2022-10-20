package pe.edu.coworkers.publicationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.services.PublicationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public ResponseEntity<List<Publication>> getPublications(){
        List<Publication> publications;
        publications = publicationService.getAll();
        if (publications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(publications);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id){
        Publication publication = publicationService.getById(id);
        if (publication == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publication);
    }

    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication){
        Publication publicationCreate = publicationService.create(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") Long id, @RequestBody Publication publication){
        publication.setId(id);
        Publication publication1 = publicationService.update(publication);
        if (publication1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publication1);
    }

    @DeleteMapping("{publicationId}")
    public Optional<ResponseEntity<?>> deletePublication(@PathVariable Long publicationId){
        return publicationService.delete(publicationId);
    }
}

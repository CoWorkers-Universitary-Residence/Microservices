package pe.edu.coworkers.publicationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.coworkers.publicationservice.entities.Detail;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.services.DetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Detail", description = "CRUD details")
@RestController
@RequestMapping(value = "/api/v1/details")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping
    public ResponseEntity<List<Detail>> getDetails(@RequestParam(name = "publicationId", required = false) Long publicationId){
        List<Detail> details;
        if (null == publicationId){
            details = detailService.getAll();
            if (details.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            details = detailService.findByPublication(Publication.builder().id(publicationId).build());
            if (details.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(details);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Detail> getDetail(@PathVariable("id") Long id){
        Detail detail = detailService.getById(id);
        if (null == detail){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }

    @PostMapping
    public ResponseEntity<Detail> createDetail(@RequestBody Detail detail){
        Detail detailCreate = detailService.create(detail);
        return ResponseEntity.status(HttpStatus.CREATED).body(detailCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable("id") Long id, @RequestBody Detail detail){
        detail.setId(id);
        Detail detail1 = detailService.update(detail);
        if (detail1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail1);
    }

    @DeleteMapping("{detailId}")
    public Optional<ResponseEntity<?>> deleteDetail(@PathVariable Long detailId){
        return detailService.delete(detailId);
    }
}

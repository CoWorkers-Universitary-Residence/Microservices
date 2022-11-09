package pe.edu.coworkers.publicationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.services.PhotoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Photo", description = "CRUD photos")
@RestController
@RequestMapping (value = "/api/v1/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<Photo>> getPhotos(@RequestParam(name = "publicationId", required = false) Long publicationId){
        List<Photo> photos;
        if (null == publicationId){
            photos = photoService.getAll();
            if (photos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            photos = photoService.findByPublication(Publication.builder().id(publicationId).build());
            if (photos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(photos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable("id") Long id){
        Photo photo = photoService.getById(id);
        if (null == photo){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photo);
    }

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo){
        Photo photoCreate = photoService.create(photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(photoCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable("id") Long id, @RequestBody Photo photo){
        photo.setId(id);
        Photo photo1 = photoService.update(photo);
        if (photo1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photo1);
    }

    @DeleteMapping("{photoId}")
    public Optional<ResponseEntity<?>> deletePhoto(@PathVariable Long photoId){
        return photoService.delete(photoId);
    }
}

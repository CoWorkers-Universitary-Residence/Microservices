package pe.edu.coworkers.publicationservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.repositories.PhotoRepository;
import pe.edu.coworkers.publicationservice.services.PhotoService;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> getAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    @Override
    public Photo create(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo update(Photo photo) {
        Photo photoDB = getById(photo.getId());
        if (null == photoDB){
            return null;
        }
        photoDB.setPhoto(photo.getPhoto());
        photoDB.setDescription(photo.getDescription());
        photoDB.setPublication(photo.getPublication());
        return photoRepository.save(photoDB);
    }

    @Override
    public Optional<ResponseEntity<?>> delete(Long photoId) {
        return photoRepository.findById(photoId).map(photo -> {
            photoRepository.delete(photo);
            return ResponseEntity.ok().build();
        });
    }

    @Override
    public List<Photo> findByPublication(Publication publication) {
        return photoRepository.findByPublication(publication);
    }
}

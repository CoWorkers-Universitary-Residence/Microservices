package pe.edu.coworkers.publicationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.repositories.PhotoRepository;

import java.util.List;

public class PhotoServiceImpl implements PhotoService{

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
    public void delete(Long id) throws Exception {
        photoRepository.deleteById(id);
    }

    @Override
    public List<Photo> findByPublication(Publication publication) {
        return photoRepository.findByPublication(publication);
    }
}

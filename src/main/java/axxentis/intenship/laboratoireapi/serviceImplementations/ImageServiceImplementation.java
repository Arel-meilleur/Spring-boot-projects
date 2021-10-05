package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Image;
import axxentis.intenship.laboratoireapi.repositories.ImageRepository;
import axxentis.intenship.laboratoireapi.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ImageServiceImplementation implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(Long id, Image image) {
        Optional<Image> imageToUpdate = findImageById(id);
        imageToUpdate.get().setUrl(image.getUrl());
        return imageRepository.save(imageToUpdate.get());
    }

    @Override
    public void deleteImage(Long id) {
         imageRepository.deleteById(id);
    }
}

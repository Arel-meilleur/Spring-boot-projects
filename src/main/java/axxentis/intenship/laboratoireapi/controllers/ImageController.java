package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.Image;
import axxentis.intenship.laboratoireapi.payload.dto.ImageDto;
import axxentis.intenship.laboratoireapi.payload.responses.ApiResponse;
import axxentis.intenship.laboratoireapi.repositories.ImageRepository;
import axxentis.intenship.laboratoireapi.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<?>getAll(){
        List<Image> images = imageService.getAllImages();
        if (!CollectionUtils.isEmpty(images)){
            // Map the employee class to ImageDto class
            List<ImageDto> allImagesDto = mapList(images, ImageDto.class);
            return ResponseEntity.ok(new ApiResponse(true, allImagesDto, "Images loaded successfully" , HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, images, "Failed to load images, empty List", HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<?>getImage(@PathVariable(value = "id") final Long id){
        Optional<Image> image = imageService.findImageById(id);
        if (image.isPresent()){
            ImageDto imageDto = mapImageToImageDto(image);
            return ResponseEntity.ok(new ApiResponse(true, imageDto, "Image loaded successfully", HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, image, "Unsuccessful attempt, image not found", HttpStatus.NOT_FOUND));
        }
    }
    @PostMapping(value = "/add")
    public ResponseEntity<?> addImage(@RequestBody Image image) {
        Image newImage = imageService.addImage(image);
        if (image.getUrl().isBlank() || image.getUrl().isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(false, image, "Please enter the image url!", HttpStatus.PARTIAL_CONTENT));
        }else {
            ImageDto imageDto = mapImageToImageDto(newImage);
            return ResponseEntity.ok(new ApiResponse(true, imageDto, "Image added successfully", HttpStatus.OK));
        }

    }

//    @PostMapping(value = "/add")
//    public ResponseEntity<?>createImage(@RequestBody Image image){
//        Image saveImage = imageService.addImage(image);
//        return ResponseEntity.ok(new ApiResponse(true, image, "Image added Successfully", HttpStatus.OK));
//    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updateImage(@PathVariable(value = "id") final Long id, @RequestBody Image image){
        Image newImage = imageService.updateImage(id, image);
        if (imageService.findImageById(id).isEmpty()){
            return ResponseEntity.ok(new ApiResponse(false, image, "Image id " + id + " not found", HttpStatus.NOT_FOUND));
        }else {
            ImageDto imageDto = mapImageToImageDto(newImage);
            return ResponseEntity.ok(new ApiResponse(true, imageDto, "Image updated Successfully", HttpStatus.OK));
        }

    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?>deleteImage(@PathVariable(value = "id") final Long id){
        imageService.deleteImage(id);
        return ResponseEntity.ok(new ApiResponse(true, "Image deleted Successfully", HttpStatus.OK));
    }

    @GetMapping("url/{url}")
    public List<Image> findByUrl(@PathVariable("url") String url){
        return imageRepository.findByUrl(url);

    }

    @GetMapping("url/{url}/{id}")
    public List<Image> findByUrlOrderById(@PathVariable("url") String url,
                                          @PathVariable("id") Long id){
        return imageRepository.findByUrlOrderById(url);

    }


    // Methods for Dto implementation

    /**
     * Transforme source List to dto list targetClass
     *
     * @param source
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }


    /**
     * Transform single Image to  ImageDto
     *
     *
     * @param image
     * @return
     */
    private ImageDto mapImageToImageDto(Image image) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(image, ImageDto.class);

    }
    private ImageDto mapImageToImageDto(Optional<Image> image) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(image, ImageDto.class);

    }

}



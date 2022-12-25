package alex.klimchuk.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface ImageService {

    void saveImageFile(String recipeId, MultipartFile file);

}

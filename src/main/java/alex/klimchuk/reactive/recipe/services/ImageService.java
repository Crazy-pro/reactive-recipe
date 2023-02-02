package alex.klimchuk.reactive.recipe.services;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface ImageService {

    Mono<Void> saveImageFile(String recipeId, MultipartFile file);

}

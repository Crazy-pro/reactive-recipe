package alex.klimchuk.reactive.recipe.controllers;

import alex.klimchuk.reactive.recipe.services.ImageService;
import alex.klimchuk.reactive.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Controller
@RequestMapping("/recipe/{id}")
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findDtoById(id).block());
        return "recipe/imageUploadForm";
    }

    @PostMapping("/image")
    public String saveImageFile(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file).block();
        return "redirect:/recipe/" + id + "/show";
    }

//    @GetMapping("/recipeimage")
//    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
//        RecipeDto recipeDto = recipeService.findDtoById(id).block();
//
//        if (Objects.nonNull(recipeDto.getImage())) {
//            byte[] byteArray = new byte[recipeDto.getImage().length];
//            int i = 0;
//
//            for (Byte wrappedByte : recipeDto.getImage()) {
//                byteArray[i++] = wrappedByte;
//            }
//
//            response.setContentType("image/jpeg");
//            InputStream is = new ByteArrayInputStream(byteArray);
//            IOUtils.copy(is, response.getOutputStream());
//        }
//    }

}

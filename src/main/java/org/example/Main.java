package org.example;

import com.github.javafaker.Faker;
import org.example.entities.CategoryEntity;
import org.example.repositories.CategoryRepository;
import org.example.storage.StorageProperties;
import org.example.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.nio.file.Path;
import java.util.Random;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    private static final String LOREM_PICSUM_URL = "https://picsum.photos/200/300";

    public static String downloadImage(String imageUrl, String destinationFile, int[] sizes) {
        try {
            URL url = new URL(imageUrl);
            for (int size : sizes) {
                // Отримання InputStream з URL
                try (InputStream inputStream = url.openStream()) {
                    Path destinationPath = Paths.get(destinationFile);

                    // Копіювання даних з InputStream до файлу
                    Files.copy(inputStream, Path.of("uploading/" + size+"_"+destinationPath));

                    System.out.println("Фото було завантажено: " + destinationFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destinationFile;
    };
    @Bean
    CommandLineRunner runner(CategoryRepository repository, StorageService storageService) {
        return args -> {
            storageService.init();
            //Faker faker = new Faker();
            //Random random = new Random();
//            for (int i = 0; i < 100; i++) {
//                CategoryEntity category = new CategoryEntity();
//                category.setName(faker.commerce().department());
//
//                int randomIndex = random.nextInt(1000); // Ви можете обрати будь-яке максимальне значення
//                String imageUrl = LOREM_PICSUM_URL + "?random=" + randomIndex;
//
//                // Викликаємо метод для завантаження зображення та збереження його
//
//                int[] sizes = {32, 150, 300, 600, 1200};
//                // Set other properties like image, description, creation time using Faker
//                category.setImage(downloadImage(imageUrl, category.getName() + ".webp", sizes));
//                category.setDescription(faker.lorem().sentence());
//                category.setCreationTime(LocalDateTime.now());
//                repository.save(category);
////            }
//            CategoryEntity category = new CategoryEntity();
//            category.setName("Одяг");
//            category.setImage("1.jpg");
//            category.setDescription("Для дорослих людей");
//            category.setCreationTime(LocalDateTime.now());
//            repository.save(category);
        }
                ;
        //};
    };

}
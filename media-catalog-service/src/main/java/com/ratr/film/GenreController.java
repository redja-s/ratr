package com.ratr.film;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class GenreController {
	private GenreRepository genreRepository;
}

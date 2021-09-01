package com.example.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiaton implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		User gabriel = new User(null, "Gabriel", "gabriel@gmail.com");
		User washington = new User(null, "Washington", "washington@gmail.com");
		User maria = new User(null, "Maria", "maria@gmail.com");
		userRepository.saveAll(Arrays.asList(gabriel, washington, maria));	

		postRepository.deleteAll();
		Post post1 = new Post(null, sdf.parse("21/03/2021"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(gabriel));
		Post post2 = new Post(null, sdf.parse("23/03/2021"), "Bom dia", "Acordei feliz!", new AuthorDTO(gabriel));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2021"), new AuthorDTO(washington));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2021"), new AuthorDTO(maria));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2021"), new AuthorDTO(washington));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		gabriel.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(gabriel);
	}
}
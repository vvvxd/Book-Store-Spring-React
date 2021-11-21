package com.example.bookstorespringreact;

import com.example.bookstorespringreact.model.Book;
import com.example.bookstorespringreact.model.Role;
import com.example.bookstorespringreact.model.User;
import com.example.bookstorespringreact.service.IRoleService;
import com.example.bookstorespringreact.service.IService;
import com.example.bookstorespringreact.utils.ConstantUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class Application implements CommandLineRunner {

	private final IService<User> userService;


	private final IRoleService<Role> roleService;


	private final IService<Book> bookService;

	public Application(IService<User> userService, IRoleService<Role> roleService, IService<Book> bookService) {
		this.userService = userService;
		this.roleService = roleService;
		this.bookService = bookService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new Role(ConstantUtils.ADMIN.toString()));
			roleService.saveOrUpdate(new Role(ConstantUtils.USER.toString()));
		}
		User user2 = new User();
		User user1 = new User();
		if (userService.findAll().isEmpty()) {

			user1.setEmail("test@user.com");
			user1.setName("Test User");
			user1.setMobile("9787456545");
			user1.setRole(roleService.findByName(ConstantUtils.USER.toString()));
			user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
			userService.saveOrUpdate(user1);


			user2.setEmail("test@admin.com");
			user2.setName("Test Admin");
			user2.setMobile("9787456545");
			user2.setRole(roleService.findByName(ConstantUtils.ADMIN.toString()));
			user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
			userService.saveOrUpdate(user2);
		}

		if (bookService.findAll().isEmpty()) {
			for (int i = 1; i <= 10; i++) {
				Book book = new Book();
				book.setTitle("Spring Microservices in Action " + i);
				book.setAuthor("John Carnell " + i);
				book.setCoverPhotoURL(
						"https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg");
				book.setPrice(2776.00 + i);
				book.setYearOfReading("2012");
				book.setLanguage("English");
				book.setType("Audio");
				book.setUser("3");
				bookService.saveOrUpdate(book);
			}
		}
	}

}

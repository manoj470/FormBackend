package com.clover.form;

import com.clover.form.model.Employee;
import com.clover.form.repository.DocumentDetailsRepo;
import com.clover.form.repository.EmployeeRepository;
import com.clover.form.repository.FamilyDetailsRepository;
import com.clover.form.service.EmployeeService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

//(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class FormApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FormApplication.class, args);
	}

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DocumentDetailsRepo documentDetailsRepo;
	@Autowired
	FamilyDetailsRepository familyDetailsRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("start");
//		CompletableFuture<Void> run = CompletableFuture.runAsync(
//				()->{
//					try {
//						insertData();
//					} catch (ParseException e) {
//						throw new RuntimeException(e);
//					}
//				}
//		);
//		run.join();

	}

	public static String toString(char[] a)
	{
		// Creating object of String class

		return new String(a);
	}

	static String getRandomWord(String[] array) {
		Random random = new Random();
		int index = random.nextInt(array.length);
		return array[index];
	}

//	static String[] getRandomHobby(String[] ar){
//		Random random = new Random();
//		String[] arr = new getRandomWord(ar)
//
//	}



	private static char[] generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for(int i = 4; i< length ; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password;
	}
	@Async(value = "taskExecutor")
	public void insertData() throws ParseException {
		Faker faker = new Faker();

		Random r = new Random();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 100000 ; i++) {
			Employee e = new Employee();
			e.setEmployeeName(faker.name().fullName());
//		System.out.println(formatter.format(faker.date().birthday(25,60)));
//		System.out.println(formatter.format(faker.date().birthday(25,60)));
			e.setDateOfBirth(formatter.parse(formatter.format(faker.date().birthday(25,60))));
			e.setAvatar(faker.avatar().image().getBytes());
			e.setPanNumber(faker.regexify("[a-z]{5}[0-9]{4}[a-z]{1}"));
			e.setGender(getRandomWord(new String[]{"Male", "Female","Trans"}));
			e.setCountry(getRandomWord(new String[]{"India","Italy","Germany","France","USA","Russia"}));
			e.setCity(getRandomWord(
					new String[]{"Mumbai","Pune","Chennai","Kolkata","Bangalore","Hyderabad","Delhi"}));
			e.setHobbies(getRandomWord(new String[]{"Reading","Cricket","Football","Listening music"}));
			e.setAddressLine1(faker.address().streetAddress());
			e.setAddressLine2(faker.address().secondaryAddress());
			e.setPassword(toString(generatePassword(8)));
			e.setZipCode("400097");
			e.setFamilyDetailsList(null);
			e.setEmail(faker.regexify("[a-z]{6}\\@"+"[a-z]{4}"));
			System.out.println(e);
			employeeRepository.save(e);
		}
		System.out.println("inserted");
	}
}

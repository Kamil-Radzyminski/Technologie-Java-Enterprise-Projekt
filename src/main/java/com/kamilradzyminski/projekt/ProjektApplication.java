package com.kamilradzyminski.projekt;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.dto.PersonRegister;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

@SpringBootApplication

public class ProjektApplication {
    @Autowired
    PersonServiceImpl personService;

    @Autowired
    PersonRepo personRepo;
    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
	public void Init() {

		String line;
		BufferedReader br;
		Long counter= 1L;
        personService.saveAdmin(new PersonRegister("admin","admin","adminnn@admin.com","Polska","Haslo123","admin"));
//		try {
//			br = new BufferedReader(new FileReader("src/main/resources/Persons.csv"));
//			while ((line = br.readLine()) != null) {
//				String[] split = line.split(",", 7);
//                personService.save(new PersonRegister(split[1], split[2], split[3], split[4], split[5], split[6]));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			br = new BufferedReader(new FileReader("src/main/resources/Domains.csv"));
//			while ((line = br.readLine()) != null) {
//				String[] split = line.split(",", 4);
//				App application = new App(split[1], split[2]);
//				Long finalCounter = counter;
//				application.getPersonList().add(personRepo.findById(counter)
//						.orElseThrow(()-> new NotFoundException(finalCounter)));
//				applicationRepository.save(application);
//				counter++;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}

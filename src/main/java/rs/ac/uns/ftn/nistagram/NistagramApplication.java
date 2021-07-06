package rs.ac.uns.ftn.nistagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NistagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(NistagramApplication.class, args);
	}

}

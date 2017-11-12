package br.com.felipe.soapcustomclient;

import br.com.felipe.soapcustomclient.models.User;
import br.com.felipe.soapcustomclient.services.DataBaseService;
import br.com.felipe.soapcustomclient.services.LoadCSVService;
import br.com.felipe.soapcustomclient.services.SOAPClientService;
import br.com.felipe.soapcustomclient.services.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SoapcustomclientApplication implements CommandLineRunner{

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		try {

			if(args.length == 1) {
				context = SpringApplication.run(SoapcustomclientApplication.class, args);
				List<User> users = context.getBean(LoadCSVService.class).load(args[0]);
				context.getBean(SOAPClientService.class).execute(users);
			} else {
				throw new IllegalArgumentException("Format of CSV file must be passed.");
			}

		} catch (IOException | IllegalArgumentException e) {
			UtilsService.handleExceptions(e);

		} finally {
			if(context != null)
				SpringApplication.exit(context);
			else
				SpringApplication.exit(SpringApplication.run(SoapcustomclientApplication.class));
		}

	}

	@Override
	public void run(String... strings) throws Exception {


	}


}

package pl.wodnet.shploader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShploaderApplication {
	private static ConfigurableApplicationContext context;
//	ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(ShploaderApplication.class, args);
	}

//	public static void restart() {
//		ApplicationArguments args = context.getBean(ApplicationArguments.class);
//
//		Thread thread = new Thread(() -> {
//			context.close();
//			context = SpringApplication.run(ShploaderApplication.class, args.getSourceArgs());
//		});
//
//		thread.setDaemon(false);
//		thread.start();
//	}
}

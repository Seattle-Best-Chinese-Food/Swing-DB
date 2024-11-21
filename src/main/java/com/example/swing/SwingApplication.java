package com.example.swing;

import com.example.swing.dao.ContactDAO;
import com.example.swing.dao.DishDAO;
import com.example.swing.ui.ContactFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.swing.ui.AdminMenuPage;


@SpringBootApplication
public class SwingApplication {

	public static void main(String[] args) {
		// Initialize SpringApplication
        SpringApplication app = new SpringApplication(SwingApplication.class);
        // Disable headless mode
        app.setHeadless(false);
		

        // Run the application
        ApplicationContext context = app.run(args);

        // Fetch the ContactDAO bean
        ContactDAO contactDAO = context.getBean(ContactDAO.class);
        DishDAO dishDAO = context.getBean(DishDAO.class);
        // Initialize and display the GUI
        // ContactFrame frame = new ContactFrame(contactDAO, dishDAO);
        // frame.initialize();

		AdminMenuPage frame2 = new AdminMenuPage(dishDAO);
		try {
			frame2.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

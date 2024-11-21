package com.example.swing;

import com.example.swing.dao.ContactDAO;
import com.example.swing.dao.DishDAO;
import com.example.swing.ui.ContactFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.swing.controller.AdminMenuController;
import com.example.swing.view.AdminMenuPage;


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


		// AdminMenuPage frame2 = new AdminMenuPage(dishDAO);
		// try {
		// 	frame2.initialize();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }


		// Create an instance of AdminMenuController and pass the DishDAO
        AdminMenuController controller = new AdminMenuController(dishDAO);

        // Create an instance of AdminMenuPage and pass the controller
        AdminMenuPage adminMenuPage = new AdminMenuPage(controller);

        // Initialize the AdminMenuPage
        adminMenuPage.initialize();


	}

}

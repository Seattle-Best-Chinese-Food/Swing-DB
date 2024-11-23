package com.example.swing;


import com.example.swing.dao.DishDAO;
import com.example.swing.dao.OrderDAO;
import com.example.swing.dao.OrderItemDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.swing.controller.AdminMenuController;
import com.example.swing.view.AdminMenuPage;
import com.example.swing.view.CustomerMenuView;
import com.example.swing.controller.CustomerMenuController;


@SpringBootApplication
public class SwingApplication {

	public static void main(String[] args) {
		// Initialize SpringApplication
        SpringApplication app = new SpringApplication(SwingApplication.class);
        // Disable headless mode
        app.setHeadless(false);
		

        // Run the application
        ApplicationContext context = app.run(args);

        DishDAO dishDAO = context.getBean(DishDAO.class);
        OrderDAO orderDAO = context.getBean(OrderDAO.class);
        OrderItemDAO orderItemDAO = context.getBean(OrderItemDAO.class);

		
		// MVC Customer Menu
		CustomerMenuView view = new CustomerMenuView(); // Initialize the view first
        CustomerMenuController controller = new CustomerMenuController(dishDAO, view, orderDAO, orderItemDAO); // Pass the view to the controller


		// Create an instance of AdminMenuController and pass the DishDAO
        // AdminMenuController controller = new AdminMenuController(dishDAO);

        // // Create an instance of AdminMenuPage and pass the controller
        // AdminMenuPage adminMenuPage = new AdminMenuPage(controller);

        // // Initialize the AdminMenuPage
        // adminMenuPage.initialize();


	}

}

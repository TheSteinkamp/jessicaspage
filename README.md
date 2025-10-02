# Jessicaspage

## Description 
This repository serves as the backend API for the personal frontpage, primarily focused on handling submissions from the Contact Form.

This backend repository is part of a larger application. The **frontend** repository is available at:
(https://github.com/TheSteinkamp/jessicasfrontpage.git)

## Technology Stack
The API is built using **Java** and uses **Maven** for dependency management and building.

## Backend API Functionality
The backend performs three key tasks when a user submits the contact form:

**Data Ingestion & Persistence:** The API receives the contact information (name, email, message) sent from the frontend. 
This data is then securely stored in an external database hosted on Render. This ensures that all submissions are logged and persistent.

**Email Notification Service:** The backend integrates with an external email service (Brevo). Immediately after receiving a submission, 
the API sends a request to Brevo to trigger an email notification, ensuring I am instantly alerted when a new message has been received.

**Client Response:** The API sends a confirmation response back to the frontend (e.g., an HTTP 200 OK), letting the user know their message has been successfully received.

This setup ensures reliable data storage and real-time communication for every contact form submission. 


## Getting Started (How to Run)

### Prerequisites
* Java Development Kit (JDK) 17
* Apache Maven installed

### Setup & Startup
1.  **Clone the repository:**
    ```bash
    git clone (https://github.com/TheSteinkamp/jessicaspage.git)
    ```
2.  **Build the project (using Maven):**
    Navigate to the project root and compile the code:
    ```bash
    mvn clean install
    ```
3.  **Run the Server:**
    Execute the compiled JAR file (usually found in the `target/` directory). *You may need to adjust the path/name of the JAR:*
    ```bash
    java -jar target/jessicaspage-1.0.jar
    ```

***Note:** You must configure the necessary environment variables (e.g., database URL, Brevo API key) for the application to function correctly.*

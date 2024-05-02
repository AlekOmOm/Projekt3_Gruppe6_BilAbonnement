
Set up database and run the server

Set up Database connection and Schema in IntelliJ

1. Set up Database connection and create schema
    - Open IntelliJ
    - Click on View -> Tool Windows -> Database
    - Click on the + sign and choose Data Source -> MySQL
    - Fill in the following fields:
        - Host: localhost
        - Port: 3306
        - User: {your username}
        - Password: {your password}
        - Database: bil_abonnement
    - Click on Test Connection and then OK
    - Right-click on the bil_abonnement database and choose New -> SQL File
    - Copy the contents of the file `database.sql` from the root of the project and paste it into the SQL file
    - Click on the green arrow to run the SQL file

2. Set environment variables in IntelliJ:
    - Click on Run -> Edit Configurations
    - Click on the + sign and choose Application
    - Name the configuration "BilAbonnementApplication"
    - Set the main class to "com.example.bilabonnement.BilAbonnementApplication"
    - Click on the Environment Variables tab
    - Add the following environment variables:
        - SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/bil_abonnement
        - SPRING_DATASOURCE_USERNAME={your username}
        - SPRING_DATASOURCE_PASSWORD={your password}
    - Click on Apply and OK

3. Run the server!
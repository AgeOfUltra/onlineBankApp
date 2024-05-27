<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>Online Banking Application</h1>
    <p>Welcome to the Online Banking Application. This project is built using Java, Servlets, and JSP to provide users with a simple and secure online banking experience.</p>
    <h2>Features</h2>
    <ul>
        <li>User Registration</li>
        <li>User Login</li>
        <li>View Transaction History</li>
        <li>Withdraw Money</li>
        <li>Deposit Money</li>
        <li>Account Deletion</li>
        <li>Check Balance</li>
    </ul>
    <h2>Technologies Used</h2>
    <ul>
        <li>Java</li>
        <li>Servlets</li>
        <li>JSP (JavaServer Pages)</li>
        <li>MySQL (or any other relational database)</li>
        <li>HTML/CSS for front-end</li>
    </ul>
    <h2>Project Setup</h2>
    <ol>
        <li>Clone the repository to your local machine:</li>
        <pre><code>git clone https://github.com/your-username/online-banking-application.git</code></pre>
        <li>Import the project into your IDE (e.g., Eclipse or IntelliJ IDEA).</li>
        <li>Set up the database:
            <ul>
                <li>Create a database named <code>online_banking</code>.</li>
                <li>Run the SQL script provided in the <code>resource</code> folder to create the necessary tables.</li>
            </ul>
        </li>
        <li>Update the database configuration in the <code>db.properties</code> file:</li>
        <pre><code>
db.url=jdbc:mysql://localhost:3306/banking
db.username=your_db_username
db.password=your_db_password
        </code></pre>
        <li>Deploy the application to a servlet container (e.g., Apache Tomcat).</li>
        <li>Access the application at <code>http://localhost:8080/</code>.</li>
    </ol>

   <h2>How to Use</h2>
    <ol>
        <li>Register a new user account via the registration page.</li>
        <li>Log in with your credentials.</li>
        <li>Use the dashboard to:
            <ul>
                <li>View your transaction history.</li>
                <li>Withdraw or deposit money.</li>
                <li>Delete your account if needed.</li>
            </ul>
        </li>
    </ol>
    <h2>Contact</h2>
    <p>If you have any questions or suggestions, feel free to open an issue or contact me at <a href="mailto:nenavathsrikanth007@gmail.com">nenavathsrikanth007@gmail.com</a>.</p>
</body>
</html>

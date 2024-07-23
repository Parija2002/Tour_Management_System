<?php

// Check if form fields are set
if(isset($_POST['name'], $_POST['email'], $_POST['phone'], $_POST['subject'], $_POST['Message'])) {
    $Name = $_POST['name'];
    $email = $_POST['email'];
    $phone = $_POST['phone'];
    $subject = $_POST['subject'];
    $message = $_POST['Message'];

    // database connection
    $conn = new mysqli('localhost', 'root', '12345', 'tourism');
    if ($conn->connect_error) {
        die('Connection failed: ' . $conn->connect_error);
    } else {
        // Prepare the SQL statement
        $stmt = $conn->prepare("INSERT INTO contact_us (Name, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)");
        if ($stmt === false) {
            die('Error preparing statement: ' . $conn->error);
        }

        // Bind parameters
        $stmt->bind_param("sssss", $Name, $email, $phone, $subject, $message);

        // Execute the statement
        $success = $stmt->execute();
        if ($success === false) {
            die('Error executing statement: ' . $stmt->error);
        }

        echo "Message sent successfully...";

        // Close statement and connection
        $stmt->close();
        $conn->close();
    }
} else {
    // Handle case where form fields are not set
    die("Form fields are not set");
}

?>

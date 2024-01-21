<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="vi-VN">
<body>

<h2>Text field</h2>
<p>The <strong>input type="text"</strong> defines a one-line text input field:</p>

<form action="/action_page.php">
  <label for="datetest">Date:</label><br>
  <input type="date" id="date" name="date" value="2023-12-01"><br>
  
  <input type="submit" value="Submit">
</form>

<p>Note that the form itself is not visible.</p>
<p>Also note that the default width of a text field is 20 characters.</p>


<p>test 1</p>

<h1>GeeksforGeeks</h1>  
      
    <h3>  
        Get input date in  
        dd-mm-yyyy format  
    </h3>  
      
    <label for="Date of Birth">  
        Enter the Date:  
        <input type="date" name="date">  
    </label>  
    
<p>test 2</p>    
    
    <label for="start">  
        Enter the Date:  
    </label>  
  
    <input type="date" name="begin"
        placeholder="dd-mm-yyyy" value=""
        min="1997-01-01" max="2030-12-31">  
        
        <p>test 3</p>
        
        <form action="/action_page.php">
  <label for="datetest">Date:</label><br>
  <input type="date" id="date" name="date" placeholder="dd/mm/yyyy" value="2023-12-01"><br>
  
  <input type="submit" value="Submit">
</form>
</body>
</html>


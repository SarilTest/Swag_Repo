package project;

//Base URL and Standard user login details
public interface Config {
    public static final String BASE_URL = "https://www.saucedemo.com/v1/index.html";
    public static final String STANDARD_USER = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String driverPath=  "C:\\\\Users\\\\saril\\\\eclipse-workspace\\\\Testing\\\\Drivers\\\\chromedriver.exe";
    public static final String driverName=  "webdriver.chrome.driver";
    
    
    public static final Object[][] USER_CREDENTIALS_ARRAY= new Object[][]{
            {"standard_user", "secret_sauce", true},   // valid user
           {"locked_out_user", "secret_sauce", false}, // locked user, cant login
             {"problem_user", "secret_sauce", true},      // another valid user but problems after logging in
           {"performance_glitch_user", "secret_sauce", true}      // another valid user but having performance glitch
         };
         
         
         public static final Object[][] POST_CODE_ARRAY=    new Object[][]{
             {"SAM", "MATHEW", "56433"},        // Minimum Boundary (5 characters)
             {"TOM", "CRUISE", "1234567890"},   // Maximum Boundary (10 characters)
             {"JACKIE", "CHAN", "12345678901"}   // Above Maximum Boundary (11 characters)
     };

}

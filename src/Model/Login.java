package Model;


public class Login {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;

    // Constructor, getters y setters
    public Login(String userId, String name, String email, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}


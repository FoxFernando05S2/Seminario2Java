package Model.Entities;

public class Client {
    private String clientId;
    private String userId;
    private String companyName;
    private String contactNumber;

    // Constructor completo
    public Client(String clientId, String userId, String companyName, String contactNumber) {
        this.clientId = clientId;
        this.userId = userId;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }

    // Getters y setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

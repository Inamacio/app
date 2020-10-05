package inter.app.model;

import java.util.List;
import java.util.UUID;

/**
 * User Model
 */
public class User {

    private UUID id;
    private String name;
    private String email;
    private List<SingleDigit> calculations;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCalculations(List<SingleDigit> calculations) {
        this.calculations = calculations;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<SingleDigit> getCalculations() {
        return calculations;
    }
}

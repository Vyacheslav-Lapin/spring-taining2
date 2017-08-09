package lab.model.simple;

import lab.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component("country")
@Entity
@Table(name="country")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class SimpleCountry implements Country, Serializable {

    @Id
    @GeneratedValue
	private int id;

    private String name;

    private String codeName;

    public SimpleCountry(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}

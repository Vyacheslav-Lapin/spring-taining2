package lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

    private String name;

    private String codeName;

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }
}

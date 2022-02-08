package restapi.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Seller {
    private int id;
    private String petId;
    private String quantity;
    private String shipDate;
    private String status;
    private int complete;
}


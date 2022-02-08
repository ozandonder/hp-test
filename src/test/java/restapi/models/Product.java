package restapi.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
public class Product {
    private int id;
    private ArrayList category;
    private String name;
    private String photoUrls;
    private ArrayList tags;
    private String status;
}


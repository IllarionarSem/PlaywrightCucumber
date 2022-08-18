package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product extends Model {

    private String title;
    private Double lowestPrice;
    private Double highestPrice;
    private Double shippingCost;
    private String location;
    private Long soldItems;
    private Long watchers;
}

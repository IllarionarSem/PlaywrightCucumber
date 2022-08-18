package model.row;

import model.Product;

import java.util.Objects;

public class ProductRow extends BaseRow<Product> {

    @ModelInfo(name = "title")
    private String title;
    @ModelInfo(name = "price")
    private String price;
    @ModelInfo(name = "shipping")
    private String shippingCost;
    @ModelInfo(name = "location")
    private String location;
    @ModelInfo(name = "hotness")
    private String hotness;

    @Override
    public Product asModel() {
        String[] prices = price.replaceAll("[$]", "").split("to");
        String shipping = shippingCost.contains("Shipping") ? null : shippingCost.replace("shipping", "").replaceAll("[+$]", "").trim();
        String sold = Objects.nonNull(hotness) && hotness.contains("sold") ? hotness.replaceAll("[+sold]", "").trim() : null;
        String watchers = Objects.nonNull(hotness) && hotness.contains("watcher") ? hotness.replaceAll("[watchers+]", "").trim() : null;
        return new Product(title,
                prices.length > 1 ? Double.parseDouble(prices[0].trim()) : 0,
                prices.length > 1 ? Double.parseDouble(prices[1].trim()) : Double.parseDouble(prices[0].trim()),
                Objects.isNull(shipping) ? 0 : Double.parseDouble(shipping),
                location.replace("from", "").trim(),
                Objects.isNull(sold) ? 0 : Long.parseLong(sold),
                Objects.isNull(watchers) ? 0 : Long.parseLong(watchers));
    }
}

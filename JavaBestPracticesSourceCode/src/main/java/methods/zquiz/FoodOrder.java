package methods.zquiz;

import java.math.BigDecimal;

public record FoodOrder(String itemName, BigDecimal itemPrice, int quantity,
        String deliveryAddress, String specialInstructions,
        boolean isExpressDelivery, boolean applyDiscount) {

}

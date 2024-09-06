package methods.zquiz;

import java.math.BigDecimal;

// Coding Exercise: https://www.udemy.com/course/java-best-practices/learn/quiz/6117558#overview
// Using the record class, create a record class named FoodOrder for many fields to reduce emthod parameter numbers.

public class FoodOrderService {

    public void placeOrder(FoodOrder order) {
        validateOrder(order);
        BigDecimal totalAmount = calculateTotalPrice(order);
        initiatePayment(totalAmount);
        initiateProduction(order);
    }
    
    private void validateOrder(FoodOrder order) {
        // implementation skipped for brevity
    }
    
    private BigDecimal calculateTotalPrice(FoodOrder order) {
        // implementation skipped for brevity
        return BigDecimal.ONE;
    }
    
    private void initiatePayment(BigDecimal totalAmount) {
        // implementation skipped for brevity
    }
    
    private void initiateProduction(FoodOrder order) {
        // implementation skipped for brevity
    }
}

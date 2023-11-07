package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {

    private int id;

    private Date buyDate;

    private Status status;

    private User user;

    private int userId;

    private List<OrderDetail> orderDetails;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return id + "," + buyDate.toString() + "," + status + "," + userId;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal total = BigDecimal.ZERO;
        for (var orderDetail : orderDetails) {
            total = total.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
        }
        return total;
    }
}
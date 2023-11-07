package service;

import model.*;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private static List<Order> orders = new ArrayList<>();


    private static List<OrderDetail> orderDetails = new ArrayList<>();

    private static ProductService productService = new ProductService();

    private static int currentIdOrder = 0;
    private static int currentIdOrderDetail = 0;

    static {
        readDataOrderDetails();
        readDataOrder();
    }

    //admin
    public List<Order> getOrders() {
        return orders;
    }
    //user
    public List<Order> getOrdersByUser(){
        return orders.stream().filter(e -> e.getUserId() == LoginService.currentUser().getId()).collect(Collectors.toList());
    }
    public List<Order> getOrdersByStatus(Status status){
        return orders.stream().filter(e -> e.getStatus() == status).collect(Collectors.toList());
    }

    public void changeOrderStatus(int id){
//        for (var order: orders) {
//            if(order.getId() == id){
//                order.setStatus(Status.PAID);
//                break;
//            }
//        }
        orders.forEach(e -> {
            if(e.getId() == id){
                e.setStatus(Status.PAID);
            }
        });
    }

    public void createOrder(List<OrderDetail> orderDetails) {
        //{
        // productId: 1,2,3
        // quantity: 2,1,1
        // }
        Order order = new Order();
        order.setBuyDate(Date.valueOf(LocalDate.now()));
        order.setStatus(Status.ORDER);
        order.setId(++currentIdOrder);
        order.setUserId(LoginService.currentUser().getId());
        for (var orderDetail : orderDetails) {
            Product product = productService.findById(orderDetail.getProductId());
            orderDetail.setName(product.getName());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setUnit(product.getUnit());
            orderDetail.setOrderId(order.getId());
            orderDetail.setId(++currentIdOrderDetail);
        }
        order.setOrderDetails(orderDetails);
        orders.add(order);
        writeData();

    }

    private static void readDataOrderDetails() {
        //1,Sting, orderId:1
        //2, Cafe, OrderId:1
        //3,Sting, orderId:2
        //4, Cafe, OrderId:2
        //orderDetails
        //1,2023-03-03,PAID
        //2,2023-03-04,PAID
        //Order
        try {
            File fileWriter = new File("data/order-details.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                String[] data = line.split(",");
                currentIdOrderDetail = Integer.parseInt(data[0]);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(Integer.parseInt(data[0]));
                orderDetail.setName(data[1]);
                orderDetail.setQuantity(Integer.parseInt(data[2]));
                orderDetail.setOrderId(Integer.parseInt(data[3]));
                orderDetail.setProductId(Integer.parseInt(data[4]));
                orderDetail.setPrice(new BigDecimal(data[5]));
                orderDetail.setUnit(Unit.valueOf(data[6]));
                orderDetails.add(orderDetail);
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readDataOrder() {
        try {
            File fileWriter = new File("data/order.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                String[] data = line.split(",");
                currentIdOrder = Integer.parseInt(data[0]);
                Order order = new Order();
                order.setId(Integer.parseInt(data[0]));
                order.setBuyDate(Date.valueOf(data[1]));
                order.setStatus(Status.valueOf(data[2]));
                order.setUserId(Integer.parseInt(data[3]));
                order.setOrderDetails(
                        orderDetails.stream().filter(orderDetail -> orderDetail.getOrderId() == order.getId()).collect(Collectors.toList())
                );
                orders.add(order);
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void writeData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/order.txt"));
            BufferedWriter writerOrderDetail = new BufferedWriter(new FileWriter("data/order-details.txt"));
            for (var order : orders) {
                writer.write(order.toString());
                writer.newLine();
                for (var orderDetails : order.getOrderDetails()) {
                    writerOrderDetail.write(orderDetails.toString());
                    writerOrderDetail.newLine();
                }
            }
            writer.flush();
            writer.close();
            writerOrderDetail.flush();
            writerOrderDetail.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
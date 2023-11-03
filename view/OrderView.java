package view;

import model.OrderDetail;
import service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ultis.AppUltis.getNumber;

public class OrderView {
     static OrderService orderService = new OrderService();
    public static void printMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tao moi don hang");
            System.out.println("2. Sua don hang");
            System.out.println("3. Xoa don hang");
            System.out.println("4. Hien thi doanh thu");
            System.out.println("5. Hien thi Don hang");
            System.out.println("6. Thoat");
            int choice = getNumber("Nhap vao lua chon", 1, 5);
            switch (choice) {
                case 1 -> createOrder();
                case 5 -> printOrders();
//                case 3 -> updateProduct();
//                case 4 -> deleteProduct();
                default -> System.exit(0);
            }
        }
    }

    private static void createOrder() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        // fake data de test
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setProductId(2);
//        orderDetail.setQuantity(1);
//        OrderDetail orderDetail1 = new OrderDetail();
//        orderDetail1.setProductId(1);
//        orderDetail1.setQuantity(2);
//        orderDetailList.add(orderDetail);
//        orderDetailList.add(orderDetail1);
        while (true){
            ProductView.printProduct();
            int productId = getNumber("Nhap id product muon mua");
            int quantity = getNumber("nhap so luong muon mua");
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(quantity);
            orderDetail.setProductId(productId);
            orderDetailList.add(orderDetail);
            System.out.println("1. Chon tiep san pham");
            System.out.println("2. Xong");
            int choice = getNumber("Nhap 1, 2", 1,2);
            if(choice == 2) break;
        }
        orderService.createOrder(orderDetailList);


    }

    private static void printOrders(){
        System.out.println("Id  | Buy Date | Status | Tong So Da Mua | Tong Tien");
        for (var order: orderService.getOrders()) {

            System.out.println(order.getId() + " | " + order.getBuyDate() + " | " + order.getStatus() + " | " + order.getOrderDetails().size() + " | " + order.getTotalPrice());
        }
    }

}
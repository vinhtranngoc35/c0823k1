package view;

import model.Status;
import service.OrderService;

import static ultis.AppUtil.getNumber;

public class OrderAdminView {
    private static OrderService orderService = new OrderService();
    public static void printMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("Menu:");
            System.out.println("1. Tao moi don hang");
            System.out.println("2. Sua don hang");
            System.out.println("3. Xoa don hang");
            System.out.println("4. Hien thi Don hang");
            System.out.println("5. Hien thi doanh thu");
            System.out.println("6. Cap nhat status Order");
            System.out.println("7. Thoat");
            int choice = getNumber("Nhap vao lua chon", 1, 7);
            switch (choice) {
//                case 1 -> createOrder();
                case 4 -> printOrders();
                case 6 -> changeStatusOrder();
//                case 3 -> updateProduct();
//                case 4 -> deleteProduct();
                default -> flag = false;
            }
        }
    }
    private static void printOrders(){
        System.out.println("Id  | Buy Date | Status | Tong So Da Mua | Tong Tien");
        for (var order: orderService.getOrders()) {

            System.out.println(order.getId() + " | " + order.getBuyDate() + " | " + order.getStatus() + " | " + order.getOrderDetails().size() + " | " + order.getTotalPrice());
        }
    }
    private static void changeStatusOrder(){
        System.out.println("Id  | Buy Date | Status | Tong So Da Mua | Tong Tien");
        for (var order: orderService.getOrdersByStatus(Status.ORDER)) {

            System.out.println(order.getId() + " | " + order.getBuyDate() + " | " + order.getStatus() + " | " + order.getOrderDetails().size() + " | " + order.getTotalPrice());
        }
        int id = getNumber("nhap id muon thay doi status");
        orderService.changeOrderStatus(id);
    }
}
package view;

import model.Role;
import service.LoginService;
import ultis.AppUtil;

public class LoginView {
    private static LoginService loginService = new LoginService();
    public static void printMenu(){
        System.out.println("1. Dang Nhap");
        System.out.println("2. Dang ky");
        int choice = AppUtil.getNumber("Nhap lua chon");
        if(choice == 1){
            String username = AppUtil.getString("Nhap username");
            String password = AppUtil.getString("Nhap password");
            Role role = loginService.login(username, password);
            if(role == null){
                System.out.println("tai khoan hoac mat khau sai");
                printMenu();
            } else if (role == Role.USER) {
                OrderView.printMenu();
            }else {
                while (true){
                    System.out.println("1. Product View");
                    System.out.println("2. Order view");
                    int choiceView = AppUtil.getNumber("Nhap lua chon");
                    if(choiceView == 1){
                        ProductView.printMenu();
                    }else {
                        OrderAdminView.printMenu();
                    }

                }

            }
            //nếu  Role user => tạo đơn hàng
            // nếu Role Admin
        }else{
            String username = AppUtil.getString("Nhap username");
            String password = AppUtil.getString("Nhap password");
            loginService.register(username, password);
            printMenu();
        }
    }
}
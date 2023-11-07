package ultis;

import java.sql.Date;
import java.util.Scanner;

public class AppUtil {
    static Scanner sc = new Scanner(System.in);
    public static int getNumber(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            return num;
        } catch (Exception e) {
            System.out.println("Khong dung dinh dang");
            return getNumber(str);
        }

    }

    public static int getNumber(String str, int min, int max) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            //min = 1 max = 7
            if(num < min || num > max) {
                System.err.println("Chọn từ khoảng " + min + " và " + max);
                return getNumber(str,min,max);
            }
            return num;
        } catch (Exception e) {
            System.err.println("Khong dung dinh dang");
            return getNumber(str,min,max);
        }

    }
    public static Date getDate(String str){
        System.out.println(str);
        System.out.println("Format yyyy-mm-dd, Example: 2023-03-30");
        try{
            return Date.valueOf(sc.nextLine());
        }catch (Exception e){
            return getDate(str);
        }

    }


    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }
}
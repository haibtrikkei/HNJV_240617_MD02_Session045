package ra.run;

import ra.entity.Categories;
import ra.entity.Product;
import ra.service.CategoryService;

import java.util.Scanner;

public class ShopManagement {
    //Sử dụng currentCategory để lưu lại tổng số category đã được nhập
    public static int currentCategory = 0;
    //Sử dụng currentProduct để lưu lại tổng số product đã được nhập
    public static int currentProduct = 0;

    //Sử dụng các mảng kiểu static để lưu và cập nhật lại các dữ  liệu  được thay đổi
    public static Categories[] arrCategories = new Categories[100];
    public static Product[] arrProducts = new Product[100];

    public static void main(String[] args) {


        CategoryService categoryService = new CategoryService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("---------------------------SHOP MENU---------------------------");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            choice = inputChoice(scanner);
            switch (choice){
                case  1:
                    categoryService.categoryManagement(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không đúng");
            }
        }
    }

    public static int inputChoice(Scanner scanner) {
        System.out.println("Nhập vào lựa chọn: ");
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch (Exception ex){
                System.out.println("lựa chọn phải là số");
            }
        }
    }


}

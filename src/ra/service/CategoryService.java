package ra.service;

import ra.entity.Categories;
import ra.run.ShopManagement;

import java.util.Scanner;

public class CategoryService {


    public void categoryManagement(Scanner scanner) {
        int choice;
        boolean flag = true;
        while(flag){
            System.out.println("---------------------------CATEGORIES MENU---------------------------");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");

            choice = ShopManagement.inputChoice(scanner);
            switch (choice){
                case 1:
                    inputListCategories(scanner);
                    break;
                case 2:
                    displayListCategory();
                    break;
                case  3:
                    updateCategory(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Bạn chỉ được chọn từ 1 đến 6");
            }
        }
    }

    private void updateCategory(Scanner scanner) {
        int cateId;
        System.out.println("Nhập mã danh mục cần cập nhật: ");
        while(true){
            try{
                cateId = Integer.parseInt(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println("Ma danh muc phai la so");
            }
        }

        if(!isExistCatalogId(cateId)){
            System.out.println("Mã danh mục không tồn tại");
        }else{
            Categories c = new Categories();
            c.setCatalogId(cateId);
            System.out.println("Nhập thông tin danh muc cần update: ");
            c.inputDataUpdate(scanner,ShopManagement.arrCategories);

            //Duyệt mảng và thay thế đối tượng có cùng id:
            for(int i=0;i<ShopManagement.currentCategory;i++){
                if(ShopManagement.arrCategories[i].getCatalogId()==cateId){
                    ShopManagement.arrCategories[i] = c;
                    System.out.println("Đã cập nhật thông tin");
                    break;
                }
            }
        }
    }

    private boolean isExistCatalogId(int cateId) {
        for(Categories c: ShopManagement.arrCategories){
            if(c.getCatalogId()==cateId)
                return true;
        }
        return false;
    }

    private void displayListCategory() {
        for(Categories c: ShopManagement.arrCategories){
            if(c!=null)
                c.displayData();
        }
    }

    private void inputListCategories(Scanner scanner) {
        int n;
        System.out.println("Nhập số danh mục cần nhập: ");
        while(true){
            try{
                n = Integer.parseInt(scanner.nextLine());
                if(n<1)
                    System.out.println("Số danh mục phải là số dương");
                else
                    break;
            }catch (Exception e){
                System.out.println("Số danh mục là số nguyên");
            }
        }
        for(int i=ShopManagement.currentCategory;i<ShopManagement.currentCategory+n;i++){
            System.out.println("Nhập thông tin danh muc thứ "+(i+1));
            Categories c = new Categories();
            c.inputData(scanner,ShopManagement.arrCategories,i);
            ShopManagement.arrCategories[i] = c;
        }
        //Cập nhật biến ở bên hàm main
        ShopManagement.currentCategory = ShopManagement.currentCategory+n;
    }

}

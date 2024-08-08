package ra.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int index, Categories[] arrCategories){
        productId = inputProductId(scanner, arrProduct);
        productName = inputProductName(scanner, arrProduct);
        price = inputPrice(scanner);
        description = inputDescription(scanner);
        created = inputCreated(scanner);
        catalogId = inputCatalogId(scanner, arrCategories);
        productStatus = inputProductStatus(scanner);
    }

    private int inputProductStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái sản phẩm: ");
        int status;
        while (true){
            try{
                status = Integer.parseInt(scanner.nextLine());
                if(!(status==1 || status==2 || status==0)){
                    System.out.println("Trạng thái sản phẩm phải nhập là 0,1 hoặc 2");
                }else{
                    return status;
                }
            }catch (Exception e){
                System.out.println("Trạng thái sản phẩm phải là số");
            }
        }
    }

    private int inputCatalogId(Scanner scanner, Categories[] arrCategories) {
        System.out.println("Danh sách catalog: ");
        int cateId;
        while (true){
            System.out.println("Nhập mã danh mục: ");
            for(Categories c: arrCategories){
                if(c!=null)
                    System.out.println(c.getCatalogId()+" - "+c.getCatalogName());
            }
            try{
                cateId = Integer.parseInt(scanner.nextLine());
                if (!isExistedCatalogId(cateId, arrCategories)){
                    System.out.println("Mã danh mục không tồn tại");
                }else{
                    return cateId;
                }
            }catch (Exception ex){
                System.out.println("Mã danh mục phải là số");
            }
        }
    }

    private boolean isExistedCatalogId(int cateId, Categories[] arrCategories) {
        for(Categories c : arrCategories){
            if(c.getCatalogId()==cateId)
                return true;
        }
        return false;
    }

    private Date inputCreated(Scanner scanner) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo");
        while(true){
            try{
                return sf.parse(scanner.nextLine());
            }catch (Exception ex){
                System.out.println("Ngày không hợp lệ");
            }
        }
    }

    private String inputDescription(Scanner scanner) {
        System.out.println("Nhập mô tả sản phẩm: ");
        return scanner.nextLine();
    }

    private float inputPrice(Scanner scanner) {
        System.out.println("Nhập giá sản phẩm: ");
        float price;
        while(true){
            try{
                price =  Float.parseFloat(scanner.nextLine());
                if(price<0)
                    System.out.println("Giá sản phẩm không hợp lệ");
                else
                    return price;
            }catch (Exception ex){
                System.out.println("Giá sản phẩm phải là số");
            }
        }
    }


    private String inputProductName(Scanner scanner, Product[] arrProduct) {
        System.out.println("Nhập tên sản phẩm: ");
        String name;
        while (true){
            name = scanner.nextLine();
            if(name.length()<10 || name.length()>50){
                System.out.println("Tên sản phẩm từ 10 đến 50 kí tự");
            }else if(isExistedProductName(name, arrProduct)){
                System.out.println("Tên sản phẩm đã tồn tại");
            }else {
                return name;
            }
        }
    }

    private boolean isExistedProductName(String name, Product[] arrProduct) {
        for(Product p : arrProduct){
            if(p.getProductName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    private String inputProductId(Scanner scanner, Product[] arrProduct) {
        System.out.println("Nhập product id: ");
        String id;
        while(true){
            id = scanner.nextLine();
            if(id.length()!=4){
                System.out.println("Product id phải nhập chính xác 4 kí tự");
            }else if(!(id.charAt(0)=='C' || id.charAt(0)=='S' || id.charAt(0)=='A')){
                System.out.println("Mã sản phẩm phải bắt đầu bằng C, S hoặc A");
            }else if(isExistedProductId(id, arrProduct)){
                System.out.println("Mã sản phẩm đã tồn tại");
            }else{
                return id;
            }
        }
    }

    public boolean isExistedProductId(String productId, Product[] arrProduct){
        for(Product p : arrProduct){
            if(p.getProductId().equalsIgnoreCase(productId))
                return true;
        }
        return false;
    }

    public void displayData(){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-106s\n","----------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-6s|%-30s|%-7.0f|%-30s|%-10s|%-5d|%-10s|\n",productId,productName,price,description,sf.format(created),catalogId,getProductStatus()==0?"Đang bán":(getProductStatus()==1?"Hết hàng":"Không bán"));
    }
    public void endTable(){
        System.out.printf("%-106s\n","----------------------------------------------------------------------------------------------------------");
    }
}


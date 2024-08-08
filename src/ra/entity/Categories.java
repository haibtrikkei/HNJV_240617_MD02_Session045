package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private Boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, Boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int index){
        catalogId = inputCatalogId(scanner, arrCategories, index);
        catalogName = inputCatalogName(scanner, arrCategories);
        descriptions = inputDescription(scanner);
        catalogStatus = inputCatalogStatus(scanner);
    }

    public void inputDataUpdate(Scanner scanner, Categories[] arrCategories){
        catalogName = inputCatalogName(scanner, arrCategories);
        descriptions = inputDescription(scanner);
        catalogStatus = inputCatalogStatus(scanner);
    }

    private Boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập catalog status: ");
        while (true){
            try{
                return Boolean.parseBoolean(scanner.nextLine());
            }catch (Exception ex){
                System.out.println("Catalog status chỉ được nhập là true hoặc false");
            }
        }
    }

    private String inputDescription(Scanner scanner) {
        System.out.println("Nhập description: ");
        return scanner.nextLine();
    }

    private String inputCatalogName(Scanner scanner, Categories[] arrCategories) {
        System.out.println("Nhập catalog name: ");
        String name;
        while(true){
            name = scanner.nextLine();
            if(name.length()>50){
                System.out.println("Catalog name tối đa 50 kí tự");
            }else if(isExistedCatalogName(name, arrCategories)){
                System.out.println("Catalog name đã tồn tại");
            }else{
                return name;
            }
        }
    }

    private int inputCatalogId(Scanner scanner, Categories[] arrCategories, int index) {
        //xu ly catalogId:
        if(index==0){
            return 1;
        }else{
            return arrCategories[index-1].getCatalogId()+1;
        }
    }

    public boolean isExistedCatalogName(String catalogName, Categories[] arrCategories){
        for(Categories c : arrCategories){
            if(c!=null && c.getCatalogName().equalsIgnoreCase(catalogName.trim())){
                return true;
            }
        }
        return false;
    }

    public void displayData(){
        System.out.printf("%-76s\n","----------------------------------------------------------------------------");
        System.out.printf("|%-5d|%-30s|%-30s|%-7s|\n",catalogId,catalogName,descriptions,catalogStatus?"active":"nonactive");
    }
    public void endTable(){
        System.out.printf("%-76s\n","----------------------------------------------------------------------------");
    }
}

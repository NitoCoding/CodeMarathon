package mdbudget.models;

public class Menu extends BaseModel {
    public int menuId;
    public String menuNama;
    public int menuHarga;
    public String menuGambar;
    
    public Menu(int id, String nama, int menuHarga, String gambar) {
        this.menuId = id;
        this.menuNama = nama;
        this.menuHarga = menuHarga;
        this.menuGambar = gambar;
    }
    
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public String getMenuNama() {
        return menuNama;
    }
    
    public void setMenuNama(String menuNama) {
        this.menuNama = menuNama;
    }

    public int getMenuHarga() {
        return menuHarga;
    }

    public void setMenuHarga(int menuHarga) {
        this.menuHarga = menuHarga;
    }

    public String getMenuGambar() {
        return menuGambar.toString();
    }

    public void setMenuGambar(String menuGambar) {
        this.menuGambar = menuGambar;
    }

    @Override
    public void displayInfo() {
        System.out.println("Menu ID: " + getMenuId());
        System.out.println("Nama: " + getMenuNama());
        System.out.println("Harga: " + getMenuHarga());
        System.out.println("Gambar: " + getMenuGambar());
    }
}

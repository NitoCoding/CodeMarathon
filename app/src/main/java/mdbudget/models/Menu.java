package mdbudget.models;

public class Menu {
    public int menuId;
    public String menuNama;
    public String menuKategori;
    public int menuHarga;
    public String menuGambar;

    public Menu(int id, String nama, String kategori, int harga, String gambar) {
        this.menuId = id;
        this.menuNama = nama;
        this.menuKategori = kategori;
        this.menuHarga = harga;
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

    public String getMenuKategori() {
        return menuKategori;
    }

    public void setMenuKategori(String menuKategori) {
        this.menuKategori = menuKategori;
    }

    public int getMenuHarga() {
        return menuHarga;
    }

    public void setMenuHarga(int menuHarga) {
        this.menuHarga = menuHarga;
    }

    public String getMenuGambar() {
        return menuGambar;
    }

    public void setMenuGambar(String menuGambar) {
        this.menuGambar = menuGambar;
    }

    
}

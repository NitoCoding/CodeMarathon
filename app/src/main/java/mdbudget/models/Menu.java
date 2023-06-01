package mdbudget.models;

public class Menu extends BaseModel{
    public String menuNama;
    public String menuKategori;
    public int menuHarga;
    public String menuGambar;

    public Menu(int id, String nama, String kategori, int harga, String gambar) {
        super(id);
        this.menuNama = nama;
        this.menuKategori = kategori;
        this.menuHarga = harga;
        this.menuGambar = gambar;
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
        return menuGambar.toString();
    }

    public void setMenuGambar(String menuGambar) {
        this.menuGambar = menuGambar;
    }

    @Override
    public void displayInfo() {
        System.out.println("Menu ID: " + getId());
        System.out.println("Nama: " + getMenuNama());
        System.out.println("Kategori: " + getMenuKategori());
        System.out.println("Harga: " + getMenuHarga());
        System.out.println("Gambar: " + getMenuGambar());
    }
}

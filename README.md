# McBudget

McBudget adalah aplikasi penjualan burger sederhana yang diperuntukan
bagi orang yang menyukai burger dengan berbagai macam rasa. Aplikasi ini
membantu pengguna memilih dan menemukan burger yang disukai dan diinginkan.
Aplikasi ini terinspirasi dari aplikasi grab atau semacamnya, hanya saja pada aplikasi
ini pemilihan jenis makanan lebih spesifik tentang burger.

## syarat

- gradle 8.1.1
- java jdk 17.0.7
- sqlite3
- sqlite jdbc 3.41.2.1

## cara menjalankan project 

1. clone project
```
git clone https://github.com/NitoCoding/CodeMarathon.git
```
2. build gradle 
```
gradle build
```
3. jalankan project
```
gradlew run
```

## fitur 

1. Dapat meminta user untuk Login terlebih dahulu sebelum memasuki aplikasi dengan memasukkan Username dan Password dan dikonfirmasi lewat database
2. Terdapat fitur Register bagi pengguna yang belum pernah login
3. Terdapat juga fitur login admin untuk mengakses fitur khusus admin
4. Menampilkan tampilan menu dengan berbagai macam burger
5. Menampilkan fitur admin untuk mellihat daftar order yang telah dilakukan, daftar menu yang tersedia, dan daftar user yang ada
6. Menampilkan tampilan checkout list untuk menampilkan jenis burger dan list harga
7. Setelah pesanan dikonfirmasi, pengguna bisa menekan tombol log out untuk keluar dari aplikasi



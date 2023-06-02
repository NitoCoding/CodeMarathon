@echo off

set DB_FILE=app/db/MDBudget.db
set SQL_FILE=app/db/MDBudget.sql

rem Cek apakah file database ada
if exist "%DB_FILE%" (
    echo Deleting existing database file...
    del "%DB_FILE%"
    echo Database file deleted.
)

rem Jalankan perintah untuk membaca kode SQL
sqlite3 "%DB_FILE%" < "%SQL_FILE%"

echo SQL execution completed.

pause
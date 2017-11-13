CREATE DATABASE Library;
USE Library;

CREATE TABLE Sach
(
	Masach   CHAR(8) PRIMARY KEY,
	Tensach  NVARCHAR(50),
	Tacgia   NVARCHAR(50),
    NXB      NVARCHAR(50),
    Theloai  NVARCHAR(50),
	NamXB    CHAR(4),
	Soluong  INT
);

create TABLE Docgia
(
	MaDG    CHAR(8) PRIMARY KEY,
	TenDG   NVARCHAR(50),
	Namsinh CHAR(4),
    Gioitinh NVARCHAR(4),
	Quequan NVARCHAR(30),
    Diachi  NVARCHAR(50),
	SDT     CHAR(15)
);

create TABLE Nhanvien
(
	MaNV    CHAR(8) PRIMARY KEY,
	TenNV   NVARCHAR(50),
	Namsinh CHAR(4),
    Gioitinh NVARCHAR(4),
	Quequan NVARCHAR(30),
    Diachi  NVARCHAR(50),
	SDT     CHAR(15)
);

CREATE TABLE Muontra
(
	MaMT CHAR(8) PRIMARY KEY,
	MaDG CHAR(8),
    MaNV CHAR(8),
	Ngaymuon CHAR(10),
	Ngayhentra CHAR(10),
    Tiencoc INT,
	FOREIGN KEY (MaDG) REFERENCES Docgia(MaDG),
    foreign key (MaNV) references Nhanvien(MaNV)
);

CREATE TABLE Chitietmuon
(
	Mamuon CHAR(8),
	Masachmuon CHAR(8),
    Soluongmuon INT,
	Ngaytra CHAR(10),
	Sotienphat FLOAT,
	PRIMARY KEY (Mamuon, Masachmuon),
	FOREIGN KEY (Mamuon) REFERENCES Muontra(MaMT),
	FOREIGN KEY (Masachmuon) REFERENCES Sach(Masach)
);
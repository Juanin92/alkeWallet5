create database if not exists alkeWallet5;
use alkeWallet5;

create table usuarios(
	id int primary key auto_increment,
	nombre varchar(20) not null,
	apellido varchar(20) not null,
	correo varchar(25) not null,
	contrasena varchar(20) not null,
	saldo decimal(10,2) not null
);

select * from usuarios;
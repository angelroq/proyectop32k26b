CREATE TABLE Comisiones Vendedores (
    id_comision INT PRIMARY KEY AUTO_INCREMENT,
    id_vendedor INT,
    monto_ventas DECIMAL(10,2),
    meta DECIMAL(10,2),
    ventas_adicionales DECIMAL(10,2),
    comision DECIMAL(10,2),
    FOREIGN KEY (id_vendedor) 
   REFERENCES Vendedores(id_vendedor)
   )ENGINE=InnoDB CHARACTER SET=LATIN1;
INSERT INTO aplicaciones (Aplcodigo, Aplnombre, Aplestado) VALUES (5050, "Comisiones Vendedores", 1)

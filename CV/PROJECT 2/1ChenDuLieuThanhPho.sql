select * from Phuong;
select * from Huyen;
select * from HanhChinh;



INSERT INTO Tinh(id,ThanhPho)
SELECT 
     distinct MaTP,ThanhPho
FROM 
	 HanhChinh
     

INSERT INTO Huyen(id, TenHuyen,ThanhPho_id)
SELECT 
    distinct MAQH,QuanHuyen,MaTP
FROM 
    HanhChinh;



INSERT INTO Phuong(id, TenPhuong,Huyen_id)
SELECT 
    distinct id,TenPhuong,MAQH
FROM 
    HanhChinh


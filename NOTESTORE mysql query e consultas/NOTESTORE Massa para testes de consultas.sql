insert into filial values(default, 'SÃO PAULO', 'SP');
insert into filial values(default, 'RIO DE JANEIRO', 'RJ');
insert into filial values(default, 'MINAS GERAIS', 'MG');

insert into produto values(default, 1, "notebooke-acer",'acer',10,5000,'note-gamer',now());
insert into produto values(default, 2, "notebooke-sony-3",'sony-3',50,5000,'note-sony',now());
insert into produto values(default, 3, "notebooke-dell-3",'dell-3',20,2000,'note-dell',now());
insert into produto values(default, 3, "notebooke-sam-3",'sam-3',30,4000,'note-sam',now());

insert into cliente values(default,1, "cliente1",53535633,now(),'masculino',504300,'note@gamer','SP','São Paulo',09867,'Bairro.cliente1',12);
insert into cliente values(default,1, "cliente2",53535633,now(),'masculino',504300,'note@gamer','SP','São Paulo',09867,'Bairro.cliente1',12);
insert into cliente values(default,2, "cliente3",53535633,now(),'masculino',504300,'note@gamer','SP','São Paulo',09867,'Bairro.cliente1',12);

insert into venda values(default,now(),10,1,1);
insert into venda values(default,now(),20,2,2);
insert into venda values(default,now(),20,2,2);
insert into venda values(default,now(),30,2,3);
insert into venda values(default,now(),70,4,2);

insert into venda_produto values(default, 5, 1,1);
insert into venda_produto values(default, 10, 1,2);
insert into venda_produto values(default, 20, 1,3);
insert into venda_produto values(default, 30, 1,2);
insert into venda_produto values(default, 70, 6,1);


-- teste com algumas mudanças
insert into filial values(default, 'SAO PAULO', 'SP');
insert into filial values(default, 'RIO DE JANEIRO', 'RJ');
insert into filial values(default, 'MINAS GERAIS', 'MG');

insert into produto values(default, 1, "notebooke-LG",'acer',10,5000,'note gamer',now());
insert into produto values(default, 3, "notebooke-sony-3",'sony-3',50,5000,'note gamer-sony',now());
insert into produto values(default, 3, "notebooke-dell-3",'dell-3',20,2000,'note gamer-dell',now());
insert into produto values(default, 3, "notebooke-sam-3",'sam-3',30,4000,'note gamer-sam',now());

insert into cliente values(default,1, "cliente1",now(),'masculino',53535633,504300,'note@gamer','SP','São Paulo',09867,'Bairro.cliente1',12);
insert into cliente values(default,1, "cliente2",now(),'masculino',53535633,504300,'note@gamer','SP','São Paulo',09867,'Bairro.cliente1',12);
insert into cliente values(default,3,"cliente3",now(),'masculino',53535633,504300,'note@gamer','MG','Minas Gerais',09867,'Bairro.cliente1',12);


insert into venda values(default,now(),10,1,1);
insert into venda values(default,now(),20,2,2);
insert into venda values(default,now(),30,2,2);
insert into venda values(default,now(),40,2,3);
insert into venda values(default,now(),60,1,3);
insert into venda values(default,now(),70,1,1);
insert into venda values(default,now(),80,3,2);

insert into venda_produto values(default, 5, 2,1);
insert into venda_produto values(default, 10, 3,2);
insert into venda_produto values(default, 20, 4,3);
insert into venda_produto values(default, 30, 4,1);
                    

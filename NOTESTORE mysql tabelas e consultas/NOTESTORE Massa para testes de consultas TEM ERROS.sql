insert into filial values(default, 'SÃO PAULO', 'SP');
insert into filial values(default, 'RIO DE JANEIRO', 'RJ');
insert into filial values(default, 'MINAS GERAIS', 'MG');

insert into produto values(default, 1, "notebooke-acer",'acer',10,5000,'note-gamer',now());
insert into produto values(default, 2, "notebooke-sony",'sony-3',50,5000,'note-sony',now());
insert into produto values(default, 1, "notebooke-dell",'dell-3',20,2000,'note-dell',now());
insert into produto values(default, 3, "notebooke-sam",'sam-3',30,4000,'note-sam',now());

insert into cliente values(default, 1, 'Douglas','333.333.333-31',now(),'masculino',504300,'gamertester@email','SP','São Paulo',09867-000,'Bairro1',10);
insert into cliente values(default,1, 'Manuel','333.333.333-32',now(),'masculino',504300,'note@gamer','SP','São Paulo',09867-000,'Bairro2',11);
insert into cliente values(default,2, 'Rodnei','333.333.333-33',now(),'masculino',504300,'note@gamer','SP','São Paulo',09867-000,'Bairro3',12);

insert into venda values(default,now(),10,1,1);
insert into venda values(default,now(),20,2,2);
insert into venda values(default,now(),20,2,2);
insert into venda values(default,now(),30,2,3);
insert into venda values(default,now(),70,2,2);

insert into venda_produto values(default, 5, 1,1);
insert into venda_produto values(default, 10, 1,2);
insert into venda_produto values(default, 20, 1,3);
insert into venda_produto values(default, 30, 1,4);
insert into venda_produto values(default, 70, 4,5);

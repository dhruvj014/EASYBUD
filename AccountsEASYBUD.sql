use easybud;

select * from login;

create table moneydata(email varchar(30),
					   date varchar(50),
                       accounttype varchar(40),
                       transactype varchar(40),
                       amount varchar(25));
                       
create table newdatabase(email varchar(30),
						transactype varchar(40),
					   purpose varchar(50),
                       amount double,
                       category varchar(40),
                       date varchar(40));

create table signup(email varchar(30),
					firstName varchar(30),
                    lastName varchar(30),
                    birthdate varchar(30),
                    sex varchar(15),
                    pass varchar(30));

create table signuptwo(email varchar(30),
					secQuestion varchar(50),
					secAnswer varchar(30));

create table login(email varchar(30),
					pass varchar(30));
                    
drop table newdatabase;

drop table signup;
drop table login;
drop table signuptwo;

     
select * from login;
select * from signup;
select * from signuptwo;
select * from newdatabase;

drop table moneydata;

select * from moneydata;
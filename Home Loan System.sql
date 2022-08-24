create database home_loan;
use home_loan;

-- Note:Foreign Key is not used here as it may cause some problems during a transaction,
-- so for simplicity purposes I'm dropping the option of using foreign key


-- Note: For loading an image following command should be used
-- INSERT INTO loan_application (doc) VALUES(LOAD_FILE('/image_path/image_fileName.png'));

-- signup table
create table signup(id int auto_increment, username varchar(100) not null, 
phone_no varchar(10) not null unique, mail varchar(50) not null unique, 
pass varchar(50) not null, confirm_pass varchar(50) not null, state varchar(50) not null,
city varchar(50) not null, country varchar(50) not null,pincode varchar(10),primary key(id));

create database home_loan;
use home_loan;

-- drop database home_loan;

-- drop table signup;
-- drop table login;

-- Note:Foreign Key is not used here as it may cause some problems during a transaction,
-- so for simplicity purposes I'm dropping the option of using foreign key


-- Note: For loading an image following command should be used
-- INSERT INTO loan_application (doc) VALUES(LOAD_FILE('/image_path/image_fileName.png'));

-- signup table
create table signup(
saving_acc_no int auto_increment not null,
username varchar(100) not null, account_type varchar(10) not null,
phone_no varchar(10) not null unique, mail varchar(50) not null unique, 
address varchar(500) not null,pass varchar(50) not null, confirm_pass varchar(50) not null, 
state varchar(50) not null,city varchar(50) not null, country varchar(50) not null,
pincode varchar(10),primary key(saving_acc_no),index(account_type),index(pass),index(username),
index(mail)
);

-- drop table login;
-- drop table signup;

-- insert into signup(username,account_type,phone_no,mail,address,pass,confirm_pass,state,city,country,pincode)
--  values('Karthi','user','8939212299','arthi31342keyan@gmail.com','313/42,TYZ street','xyz','xyz','TN','CHN','IND','600001');

-- insert into signup(username,account_type,phone_no,mail,address,pass,confirm_pass,state,city,country,pincode)
-- values('Karthi','user','8939212290','karthi31342keyan@gmail.com','313/42,TYZ street','xyz','xyz','TN','CHN','IND','600001');
--  
 -- select * from signup;
-- Login table 
create table login(log_id int auto_increment,saving_acc_no int not null, pass varchar(50) not null, account_type varchar(10) not null, primary key(log_id),
CONSTRAINT fk_login foreign key(saving_acc_no) references signup(saving_acc_no),
CONSTRAINT fk_acc_type foreign key(account_type) references signup(account_type),
CONSTRAINT fk_acc_pass foreign key(pass) references signup(pass),index(saving_acc_no)
);

-- insert into login(saving_acc_no, pass,account_type) values (1,'xyz','user');
-- insert into login(saving_acc_no, pass,account_type) values (2,'xyz','user');

 
 
 
 
 
 -- Morning create index and foreign keys in correct format by referencing the login and signup tables
 
 
 

-- Loan Application table
create table loan_application(
loan_id int auto_increment not null, 
saving_acc_no int not null unique, address_prop varchar(500) not null, 
loan_amt double not null, tenure int not null, net_month_sal double not null, 
doc blob not null, status varchar(10) not null,primary key(loan_id),
CONSTRAINT fk_sav_acc foreign key (saving_acc_no) references signup(saving_acc_no),
index(tenure),index(loan_amt),index(status)
);

-- Loan Account table
create table loan_acc(l_id int auto_increment,loan_id int not null unique, 
saving_acc_no int not null unique, loan_amt double not null, 
interest double not null, tenure int not null, status varchar(30),primary key(l_id),
constraint fk_loan_acc foreign key(loan_id) references loan_application(loan_id),
constraint fk_ten foreign key(tenure) references loan_application(tenure),
constraint fk_sav_acc_1 foreign key(saving_acc_no) references signup(saving_acc_no),
constraint fk_loan_amt foreign key(loan_amt) references loan_application(loan_amt),
constraint fk_stat foreign key(status) references loan_application(status)
);

-- Savings Account table
create table savings_acc(
sav_id int auto_increment,saving_acc_no int not null unique,
username varchar(100) not null,mail varchar(50) not null unique, balance double not null,
primary key(sav_id),constraint fk_sav_acc_2 foreign key(saving_acc_no) references signup(saving_acc_no),
constraint fk_user foreign key(username) references signup(username),
constraint fk_user_mail foreign key(mail) references signup(mail), index(balance)
);

-- Loan repayment table

-- dor = date of return
create table loan_repay(repay_id int auto_increment, loan_id int not null unique, 
saving_acc_no int not null unique, dor date not null, emi double not null, 
principal double not null, interest_amount double not null, no_of_emi int not null,
outstanding_amt double not null, status varchar(30) not null,primary key(repay_id),
constraint fk_sav_acc_3 foreign key(saving_acc_no) references signup(saving_acc_no),
constraint fk_loan_acc_1 foreign key(loan_id) references loan_application(loan_id)
);

create table credit(id int auto_increment, saving_acc_no int not null unique,amount double not null,balance double not null,
primary key(id),
constraint fk_sav_acc_4 foreign key(saving_acc_no) references signup(saving_acc_no),
constraint fk_bal foreign key(balance) references savings_acc(balance)
);

create table debit(id int auto_increment, saving_acc_no int not null unique,amount double not null,balance double not null,
primary key(id),
constraint fk_sav_acc_5 foreign key(saving_acc_no) references signup(saving_acc_no),
constraint fk_bal_1 foreign key(balance) references savings_acc(balance)
);
-- create table transaction(trans_id int auto_increment,loan_id int not null unique, 
-- savings_acc_no int not null unique, balance double not null, primary key(trans_id));

 

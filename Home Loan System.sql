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
city varchar(50) not null, country varchar(50) not null,primary key(id));

-- Login table 
create table login(log_id int, pass varchar(50) not null, account_type varchar(10) 
not null, primary key(log_id));

-- Loan Application table
create table loan_application(loan_app_id int auto_increment,loan_id int not null unique, 
saving_acc_no int not null unique, address_prop varchar(500) not null, 
loan_amt double not null, tenure int not null, net_month_sal double not null, 
doc blob not null, primary key(loan_app_id));

-- Loan Account table
create table loan_acc(l_id int auto_increment,loan_id int not null unique, 
saving_acc_no int not null unique, loan_amt double not null, 
interest double not null, tenure int not null, status varchar(30),primary key(l_id));

-- Savings Account table
create table savings_acc(sav_id int auto_increment,saving_acc_no int not null unique,
username varchar(100) not null,mail varchar(50) not null unique, balance double not null,
primary key(sav_id));

-- Loan repayment table

-- dor = date of return
create table loan_repay(repay_id int auto_increment, loan_id int not null unique, 
saving_acc_no int not null unique, dor date not null, emi double not null, 
principal double not null, interest_amount double not null, no_of_emi int not null,
outstanding_amt double not null, status varchar(30) not null,primary key(repay_id));

create table transaction(trans_id int auto_increment,loan_id int not null unique, 
savings_acc_no int not null unique, balance double not null, primary key(trans_id));

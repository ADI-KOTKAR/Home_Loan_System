create database bank;
use bank;

-- Users 
create table users(
	login_id int not null auto_increment,
    password varchar(20),
    primary key(login_id)
);

-- Savings Account 
create table savings_account(
	sequence_id int not null,
    account_no int not null,
    login_id int not null,
    name varchar(20) not null,
    email varchar(20),
    address varchar(50),
    current_balance double,
	primary key(account_no),
    CONSTRAINT fk_login_id foreign key (login_id) references users(login_id)
);

-- Loan Application 
create table loan_application(
	loan_id int auto_increment not null, 
	savings_acc_no int not null unique,
    address_property varchar(500) not null, 
	loan_amt double not null, 
    tenure int not null, 
    net_month_sal double not null, 
	doc blob not null, 
    status enum("Pending Approval", "Approved") not null,
    primary key(loan_id),
	CONSTRAINT fk_sav_acc foreign key (savings_acc_no) references savings_account(account_no),
	index(tenure), index(loan_amt), index(status)
);

-- Loan Account 
create table loan_account(
	loan_account_id int not null auto_increment, 
	savings_account_no int not null,
    loan_amt double not null,
	interest_rate double not null, 
    tenure int not null, 
    status enum("Approved", "Ongoing", "Closed"),
    primary key(loan_account_id),
	constraint fk_ten foreign key(tenure) references loan_application(tenure),
	constraint fk_sav_acc_1 foreign key(savings_account_no) references savings_account(account_no),
	constraint fk_loan_amt foreign key(loan_amt) references loan_application(loan_amt)
);

-- Loan Repayment Schedule 
create table loan_repayment(
	lr_id int auto_increment, 
    loan_account_id int not null unique, 
	savings_acc_no int not null unique, 
    yrs int not null,
    months int not null,
    emi double not null, 
	principal_amt double not null, 
    interest_amt double not null, 
	outstanding_amt double not null, 
    status enum("Pending", "Paid", "Cancelled") not null,
    primary key(lr_id),
	constraint fk_sav_acc_3 foreign key(savings_acc_no) references savings_account(account_no),
	constraint fk_loan_acc_1 foreign key(loan_account_id) references loan_account(loan_account_id)
);




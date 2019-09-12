CREATE TABLE AUTH_BILLING_EVENT_DETAIL
(
PRCSS_DT varchar(8) NOT NULL, 
IME_TRACE_ID varchar(46) NOT NULL, 
GUIDE_POINT_ID	varchar(20) NOT NULL, 
GUIDE_POINT_PARTY_ID varchar(10) NOT NULL, 
BILL_EVENT_ID varchar(11) NOT NULL, 
QTY_NUM		varchar(8),
CONV_AMT	varchar(8), 
CONV_CURR_CD varchar(3),
SEQ_NUM	varchar(10) NOT NULL,
CHRG_AMT varchar(8),
PRIMARY KEY (BILL_EVENT_ID),
CONSTRAINT AUTH_BILLING_EVENT_DETAIL_FK foreign KEY (IME_TRACE_ID) references 
auth_transaction_detail(IME_TRACE_ID)
);
insert into auth_billing_event_detail values('05-09-19','IME1','G2','GP2','B2','Q1','102','C2','12','200');
======================================================================
insert into auth_transaction_detail values('IME2',1002,'B','ICA2','ACQ2');

CREATE table AUTH_TRANSACTION_DETAIL
(
IME_TRACE_ID		varchar(46) NOT NULL, 
MTI_FUNC_CD			int(4), 
TRAN_TYPE_CD	varchar(1),
ISS_ICA_NUM		varchar(11),
ACQ_ICA_NUM			varchar(11),
CONSTRAINT AUTH_TRANSACTION_DETAIL_PK PRIMARY KEY (IME_TRACE_ID)
)
==========================

select  MTI_FUNC_CD, TRAN_TYPE_CD, ISS_ICA_NUM, ACQ_ICA_NUM from auth_billing_event_detail bd,
auth_transaction_detail td where bd.IME_TRACE_ID=td.IME_TRACE_ID;

===============
CREATE TABLE USER_ROLES (
   role_Name varchar(20),
   role_Description varchar(100),
   ICA varchar(20),
   STATUS varchar(20),
   user_Id varchar(20) NOT NULL,
   PRIMARY KEY (user_Id)
);

=========
CREATE TABLE ROLE_MAPPING (
   role_Name varchar(20),
   download_Detail_Count varchar(20),
   download_Summary_Count varchar(20),
   summary_Response_Count varchar(20),
   detail_Response_Count varchar(20),
   PRIMARY KEY (role_Name)
);

=======
CREATE TABLE ELEMENT_MAPPING (
   FEEDER_TYPE varchar(20),
   ELEMENTS varchar(100),
   ROLE varchar(20),
   ENABLE CHAR(1),
   AS_FIELDS varchar(20) NOT NULL
);






Canang Finance for Government
=============================

##Introduction
Canang Finance for Gov is an open source financial container written entirely 
in java on top of Spring, Activiti and GWT framework. It is targeted to facilitate
government's day to day business in finance by providing robust and specialized 
workflows and key feature sets meant for Kementerian, Jabatan and Institutions of 
Malaysia.

###Supports
Canang offers commercial, implementation and training supports which is offered through email and
our forums.


## Key Features and Technologies

### Spring Framework
Spring is the enterprise Java platform on which Canang Finance is based.  It provides numerous features, including dependency 
injection and transaction control.

### Security
Spring Security provides a robust security framework for controlling authentication and authorization at both the code and page level 
and is utilized by Canang Finance for access control.

### Persistence
Hibernate represent the Canang Finance ORM infrastructure for controlling persistence of our deep and rich domain model.

### Search
Flexible domain search capabilities in Canang Finance are provided through integration
with Solr.

### Task Scheduling
Scheduling of repetitive tasks in Canang Finance is offered through the 
Quartz job scheduling system.

### Email
Email support is provided throughout the Canang Finance framework in either synchronous 
or asynchronous (JMS) modes. 

### Modular Design
Important finance domain and workflow are embodied in the concept of Canang Finance.
Any number of custom domains and workflows may be developed and utilized with Canang Finance.

### Configurable Workflows
Key areas in the e-commerce lifecycle are represented as configurable 
workflows. Implementors have full control over the keys steps in pricing and checkout, allowing 
manipulation of module ordering, overriding existing module behavior and custom module execution. 
Composite workflows are also supported to achieve more exotic, nested behavior.

### Extensible Design
Canang Finance is designed from the ground-up with extensibility in mind. 
Almost every aspect of Canang Finance can be overridden, added to or otherwise modified to enhance 
or change the default behavior to best fit your needs. This includes all of our services, data access 
objects and entities. Please refer to the extensibility section of our documentation.



### Modules
* 00 SO - Shared Object / common
* 01 AM - Access Management / acces
* 02 PM - Process Management / process
* 03 HM - History Management / history
* 04 DD - Data Dictionary / dictionary
* 05 GL - General Ledger  / ledger
* 06 VM - Vote Management / vote
* 07 JM - Journal Management  / journal
* 08 BM - Budget Management   / budget
* 09 DM - Disbursement Management / disbursement
* 10 AP - Account Payable         / payable
* 11 AR - Account Receivable      / receivable


###Extension Points
* custom data asas
* document extension (Journal MANUAL, AUTO);
* reference no (campus, dept etc)
* secondary refno (journal no, voucher no)
* queries, workflow with queries/written direction
* workflow basic PTJ -> BENDAHARI, no zone, shared, group etc
* currency in receipt
* sub type in receipt
* upper approval in workflow
* complex router
* cost center structure
* item code / sodo code customization, basic only use sodo code

###Permission
* VIEW
* READ
* WRITE
* UPDATE
* DELETE


###Role
* PrincipalRole.GUEST
* PrincipalRole.USER
* PrincipalRole.ADMINISTRATOR

###Name Folding
* ACCT=ACCOUNT
* ACTR=ACTOR
* ASMT=ASSESSMENT
* ANSR=ANSWER
* BTCH=BATCH
* BNDL=BUNDLE
* CNTR=CENTER
* CTNT=CONTENT
* CMPS=CAMPUS
* CFGN=CONFIGURATION
* CORS=COURSE
* CRCY=CURRENCY
* DOCM=DOCUMENT
* DPMT=DEPARTMENT
* DSRT=DISTRICT
* DSBM=DISBURSEMENT
* ENRN=ENROLLMENT
* EXMN=EXAMINATION
* FLMN=FULFILLMENT
* GROP=GROUP
* INTR=INSTRUCTOR
* INVC=INVOICE
* LDGR=LEDGER
* LSSN=LESSON
* MANL=MANUAL
* MMBR=MEMBER
* MODL=MODULE
* PCPL=PRINCIPAL
* PLAN = PLAN
* PYBL=PAYABLE
* PYMT=PAYMENT
* PRJT=PROJECT
* QSTN=QUESTION
* RVBL=RECEIVABLE
* RCPN=RECIPIENT
* RGTN=REGISTRATION
* RSPN=RESPONSE
* SCTN=SECTION
* SESN=SESSION
* SNGL=SINGLE
* STDN=STUDENT
* SPRT = SUB_PROJECT
* SMDL=SUB_MODULE/SAMDOL
* SPPT=SUPPORT
* TRSN = TRANSACTION
* USER=USER
* VCHR=VOUCHER





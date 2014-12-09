
CREATE TABLE dbo.AdvertisementPackage(
	packageId varchar(50) NOT NULL,
	bannerDimension varchar(50) NOT NULL,
	bannerImageSize varchar(50) NOT NULL,
	bannerTextSize varchar(50) NOT NULL,
	costPerDay int NOT NULL,
	weekDiscount int NOT NULL,
	monthDiscount int NOT NULL,
	yearDiscount int NOT NULL,
  PRIMARY KEY 
(
	packageId
)
);

CREATE TABLE dbo.AdvertisementSubscriptionDetail(
	packageId varchar(50) NOT NULL,
	requestorId varchar(50) NOT NULL,
	duration int NOT NULL,
	dimension varchar(10) NOT NULL,
	advertisementStartDate timestamp NOT NULL,
	advertisementEndDate timestamp NOT NULL,
  PRIMARY KEY
(
	packageId 
));


CREATE TABLE dbo.agentMemberDetail(
	memberId varchar(50) NOT NULL,
	agentType varchar(50) ,
	locationId varchar(50),
  PRIMARY KEY 
(
	memberId
));


CREATE TABLE dbo.buyerMemberDetail(
	memberId varchar(50) NOT NULL,
	locationId varchar(50) NOT NULL,
	propertyArea int NOT NULL,
	propertyBudget float NOT NULL,
  PRIMARY KEY 
(
	memberId
));


CREATE TABLE dbo.MemberCategoryMaster(
	memberCategoryId varchar(50) NOT NULL,
	memberCategoryName varchar(50) NOT NULL,
	memberCategoryDescription varchar(256) NOT NULL,
  PRIMARY KEY 
(
	memberCategoryId
) );


drop table dbo.MemberDetail;

CREATE TABLE dbo.MemberDetail(
	memberId varchar(50) NOT NULL,
	memberCategoryId varchar(50) NOT NULL,
	name varchar(50),
	address varchar(256),
	pincode numeric(6),
	phoneNnumber numeric(20),
	mobileNumber numeric(50),
	emaiIId varchar(50) ,
	newsletterSubscription varchar(50) ,
	username varchar(50) ,
	password varchar(64) NOT NULL,
  PRIMARY KEY 
(
	memberId
));


CREATE TABLE dbo.PropertyApproval(
	propertyId varchar(50) NOT NULL,
	approvalStatus varchar(30) NOT NULL,
	approvalDescription varchar(50) NOT NULL,
	approverId varchar(50) NOT NULL,
 PRIMARY KEY 
(
	propertyId
) );


CREATE TABLE dbo.PropertyCategoryMaster(
	categoryId varchar(50) NOT NULL,
	categoryName varchar(50) NOT NULL,
	categoryDescription varchar(50) NOT NULL,
	buyerCommission int NOT NULL,
	sellerCommission int NOT NULL,
	agentCommission int,
  PRIMARY KEY 
(
	categoryId 
));

CREATE TABLE dbo.PropertyDetails(
	propertyId varchar(50) NOT NULL,
	categoryId varchar(50) NOT NULL,
	country varchar(50) NOT NULL,
	state varchar(50) NOT NULL,
	city varchar(50) ,
	region varchar(50) ,
	postedBy varchar(50) NOT NULL,
	area int NOT NULL,
	rate float ,
	lumpsumCost float ,
	image blob,
	propertyDescription varchar(256) NOT NULL,
	propertyTitle varchar(50) NOT NULL,
	locationId varchar(50) NOT NULL,
  PRIMARY KEY 
(
	propertyId 
));


CREATE TABLE dbo.PropertyLocationMaster(
	locationId varchar(50) NOT NULL,
	country varchar(50) NOT NULL,
	state varchar(50) NOT NULL,
	city varchar(50) NOT NULL,
	locality varchar(50) ,
 PRIMARY KEY 
(
	locationId 
));


ALTER TABLE dbo.AdvertisementSubscriptionDetail ADD  FOREIGN KEY(packageId)
REFERENCES dbo.AdvertisementPackage (packageId);

ALTER TABLE dbo.AdvertisementSubscriptionDetail ADD FOREIGN KEY(requestorId)
REFERENCES dbo.MemberDetail (memberId);

ALTER TABLE dbo.agentMemberDetail ADD FOREIGN KEY(memberId)
REFERENCES dbo.MemberDetail (memberId);

ALTER TABLE dbo.agentMemberDetail ADD FOREIGN KEY(locationId)
REFERENCES dbo.PropertyLocationMaster (locationId);

ALTER TABLE dbo.buyerMemberDetail ADD FOREIGN KEY(memberId)
REFERENCES dbo.MemberDetail (memberId);

ALTER TABLE dbo.buyerMemberDetail ADD FOREIGN KEY(locationId)
REFERENCES dbo.PropertyLocationMaster (locationId);

ALTER TABLE dbo.MemberDetail ADD FOREIGN KEY(memberCategoryId)
REFERENCES dbo.MemberCategoryMaster (memberCategoryId);

ALTER TABLE dbo.PropertyApproval ADD FOREIGN KEY(propertyId)
REFERENCES dbo.PropertyDetails (propertyId);

ALTER TABLE dbo.PropertyDetails ADD FOREIGN KEY(postedBy)
REFERENCES dbo.MemberDetail (memberId);

ALTER TABLE dbo.PropertyDetails ADD FOREIGN KEY(categoryId)
REFERENCES dbo.PropertyCategoryMaster (categoryId);

ALTER TABLE dbo.PropertyDetails ADD FOREIGN KEY(locationId)
REFERENCES dbo.PropertyLocationMaster (locationId);


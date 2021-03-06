USE [AptechDB]
GO
/****** Object:  Table [dbo].[AdvertisementPackage]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AdvertisementPackage](
	[packageId] [varchar](50) NOT NULL,
	[bannerDimension] [varchar](50) NOT NULL,
	[bannerImageSize] [varchar](50) NOT NULL,
	[bannerTextSize] [varchar](50) NOT NULL,
	[costPerDay] [money] NOT NULL,
	[weekDiscount] [int] NOT NULL,
	[monthDiscount] [int] NOT NULL,
	[yearDiscount] [int] NOT NULL,
 CONSTRAINT [PK_AdvertisementPackage] PRIMARY KEY CLUSTERED 
(
	[packageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AdvertisementSubscriptionDetail]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AdvertisementSubscriptionDetail](
	[packageId] [varchar](50) NOT NULL,
	[requestorId] [varchar](50) NOT NULL,
	[duration] [int] NOT NULL,
	[dimension] [varchar](10) NOT NULL,
	[advertisementStartDate] [datetime] NOT NULL,
	[advertisementEndDate] [datetime] NULL,
 CONSTRAINT [PK_AdvertisementSubscriptionDetail] PRIMARY KEY CLUSTERED 
(
	[packageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[agentMemberDetail]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[agentMemberDetail](
	[memberId] [varchar](50) NOT NULL,
	[agentType] [varchar](50) NULL,
	[locationId] [varchar](50) NULL,
 CONSTRAINT [PK_agentMemberDetail] PRIMARY KEY CLUSTERED 
(
	[memberId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[buyerMemberDetail]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[buyerMemberDetail](
	[memberId] [varchar](50) NOT NULL,
	[locationId] [varchar](50) NOT NULL,
	[propertyArea] [int] NOT NULL,
	[propertyBudget] [money] NOT NULL,
 CONSTRAINT [PK_buyerMemberDetail] PRIMARY KEY CLUSTERED 
(
	[memberId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MemberCategoryMaster]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MemberCategoryMaster](
	[memberCategoryId] [varchar](50) NOT NULL,
	[memberCategoryName] [varchar](50) NOT NULL,
	[memberCategoryDescription] [varchar](256) NOT NULL,
 CONSTRAINT [PK_MemberCategoryMaster] PRIMARY KEY CLUSTERED 
(
	[memberCategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MemberDetail]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MemberDetail](
	[memberId] [varchar](50) NOT NULL,
	[memberCategoryId] [varchar](50) NOT NULL,
	[name] [varchar](50) NULL,
	[address] [varchar](256) NULL,
	[pincode] [char](6) NULL,
	[phoneNnumber] [varchar](20) NULL,
	[mobileNumber] [varchar](50) NULL,
	[emaiIId] [varchar](50) NULL,
	[newsletterSubscription] [bit] NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](64) NOT NULL,
 CONSTRAINT [PK_MemberDetail] PRIMARY KEY CLUSTERED 
(
	[memberId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PropertyApproval]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PropertyApproval](
	[propertyId] [varchar](50) NOT NULL,
	[approvalStatus] [varchar](30) NOT NULL,
	[approvalDescription] [varchar](50) NOT NULL,
	[approverId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PropertyApproval] PRIMARY KEY CLUSTERED 
(
	[propertyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PropertyCategoryMaster]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PropertyCategoryMaster](
	[categoryId] [varchar](50) NOT NULL,
	[categoryName] [varchar](50) NOT NULL,
	[categoryDescription] [varchar](50) NOT NULL,
	[buyerCommission] [int] NOT NULL,
	[sellerCommission] [int] NOT NULL,
	[agentCommission] [int] NULL,
 CONSTRAINT [PK_PropertyCategoryMaster] PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PropertyDetails]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PropertyDetails](
	[propertyId] [varchar](50) NOT NULL,
	[categoryId] [varchar](50) NOT NULL,
	[country] [varchar](50) NOT NULL,
	[state] [varchar](50) NOT NULL,
	[city] [varchar](50) NULL,
	[region] [varchar](50) NULL,
	[postedBy] [varchar](50) NOT NULL,
	[area] [int] NOT NULL,
	[rate] [money] NULL,
	[lumpsumCost] [money] NULL,
	[image] [image] NULL,
	[propertyDescription] [varchar](256) NOT NULL,
	[propertyTitle] [varchar](50) NOT NULL,
	[locationId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PropertyDetails] PRIMARY KEY CLUSTERED 
(
	[propertyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PropertyLocationMaster]    Script Date: 8.12.2014 22:50:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PropertyLocationMaster](
	[locationId] [varchar](50) NOT NULL,
	[country] [varchar](50) NOT NULL,
	[state] [varchar](50) NOT NULL,
	[city] [varchar](50) NOT NULL,
	[locality] [varchar](50) NULL,
 CONSTRAINT [PK_PropertyLocationMaster] PRIMARY KEY CLUSTERED 
(
	[locationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[AdvertisementSubscriptionDetail]  WITH CHECK ADD  CONSTRAINT [FK_AdvertisementSubscriptionDetail_AdvertisementPackage] FOREIGN KEY([packageId])
REFERENCES [dbo].[AdvertisementPackage] ([packageId])
GO
ALTER TABLE [dbo].[AdvertisementSubscriptionDetail] CHECK CONSTRAINT [FK_AdvertisementSubscriptionDetail_AdvertisementPackage]
GO
ALTER TABLE [dbo].[AdvertisementSubscriptionDetail]  WITH CHECK ADD  CONSTRAINT [FK_AdvertisementSubscriptionDetail_MemberDetail] FOREIGN KEY([requestorId])
REFERENCES [dbo].[MemberDetail] ([memberId])
GO
ALTER TABLE [dbo].[AdvertisementSubscriptionDetail] CHECK CONSTRAINT [FK_AdvertisementSubscriptionDetail_MemberDetail]
GO
ALTER TABLE [dbo].[agentMemberDetail]  WITH CHECK ADD  CONSTRAINT [FK_agentMemberDetail_agentMemberDetail] FOREIGN KEY([memberId])
REFERENCES [dbo].[MemberDetail] ([memberId])
GO
ALTER TABLE [dbo].[agentMemberDetail] CHECK CONSTRAINT [FK_agentMemberDetail_agentMemberDetail]
GO
ALTER TABLE [dbo].[agentMemberDetail]  WITH CHECK ADD  CONSTRAINT [FK_agentMemberDetail_PropertyLocationMaster] FOREIGN KEY([locationId])
REFERENCES [dbo].[PropertyLocationMaster] ([locationId])
GO
ALTER TABLE [dbo].[agentMemberDetail] CHECK CONSTRAINT [FK_agentMemberDetail_PropertyLocationMaster]
GO
ALTER TABLE [dbo].[buyerMemberDetail]  WITH CHECK ADD  CONSTRAINT [FK_buyerMemberDetail_MemberDetail] FOREIGN KEY([memberId])
REFERENCES [dbo].[MemberDetail] ([memberId])
GO
ALTER TABLE [dbo].[buyerMemberDetail] CHECK CONSTRAINT [FK_buyerMemberDetail_MemberDetail]
GO
ALTER TABLE [dbo].[buyerMemberDetail]  WITH CHECK ADD  CONSTRAINT [FK_buyerMemberDetail_PropertyLocationMaster] FOREIGN KEY([locationId])
REFERENCES [dbo].[PropertyLocationMaster] ([locationId])
GO
ALTER TABLE [dbo].[buyerMemberDetail] CHECK CONSTRAINT [FK_buyerMemberDetail_PropertyLocationMaster]
GO
ALTER TABLE [dbo].[MemberDetail]  WITH CHECK ADD  CONSTRAINT [FK_MemberDetail_MemberDetail] FOREIGN KEY([memberCategoryId])
REFERENCES [dbo].[MemberCategoryMaster] ([memberCategoryId])
GO
ALTER TABLE [dbo].[MemberDetail] CHECK CONSTRAINT [FK_MemberDetail_MemberDetail]
GO
ALTER TABLE [dbo].[PropertyApproval]  WITH CHECK ADD  CONSTRAINT [FK_PropertyApproval_PropertyApproval] FOREIGN KEY([propertyId])
REFERENCES [dbo].[PropertyDetails] ([propertyId])
GO
ALTER TABLE [dbo].[PropertyApproval] CHECK CONSTRAINT [FK_PropertyApproval_PropertyApproval]
GO
ALTER TABLE [dbo].[PropertyDetails]  WITH CHECK ADD  CONSTRAINT [FK_PropertyDetails_MemberDetail] FOREIGN KEY([postedBy])
REFERENCES [dbo].[MemberDetail] ([memberId])
GO
ALTER TABLE [dbo].[PropertyDetails] CHECK CONSTRAINT [FK_PropertyDetails_MemberDetail]
GO
ALTER TABLE [dbo].[PropertyDetails]  WITH CHECK ADD  CONSTRAINT [FK_PropertyDetails_PropertyDetails] FOREIGN KEY([categoryId])
REFERENCES [dbo].[PropertyCategoryMaster] ([categoryId])
GO
ALTER TABLE [dbo].[PropertyDetails] CHECK CONSTRAINT [FK_PropertyDetails_PropertyDetails]
GO
ALTER TABLE [dbo].[PropertyDetails]  WITH CHECK ADD  CONSTRAINT [FK_PropertyDetails_PropertyLocationMaster] FOREIGN KEY([locationId])
REFERENCES [dbo].[PropertyLocationMaster] ([locationId])
GO
ALTER TABLE [dbo].[PropertyDetails] CHECK CONSTRAINT [FK_PropertyDetails_PropertyLocationMaster]
GO

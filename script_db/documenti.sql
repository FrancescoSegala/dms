-- summer_anag_remi.dbo.documenti definition

-- Drop table

-- DROP TABLE summer_anag_remi.dbo.documenti GO

CREATE TABLE documenti (
	id varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
	[data] varchar(100) COLLATE Latin1_General_CI_AS NULL,
	c_remi_ass varchar(100) COLLATE Latin1_General_CI_AS NULL,
	tdoc1 varchar(100) COLLATE Latin1_General_CI_AS NULL,
	tdoc2 varchar(100) COLLATE Latin1_General_CI_AS NULL,
	CONSTRAINT documenti_PK PRIMARY KEY (id)
) GO;

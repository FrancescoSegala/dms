-- summer_anag_remi.dbo.profilo_tdoc definition

-- Drop table

-- DROP TABLE summer_anag_remi.dbo.profilo_tdoc GO

CREATE TABLE summer_anag_remi.dbo.profilo_tdoc (
	profilo varchar(300) COLLATE Latin1_General_CI_AS NOT NULL,
	tdoc_primo_livello varchar(500) COLLATE Latin1_General_CI_AS NULL

	CONSTRAINT profilo_tdoc_PK PRIMARY KEY (profilo)
) GO;

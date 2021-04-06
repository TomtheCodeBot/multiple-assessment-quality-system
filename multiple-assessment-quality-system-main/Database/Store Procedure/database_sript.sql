CREATE TABLE Academic_Year(
	AYCode	VARCHAR(6),
	AYName	VARCHAR(50),
	
	PRIMARY KEY(AYCode)
);

CREATE TABLE Faculty(
	FCode	VARCHAR(6),
	FName	VARCHAR(50),
	
	PRIMARY KEY(FCode)
);

CREATE TABLE Program(
	PCode	VARCHAR(6),
	PName	VARCHAR(50),
	
	PRIMARY KEY(PCode)
);

CREATE TABLE Module(
	MCode	VARCHAR(6),
	MName	VARCHAR(50),
	
	PRIMARY KEY(MCode)
);

CREATE TABLE Aca_Faculty(
	AFCode	VARCHAR(6),
	AYCode	VARCHAR(6),
	FCode	VARCHAR(6),
	
	PRIMARY KEY(AFCode),
	FOREIGN KEY (AYCode) REFERENCES Academic_Year(AYCode),
	FOREIGN KEY (FCode) REFERENCES Faculty(FCode)
);

CREATE TABLE Aca_Fac_Pro(
	PFCode	VARCHAR(6),
	AFCode	VARCHAR(6),
	PCode	VARCHAR(6),
	
	PRIMARY KEY(PFCode),
	FOREIGN KEY (AFCode) REFERENCES Aca_Faculty(AFCode),
	FOREIGN KEY (PCode) REFERENCES Program(PCode)
);

CREATE TABLE Aca_Pro_Mod(
	PFCode	VARCHAR(6),
	MCode	VARCHAR(6),
	
	PRIMARY KEY(PFCode,MCode),
	FOREIGN KEY (PFCode) REFERENCES Aca_Fac_Pro(PFCode),
	FOREIGN KEY (MCode) REFERENCES Module(MCode)
);

CREATE TABLE Semester(
	SCode	VARCHAR(6),
	SName	VARCHAR(50),
	AYCode	VARCHAR(6),
	
	PRIMARY KEY(SCode),
	FOREIGN KEY (AYCode) REFERENCES Academic_Year(AYCode)
);


CREATE TABLE Class(
	CCode	VARCHAR(6),
	CName	VARCHAR(50),
	Size	INT 	NOT NULL,
	MCode	VARCHAR(6),
	SCode	VARCHAR(6),
	
	PRIMARY KEY (CCode),
	FOREIGN KEY (SCode) REFERENCES Semester(SCode),
	FOREIGN KEY (MCode) REFERENCES Module(MCode)
);

CREATE TABLE Lecturer(
	LCode	VARCHAR(6),
	LName	VARCHAR(50),
	CCode	VARCHAR(6),
	
	PRIMARY KEY(LCode),
	FOREIGN KEY (CCode) REFERENCES Class(CCode)
);

CREATE TABLE Questionnaire(
	CCode	VARCHAR(6)	NOT NULL,
	LCode	VARCHAR(6)	NOT NULL,
	/*STUDENT INFO*/
	attend	VARCHAR(10)	CHECK (attend IN ('Never','Rarely','Sometimes','Often','Always')),
	gender	VARCHAR(10)	CHECK (gender IN ('Male','Female','Other')),
	/*ORGANIZATION & PLANNING*/
	Q1	VARCHAR(10)	CHECK (Q1 IN ('1','2','3','4','5','Not')),
	Q2	VARCHAR(10)	CHECK (Q2 IN ('1','2','3','4','5','Not')),
	Q3	VARCHAR(10)	CHECK (Q3 IN ('1','2','3','4','5','Not')),
	Q4	VARCHAR(10)	CHECK (Q4 IN ('1','2','3','4','5','Not')),
	/*MODULE WORKLOAD AND DIFFICULTY*/
	Q5	VARCHAR(10)	CHECK (Q5 IN ('1','2','3','4','5')),
	Q6	VARCHAR(10)	CHECK (Q6 IN ('1','2','3','4','5')),
	Q7	VARCHAR(10)	CHECK (Q7 IN ('1','2','3','4','5')),
	/*TEACHING & LEARNING*/
	Q8	VARCHAR(10)	CHECK (Q8 IN ('1','2','3','4','5','Not')),
	Q9	VARCHAR(10)	CHECK (Q9 IN ('1','2','3','4','5','Not')),
	Q10	VARCHAR(10)	CHECK (Q10 IN ('1','2','3','4','5','Not')),
	Q11	VARCHAR(10)	CHECK (Q11 IN ('1','2','3','4','5','Not')),
	Q12	VARCHAR(10)	CHECK (Q12 IN ('1','2','3','4','5','Not')),
	Q13	VARCHAR(10)	CHECK (Q13 IN ('1','2','3','4','5','Not')),
	Q14	VARCHAR(10)	CHECK (Q14 IN ('1','2','3','4','5','Not')),
	Q15	VARCHAR(10)	CHECK (Q15 IN ('1','2','3','4','5','Not')),
	Q16	VARCHAR(10)	CHECK (Q16 IN ('1','2','3','4','5','Not')),
	Q17	VARCHAR(10)	CHECK (Q17 IN ('1','2','3','4','5','Not')),
	Q18	VARCHAR(500),
	
	FOREIGN KEY (CCode) REFERENCES Class(CCode),
	FOREIGN KEY (LCode) REFERENCES Lecturer(LCode)
);
);

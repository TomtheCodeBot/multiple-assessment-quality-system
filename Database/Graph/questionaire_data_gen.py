import random
LCode=["L00001","L00002","L00003","L00004","L00005","L00006","L00007","L00008","L00009","L00010","L00011","L00012","L00013","L00014","L00015","L00016","L00017","L00018","L00019","L00020","L00021","L00022","L00023","L00024","L00025","L00026","L00027","L00028","L00029","L00030","L00031"]
CCode=["C00002","C00018","C00010","C00006","C00017","C00009","C00014","C00015","C00029","C00023","C00022","C00024","C00023","C00025","C00016","C00011","C00026","C00012","C00030","C00019","C00020","C00001","C00028","C00005","C00013","C00008","C00021","C00003","C00027","C00007","C00004"]
gender=['Male','Female','Other']
attend=['Never','Rarely','Sometimes','Often','Always']
answers=['1','2','3','4','5']

for i in range(0,len(CCode)):
    for k in range (0,20):
        print("INSERT INTO Questionnaire(LCode,CCode,gender,attend,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,Q13,Q14,Q15,Q16,Q17,Q18) VALUES(\""+LCode[i]+"\",\""+CCode[i]+"\",\""+gender[random.randrange(0,3)]+"\",\""+attend[random.randrange(0,5)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\",\""+answers[random.randrange(0,4)]+"\");")

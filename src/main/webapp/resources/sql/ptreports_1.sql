select r.`CREATEDAT` from patientreportitemvalue v join patientreport r on v.`PATIENTREPORT_ID`=r.`ID` order by v.id desc limit 10;
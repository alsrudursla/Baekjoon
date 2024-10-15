SELECT ID
FROM ECOLI_DATA
WHERE PARENT_ID IN (SELECT E.ID 
                FROM ECOLI_DATA AS E 
                WHERE E.PARENT_ID IN (SELECT E2.ID
                                     FROM ECOLI_DATA AS E2
                                     WHERE E2.PARENT_ID IS NULL))
ORDER BY ID
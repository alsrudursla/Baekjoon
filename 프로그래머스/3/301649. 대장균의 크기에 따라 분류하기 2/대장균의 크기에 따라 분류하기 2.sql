-- NTILE : 지정된 수 만큼의 등급으로 나누어 각 등급의 번호를 출력
-- ROW_NUMBER : 동등 순위를 인식하지 않고 매번 증가되는 번호를 출력
-- DENSE_RANK : 동등 순위는 같게 나오고 그 다음 순위를 다음 번호로 출력 
-- RANK : 동등 순위는 같게 나오고 그 다음 순위를 다음 번호를 뺀 그 다음 번호로 출력
SELECT E.ID, 
    CASE
        WHEN LEVEL = 1 THEN 'CRITICAL'
        WHEN LEVEL = 2 THEN 'HIGH'
        WHEN LEVEL = 3 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM (
    SELECT ID, NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS LEVEL
    FROM ECOLI_DATA
    ) AS E
ORDER BY E.ID ASC
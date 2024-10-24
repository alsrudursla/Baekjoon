-- 날짜 사이값을 구하기 위해 그 밖의 값을 삭제
-- EX) END_DATE > '2022-11-01' && START_DATE < '2022-11-30
-- 정수 변환 (소수점 아래 삭제) : FLOOR()
-- 반올림 : ROUND()
SELECT C.CAR_ID, C.CAR_TYPE, FLOOR(C.DAILY_FEE * (1- P.DISCOUNT_RATE*0.01) * 30) AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY H ON C.CAR_ID = H.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P ON C.CAR_TYPE = P.CAR_TYPE
WHERE (C.CAR_TYPE = '세단' || C.CAR_TYPE = 'SUV') &&
    C.CAR_ID NOT IN (SELECT CAR_ID
                    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                    WHERE END_DATE > '2022-11-01' && START_DATE < '2022-11-30') &&
    P.DURATION_TYPE = '30일 이상'
GROUP BY C.CAR_ID
HAVING FEE BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, C.CAR_TYPE ASC, C.CAR_ID DESC
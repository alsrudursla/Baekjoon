
-- 형질 코드에서 1, 2, 3의 비트는 2의 제곱수로 표현되어야 합니다. 즉, 1번 형질은 1, 2번 형질은 2, 3번 형질은 4가 됩니다.
SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE (GENOTYPE & 2) = 0 && ((GENOTYPE & 1) = 1 || (GENOTYPE & 4) = 4)
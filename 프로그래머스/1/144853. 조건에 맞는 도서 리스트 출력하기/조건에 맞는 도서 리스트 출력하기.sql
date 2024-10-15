SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, "%Y-%m-%d")
FROM BOOK
WHERE YEAR(PUBLISHED_DATE) = 2021 && CATEGORY = '인문'
ORDER BY PUBLISHED_DATE ASC
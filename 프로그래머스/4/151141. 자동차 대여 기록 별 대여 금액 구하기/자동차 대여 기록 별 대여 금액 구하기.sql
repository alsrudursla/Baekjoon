select H.HISTORY_ID, floor(C.DAILY_FEE * (datediff(H.END_DATE, H.START_DATE)+1) * (1 - 0.01 * (case
            when D.DURATION_TYPE is null then 0
            else D.DISCOUNT_RATE
          end))) as FEE
from CAR_RENTAL_COMPANY_CAR C
join CAR_RENTAL_COMPANY_RENTAL_HISTORY H on C.CAR_ID = H.CAR_ID
left join CAR_RENTAL_COMPANY_DISCOUNT_PLAN D on C.CAR_TYPE = D.CAR_TYPE and
    ((D.DURATION_TYPE = '7일 이상' and (datediff(H.END_DATE, H.START_DATE)+1) between 7 and 29) or 
    (D.DURATION_TYPE = '30일 이상' and (datediff(H.END_DATE, H.START_DATE)+1) between 30 and 89) or
    (D.DURATION_TYPE = '90일 이상' and (datediff(H.END_DATE, H.START_DATE)+1) >= 90))
where C.CAR_TYPE = '트럭'
order by FEE desc, H.HISTORY_ID desc
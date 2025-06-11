select A.APNT_NO, P.PT_NAME, P.PT_NO, A.MCDP_CD, D.DR_NAME, A.APNT_YMD
from APPOINTMENT A
left join PATIENT P on A.PT_NO = P.PT_NO
left join DOCTOR D on A.MDDR_ID = D.DR_ID
where A.APNT_CNCL_YN = 'N' and date_format(A.APNT_YMD, '%Y-%m-%d') = '2022-04-13'
order by A.APNT_YMD
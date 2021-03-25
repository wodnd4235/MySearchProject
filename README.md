# MySearchProject


외부 라이브러리:
     -  guava:22.0 , 사용이유 : api 응답 데이터중 필요한 값만 추출 하기위해 사용


프로세스 curl


 1. 회원가입
     -     curl -X "POST" "http://localhost:8080/sign/up" \-H 'Content-Type: application/x-www-form-urlencoded' \ -—data-urlencode "username=alice" \ —-data-urlencode                        "password=wonderland"    
     
 2. 로그인
     -     curl -i -X POST -d username=아이디 -d password=비밀번호 -c cookies.txt http://localhost:8080/login
 
 3. api 사용하여 검색
     -     curl -X "POST" "http://localhost:8080/search/area " \-H 'Content-Type: application/x-www-form-urlencoded' \ -—data-urlencode "keyword=검색어" \ —-data-urlencode                        "currentPage=페이지 개수" \ —-data-urlencode  " pageSize =가져올 데이터 개수"
     
 4. 검색 히스토리
     -      curl -X "POST" "http://localhost:8080/search/area"
     
 5. 검색어 top10 목록
     -      curl -X "GET" "http://localhost:8080/search"    

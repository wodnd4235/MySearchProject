# MySearchProject


외부 라이브러리:
     -  guava:22.0 , 사용이유 : api 응답 데이터중 필요한 값만 추출 하기위해 사용


프로세스 curl :


 1. 회원가입  
     -      curl -d "username=alice1&password=wonderland" \ -H "Content-Type: application/x-www-form-urlencoded" \ -X POST http://localhost:8000/sign/up
     
 2. 로그인
     -     curl -i -X POST -d username=아이디 -d password=비밀번호 -c cookies.txt http://localhost:8080/login
 
 3. api 사용하여 검색  
     -      curl -d "keyword=검색어& pageSize =가져올 데이터 수&currentPage=페이지수" \ -H "Content-Type: application/x-www-form-urlencoded" \ -X POST                 http://localhost:8000/search/area
     
 4. 검색 히스토리
     -      curl -d  \ -H "Content-Type: application/x-www-form-urlencoded" \ -X GET http://localhost:8000/my/search
     
 5. 검색어 top10 목록
     -      curl -d  \ -H "Content-Type: application/x-www-form-urlencoded" \ -X GET http://localhost:8000/search

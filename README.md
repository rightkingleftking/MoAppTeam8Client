# MoApp8Client

## 진행상황
url을 통해 json을 받아오는 과정에서 오류
- 이클립스에서 돌렸을 때는 문제 없었음 > 코드는 문제없음
- 에러코드 :
  - error : No Network Security Config specified, using platform default
      안드로이드 네트워크 보안구성 에 따르면 Android 9.0 (API 수준 28)부터는 일반 텍스트 지원이 기본적으로 사용 중지되어 있습니다.
      http://kimboomin.blogspot.com/2018/11/android.html 여기 나온대로 했는데 해결된건지 잘 모르겠음
  - android.os.NetworkOnMainThreadException
     메인쓰레드에서는 네트워크 통신이 안된다고함
     https://jamesdreaming.tistory.com/35 < 쓰레드 처리가 저한테는 너무 어려워서 시간이 좀 걸릴거 같습니다,,

## 구현해야할것들 
최저가 검색 

- 검색방법(택1? 둘 다?)

  - dropdown list 클릭으로 선택(대분류 / 품목)

  - 직접 타이핑으로 검색후 선택 ( 타이핑 할 때 밑에 자동완성으로 리스트가 보여지는 것을 선택함, 뷰를 따로 만들어야됨)

- 정렬 (거리, 가격)


추가기능들

- 등락률 순위 (전주 대비 시세하락율이 가장 높은순위대로 보여줌)

  - 등락률 차트? (시세차트?)

- 행사 정보(세일기간?, 품목?)

- 영업시간



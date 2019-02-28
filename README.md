# android-edu
2/27~3/4일간 진행된 안드로이드 교육 내용 정리

## 1. 레이아웃 종류
#### 1. LinearLayout
- 사각형 박스 형태의 디스플레이 화면에 UI 요소들을 일렬로 배치할 수 있다.
- orientation: 방향성이 있음
- match_parent: 부모 영역의 공간을 차지하지만, 차지할 수 있는 영역까지만 차지
- gravity: 자신의 뷰에서 포함하고 있는 데이터를 정렬
- layout_gravity: LinearLayout에서만 동작한다. 자신을 포함하고 있는 부모 위젯 레이아웃에서 옵션값에 따라 정렬 (자기 자신의 외부에 관련된 속성은 layout이 붙는다. ex. android:layout-margin, android:padding)

#### 2. RelativeLayout
- 기준을 잡고 그 기준을 대상으로 배치하고, align 같은 속성들을 이용해서 정렬한다.
- android:layout_centerInParent: 화면 한 가운데에 배치
- android:layout_centerHorizontal : 수평 상 가운데 배치
- [RelativeLayout.LayoutParams 종류](https://developer.android.com/reference/android/widget/RelativeLayout.LayoutParams.html)

## 2. Activity
- startActivity(Intent): 다른 액티비티를 실행시킨다
- startActivityForResult(Intent): 다른 액티비티를 실행시키고 결과값을 받는다. onActivityResult() 리스너를 이용하여 데이터를 처리할 수 있다.

```
@Override
 protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
 }
```

- getIntent(): 전달받은 인텐트를 가져 옴
- setResult(): 자신을 호출한 액티비티에 결과값을 보내거나 추가적인 데이터를 보낼 수 있다. 역시 인텐트를 이용해야 한다.
- Intent: 컴포넌트 간의 데이터를 주고받거나 액티비티를 호출할 때 사용
  - 명시적 인텐트: 어떤 인텐트를 실행할지 명시적으로 표기 (ex. new Intent(MainActivity.this, MyActivity.class); )
  - 암시적 인텐트
  - intent.putExtra()를 통해 데이터를 주고 받을 수 있음.

## 3. ListView
- setOnItemClickListener()는 ListView만 가지고 있다.
- Adapter
   - 사용자가 정의한 데이터를 ListView에 출력하기 위해 사용하는 객체로, 사용자 데이터와 화면 출력 View로 이루어진 두 개의 부분을 이어주는 객체이다.
   - https://recipes4dev.tistory.com/42
   - Adapter는 BaseAdapter 클래스를 상속받아 새로 custom adapter를 구현할 수 있음
   - 스크롤 내리면서 데이터가 더 필요하면 getView() 호출하여 새로 생성.

## 4. 안드로이드 Life Cycle
![alt text](https://developer.android.com/guide/components/images/activity_lifecycle.png)

## 5. menu
- onCreateOptionsMenu(): activity에 메뉴를 생성할 수 있음.
- getMenuInflater()로 메뉴 inflater를 얻을 수 있다.
- onOptionsItemSelected(): 메뉴 선택 시 동작 정의

## 6. Thread
- 독립적으로 실행되는 실행 단위
- 네트워크 같은 경우 Thread로만 동작하게 만듦
- 단 직접 만든 Thread는 UI에 직접 접근할 수 없어서 'CalledFromWrongThreadException:Only the original thread that created a view hierarchy can touch its views.' 와 같은 예외가 발생한다.
- Thread에서 UI를 조작하기 위해서는 핸들러가 필요하다.

```
Handler handler = new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
     }
}
```

- Thread 예제로 progressBar 예제도 많이 사용 됨
- Logical한 코드만 Thread에 넣고, UI 조작 관련 코드는 Handler에 넣는 것을 권장함
- Thread & Handler의 기능을 같이 해주는 AsyncTask 클래스도 있다.

## 7. AsyncTask
- extends AsyncTask<Params, Progress, Result> 해서 사용한다

```
class MyTask extends AsyncTask<Void, Integer, Void>
```
#### 1. Thread 로직 관련
- Generic 타입이므로 기본형은 Wrapper 클래스로 넘겨야 한다.
- params: 진행 전에 넘기고 싶은 데이터
- progress : 진행 중간에 데이터를 넘기고 싶을 때
- result : 결과 리턴

#### 2. UI 조작
- onPreExecute() : doInBackground() 시작 전에
- onPostExecute() : doInBackground() 시작 후에
- onProgressUpdate(): doInBackground() 실행 중에 publishProgress()로 전달한 데이터를 받아 처리한다.

## 8. xmlpullparser
- xml 데이터를 읽고 파싱할 수 있다
- https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser
- 08_xml 프로젝트처럼 AndroidMenifest.xml에 다음 퍼미션을 추가해야 기상청의 기상정보 RSS를 받아올 수 있다.
- 자바 URL 객체에 url을 넣고 xmlpullparser에 전달하면 xmlpullparser가 알아서 읽어온다. (스트림을 열어서 읽고 쓰고 작업을 xmlpullparser가 함)

```
  <!-- 인터넷 사용 -->
  <uses-permission android:name="android.permission.INTERNET"/>
```

## 9. RecyclerView
- RecyclerView는 자동으로 추가되지 않으므로 Product Structure 메뉴를 이용한다.
- 새로 dependency를 추가한다.(implementation 'com.android.support:recyclerview-v7:28.0.0')
- custom ListView와 비슷하다고 보면 됨.
- 리스트뷰 + 그리드뷰 가 합쳐진 느낌

- ViewHolder
 - ListView의 adapter가 했던 getView()의 역할을 대신함
 - ViewHolder에 ClickListner를 붙이면 어떤 item이 클릭됐는지 알 수 있다 (ListView.OnItemClickListener() 와 같음)

#### * 그외
- 디자인은 스케치 툴 사용
- cf.인플레이트: xml 코드에 있는 UI 객체를 메모리에 올려 사용할 수 있게 한다
- 뷰를 수정할 경우에는 Activity Context를, 나머지 경우에는 Application Context를 사용하는 것을 권장한다.

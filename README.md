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
- Adapter
   - 사용자가 정의한 데이터를 ListView에 출력하기 위해 사용하는 객체로, 사용자 데이터와 화면 출력 View로 이루어진 두 개의 부분을 이어주는 객체이다.
   - https://recipes4dev.tistory.com/42

#### 그외
- 디자인은 스케치 툴 사용
- cf.인플레이트: xml 코드에 있는 UI 객체를 메모리에 올려 사용할 수 있게 한다

이 프로젝트는 전적으로 심심해서 만들어본 프로젝트입니다.
--
또한, 기능의 추가 및 연습을 위해 생성하였습니다.

웹페이지에서 게시판의 본문과 제목을 가져와 txt로 만들었습니다.
##### class 구조도
```
.
├── java
│     └── file_downloader
│                ├── connector
│                │        └── Connector.java
│                ├── Downloader.java
│                ├── fileprocess
│                │        ├── ReadBody.java
│                │        └── SaveText.java
│                ├── imageprocess
│                │        └── ImageMaker.java
│                ├── ReadProperty.java
│                ├── responseprocess
│                │        ├── GetBody.java
│                │        ├── ListObj.java
│                │        └── SplitLiTag.java
│                └── textprocess                       
│                         ├── GetValueByVarName.java
│                         ├── ImageType.java
│                         ├── TagGetter.java
│                         ├── TagType.java
│                         ├── TextMerger.java
│                         └── TextTransform.java
├── Main.java
├── makeImg.java
├── makeImgList.java
├── multiMaker.java
├── OneMaker.java
├── setting.properties
└── textMerger.java
```
---
CLASS 설명
--
* Downloader : 각 클래스의 실행 관리를 담당합니다.
* ReadProperty : properties file의 리딩과 세팅을 담당합니다.
* connector.Connector : url 접속과, 리딩을 담당합니다.
* fileprocess :Package. 파일의 처리를 담당하는 클래스를 모아둡니다.
    * SaveText : 읽어온 결과를 txt로 형식으로 저장합니다. setting.properties 에서 설정한 위치를 읽어와, 진행되도록 설정하였습니다.
    * ReadText : text파일 읽어옵니다. 이는 매번 실제페이지에 접속하기는 페이지 주인에게 미안하니, test용으로 쓸 txt를 만들고 거기서 읽기 위해 만들었습니다.
* imageprocess : 이미지 관련 패키지입니다.
    * ImageMaker : 1개의 이미지에 관해 저장을 실행합니다.
* responseprocess : reponse에 대한 처리를 담당합니다.
    * GetBody : 페이지에서 가져온 텍스트의 body부분을 추출, 또한 필요한 테그가 있는 문자들을 추출합니다.
    * ListObj : title과 address로 이루어진 class 정보를 담습니다.
    * SplitLiTag : html문서의 특정 id 혹은 class 를 기준으로 li별로 각각 분리합니다.  

* textprocess : text처리에 관한 패키지입니다.
    * GetValueByVarName : 정보가 특정한 변수에 담겨져 있는 html의 경우, 특정 변수의 값을 가져오는 클래스입니다.
    * TagGetter : 리펙토링 전 선택한 태그를 가져오는 클래스입니다. 중복 제거을 위해 설계하였습니다.
    * TextMerger : 폴더를 읽어와 그 폴더 안의 모든 txt파일을 폴더명.txt파일로 합치게 됩니다.                                 
             기존 SaveText가 개별 상세페이지 기준으로 txt를 만들도록 했습니다. 이걸 폴더명.txt파일을 만들어주는 클래스입니다.
    * TextTransform : 자주 쓰는 문자 처리를 위한 클래스입니다.

* makeImg / makeImgList / multiMaker / OneMaker : 크롤링의 기능 네가지 실행을 위한 클래스입니다. argments 로 주소값을 넘겨 목록 페이지에서 데이터 처리를 하도록 제작했습니다.
    * makeImg - 이미지 파일로 생성합니다.
    * makeImgList - 이미지 목록의 주소를 크롤링 합니다..
    * multiMaker - 텍스트 목록을 각각 처리하여 txt로 생성합니다.
    * oneMaker - 텍스트 목록을 모아 하나의 txt로 생성합니다.
    * setting.properties - 모든 클래스의 실행에 대한 설정값이 저장되는 클래스입니다.

* 기타 클래스의 경우 차후 추가될 기능과 관련 있는 클래스입니다.
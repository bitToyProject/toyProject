
# BORA
협업 관리 툴 토이 프로젝트 개발


# 소스코드 작성 규칙
모든 소스코드와 커밋은 아래 규칙에 따라 작성되어야 합니다.
소스코드 작성 규칙은 지속적으로 추가 또는 수정될 수 있습니다.

모든 변수 및 메서드명은 camel case rule(첫 글자는 소문자, 새로운 어절이 시작되는 부분은 대문자)을 따릅니다.

예: divMain (O) div_main (X) divmain (X) DivMain(X)
모든 클래스명은 camel case rule을 따르되, 첫 글자를 대문자로 합니다.

예: DiscoverService (O) discoverService (X) Discover_service (X)
모든 소스코드의 상단에는 아래 예시와 같은 양식에 따라 주석 헤더를 삽입합니다. (여러 줄 주석 문법은 언어에 따라 달라질 수 있습니다.)

/*
example.java(파일명)	Jinwook oh(작성자)	2021.7.31(작성일)

- Simple description here (간단한 설명)
*/
이외에도 코드 가독성을 위해 적절한 위치에 주석을 삽입합니다.

# Git 커밋 규칙
커밋 규칙은 지속적으로 추가 또는 수정될 수 있습니다.

커밋 메시지의 시작은 다음 prefix 중 하나로 합니다. (예: feat: Discover 컴포넌트 추가)
feat: 기능 추가
docs: 문서 수정 (README.md, 개발문서, HTML 단순 내용 수정 등)
refactor: 리팩토링 (기능 상의 변화는 없으나 가독성, 유지보수성을 위해 코드를 수정한 경우)
fix: 문제 해결 (자세한 문제와 해결 방법을 커밋 메시지에 명시)
커밋 메시지의 내용은 수정 사항을 최대한 자세히 명시할 수 있도록 작성합니다.


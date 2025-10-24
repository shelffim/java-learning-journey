# 애노테이션 기반 MVC 핸들러

## 🚴 프로젝트 목적 (WHAT & WHY)

리플렉션과 애노테이션을 활용하여, URL 요청을 받아 해당 컨트롤러의 메서드를 동적으로 찾아 실행

## 핵심 설계 결정  (WHAT, WHY)

main 메서드에서 스캔하고자 하는 패키지를 HandlerMapper 생성자에 주입합니다.
HandlerMapper 생성자에서 init() 메서드를 실행하여 스캔하고자 하는 디렉터리의 절대경로를 알아내고 디렉터리 안에 있는 모든 파일을 읽는다.
파일 이름이 .class 로 끝나는 파일만 골라 파일 이름과 스캔하고자 하는 패키지명을 합쳐서 클래스 메타 데이터를 조회한다.
@MyController가 붙은 클래스의 인스턴스를 생성한다. @MyController 가 붙은 클래스 메타 데이터를 사용해 모든 메서드를 순회하며 @MyRequestMapping이 붙어 있는지 확인 후,
@MyRequestMapping의 값을 키로 갖고 메서드의 메타 데이터와 클래스 인스턴스를 가지는 MethodInfo를 값으로 가지는
Map<String, MethodInfo> 컬렉션에 저장한다.
main 메서드의 HandlerAdapter 인스턴스로 invoke(String) 메서드를 실행해, HandlerMapper 클래스에 있는 Map 키 값을 확인한 후,
값을 통해 메서드를 실행시킨다.

## 사용 방법 (HOW)

'''
String path = "com.mvchandler.controller";
HandlerMapper handler = new HandlerMapper(path);
HandlerAdapter adapter = new HandlerAdapter(handler);
adapter.invoke("/hello")
adapter.invoke("/private-test")
'''

## 예외 처리 전략 (TRUST)

스캔 대상 인스턴스에서 @MyController가 붙은 클래스를 못 찾았을 경우 NotFoundMyController 예외 던지기
invoke(String) 메서드 안에 보낸 String 값을 HandlerAdapter의 Map의 키 값에 없을 경우 HandlerNotFoundException 예외 던지기

## 핵심 난관 및 해결 전략 (TRUST)

private 메서드의 경우, 접근이 불가능하기 때문에
@MyRequestMapping이 붙은 메서드를 찾으면 setAccessible(true)를 호출한다.
사용자의 요구사항이 @MyRequestMapping이 붙은 메소드의 실행을 컴파일러가 아닌 프레임워크에게 위임한 것이므로,
캡슐화 원칙보다는 사용자의 요구사항을 준수하는게 더 중요하다고 판단했다.

# 프로그램 만들기 (SimpleDotCom 2단계)
  - ✅ 좋은 방법 (Scanner를 메인에서 생성하고 함수에서 사용)
  - Scanner를 루프 바깥에서 한 번만 생성하고, 입력 받는 함수에서 이를 사용하도록 변경.

```java
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 🔹 Scanner를 한 번만 생성
        boolean isAlive = true;
        int numOfGuesses = 0;
        
        while (isAlive) {
            String userGuess = getUserInput(scanner); // Scanner를 함수에 전달
            numOfGuesses++;

            if (userGuess.equals("kill")) {
                isAlive = false;
                System.out.println("You took " + numOfGuesses + " guesses");
            }
        }

        scanner.close(); // 🔹 프로그램 종료 후 한 번만 닫기
    }

    // Scanner를 인자로 받아서 사용
    public static String getUserInput(Scanner scanner) {
        System.out.print("Enter a number: ");
        return scanner.next(); // 🔹 Scanner를 닫지 않고 그대로 사용
    }
}
```
✅ 장점:
  - Scanner를 반복해서 생성하지 않음 → 메모리 낭비 방지.
  - scanner.close()는 프로그램이 끝날 때 한 번만 호출 → System.in이 닫히는 문제 해결.
  - getUserInput() 함수는 입력만 담당 → 코드가 더 명확해짐.
📌 결론
✔ 좋은 방법: Scanner를 main()에서 한 번만 생성하고, 입력 받는 함수에서 인자로 받아서 사용.
🚫 나쁜 방법: Scanner를 함수 내부에서 매번 생성하고 닫는 것. (입력 스트림이 닫혀서 오류 발생)

  - 함수로 분리할 때는 Scanner의 생명 주기(Lifecycle)를 잘 관리해야 합니다! 🚀

```java
		while(isAlive)
		{
		
			//System.out.println("enter a number ");
			//String userGuess= scanner.next();
			String userGuess = helper.getUserInput("enter a number");
			result = dot.checkYourself(userGuess);
			numOfGuesses++;
			if(result.equals("kill"))
			{
				isAlive = false;
				System.out.println("You took "+numOfGuesses+ "guesses");
				
			}
		}
		//scanner.close();

public class GameHelper {
	
	public String getUserInput(String prompt)
	{
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length()==0) return null;
		}catch(IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}

}

이렇게 변경한건 전에 비해서 어때?
```


  - 변경된 코드의 장점과 단점
  - ✅ 장점 (좋아진 점)
    - Scanner 대신 BufferedReader 사용 → 성능 향상
    - BufferedReader는 Scanner보다 입력 속도가 빠르다.
    - 특히, 반복문에서 여러 번 입력을 받을 때 성능이 더 좋다.
    - Scanner는 내부적으로 정규 표현식(Regex)을 사용하여 입력을 처리하기 때문에 속도가 상대적으로 느릴 수 있음.
    - 함수로 분리하여 코드의 재사용성 증가
    - GameHelper의 getUserInput() 메서드는 입력받는 역할만 담당 → 코드가 더 모듈화됨.
    - while 루프에서는 getUserInput()을 호출하는 방식으로 입력 로직을 단순화할 수 있음.
    - 코드 가독성 향상
    - GameHelper.getUserInput()을 사용하여 입력받는 부분이 명확해짐.
    - System.out.println("enter a number")을 getUserInput("enter a number")로 변경하여 코드가 더 깔끔해짐.
  - ❌ 단점 (개선할 점)
    - 매번 BufferedReader를 새로 생성 → 메모리 낭비
    - BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
    - BufferedReader를 getUserInput()이 호출될 때마다 새로 생성함.
    - 반복문 안에서 계속 실행되므로 불필요한 객체가 매번 생성됨 → 성능 저하 가능.
    - 입력 스트림을 닫지 않음

    - 현재 코드에서는 BufferedReader를 닫지 않지만, 닫을 필요가 없음.
    - 하지만 BufferedReader를 매번 생성하는 구조는 좋지 않으므로 한 번만 생성하고 재사용하는 방식이 필요.

  - 📌 개선된 코드 (객체 재사용)
    - BufferedReader를 GameHelper 클래스의 멤버 변수로 유지하여 재사용
    - 객체를 한 번만 생성하고 입력을 받을 때마다 사용

```java
import java.io.*;

public class GameHelper {
    private BufferedReader reader;

    // 생성자에서 BufferedReader 한 번만 생성
    public GameHelper() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            inputLine = reader.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }

    // 프로그램이 끝날 때 호출하여 리소스 정리
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error closing BufferedReader: " + e);
        }
    }
}
```
  - 📌 main 함수에서 사용 예시
```java
public class Game {
    public static void main(String[] args) {
        GameHelper helper = new GameHelper();
        boolean isAlive = true;
        int numOfGuesses = 0;
        String result = "";

        while (isAlive) {
            String userGuess = helper.getUserInput("Enter a number");
            result = dot.checkYourself(userGuess);
            numOfGuesses++;

            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("You took " + numOfGuesses + " guesses");
            }
        }

        helper.close(); // 🔹 프로그램 종료 시 BufferedReader 닫기
    }
}
```
```
🚀 개선된 코드의 장점
✅ 한 번만 BufferedReader를 생성하고 재사용 → 불필요한 객체 생성을 방지
✅ 입력 스트림(System.in)을 닫지 않으면서도 자원 관리를 명확하게 처리
✅ 함수 분리로 코드의 모듈화와 가독성 향상

💡 최종 결론
✔ 기존 코드보다 BufferedReader 사용으로 성능이 향상됨.
✔ 하지만 BufferedReader를 반복해서 생성하는 것은 비효율적 → 객체를 한 번만 생성하고 재사용하는 방식이 더 좋다! 🚀
```

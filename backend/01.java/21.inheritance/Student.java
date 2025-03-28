
// Student 클래스 (Human 클래스를 상속)
public class Student extends Human {
    String studentId;

    // 생성자
    public Student(String name, String studentId) {
        super(name); // 부모 클래스의 생성자 호출
        this.studentId = studentId;
    }

    // introduce 메서드 오버라이딩
    @Override
    void introduce() {
        System.out.println("저의 이름은 " + name + "이고, 학생 ID는 " + studentId + "입니다.");
    }
}
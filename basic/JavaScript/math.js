// 사칙연산 함수 정의
export function add(a, b) {
    return a + b;
}

export function subtract(a, b) {
    return a - b;
}

export function multiply(a, b) {
    return a * b;
}

export function divide(a, b) {
    if (b === 0) {
        alert("0으로 나눌 수 없습니다.");
        return "NaN";
    }
    return a / b;
}

// 입력값 가져오는 함수 (수정: 입력값 검증 추가)
function getInputValues() {
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    // 입력값이 비어있거나 숫자가 아닌 경우 NaN 처리
    if (isNaN(num1) || isNaN(num2)) {
        alert("숫자를 모두 입력해 주세요.");
        return null;
    }
    return { num1, num2 };
}

// 결과 출력 함수
function displayResult(result) {
    console.log("결과:", result); // 디버깅용 콘솔 출력
    document.getElementById('result').innerText = `결과: ${result}`;
}

// 이벤트 처리 (수정: 입력값 검증 추가)
document.getElementById('add').addEventListener('click', () => {
    const values = getInputValues();
    if (values) displayResult(add(values.num1, values.num2));
});

document.getElementById('subtract').addEventListener('click', () => {
    const values = getInputValues();
    if (values) displayResult(subtract(values.num1, values.num2));
});

document.getElementById('multiply').addEventListener('click', () => {
    const values = getInputValues();
    if (values) displayResult(multiply(values.num1, values.num2));
});

document.getElementById('divide').addEventListener('click', () => {
    const values = getInputValues();
    if (values) displayResult(divide(values.num1, values.num2));
});
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault(); // 폼 제출 시 페이지 새로 고침 방지

    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    // 입력값 검증
    if (!email) {
        alert("Email를 채워 주세요.");
        return;
    }
    else if (!password) {
        alert("Password를 채워 주세요.");
        return;
    }

    // 메시지 표시
    const message = `로그인 성공!<br>이메일: ${email}`;
    document.getElementById('message').innerHTML = message;
});

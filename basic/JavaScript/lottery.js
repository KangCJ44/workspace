// lottery.js

// 추첨 결과를 담는 함수
function drawLottery() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            // 50% 확률로 당첨 또는 실패
            const isWinner = Math.random() < 0.5;
            if (isWinner) {
                resolve("당첨 되었습니다.");
            } else {
                reject("꽝! 다음 기회에...");
            }
        }, 1000); // 1초 후에 결과 발표
    });
}

// 추첨 버튼 클릭 시 처리
document.getElementById("drawButton").addEventListener("click", async () => {
    // 메시지 표시
    document.getElementById("message").innerText = "1초 후에 당첨 결과가 발표됩니다.";
    document.getElementById("result").innerHTML = ''; // 이전 결과 삭제

    drawButton.disabled = true;
    // async/await를 사용하여 추첨 결과를 기다림
    try {
        const message = await drawLottery();
        // 당첨된 경우 파란색
        document.getElementById("result").innerHTML = `<span class="win">${message}</span>`;
    } catch (message) {
        // 실패한 경우 빨간색
        document.getElementById("result").innerHTML = `<span class="lose">${message}</span>`;
    } finally{
        drawButton.disabled = false;
    }

    // // 추첨 시작
    // drawLottery()
    //     .then((message) => {
    //         // 당첨된 경우 파란색
    //         document.getElementById("result").innerHTML = `<span class="win">${message}</span>`;
    //     })
    //     .catch((message) => {
    //         // 실패한 경우 빨간색
    //         document.getElementById("result").innerHTML = `<span class="lose">${message}</span>`;
    //     });
});

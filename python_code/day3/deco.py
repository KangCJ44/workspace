# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# SKALA
# 작성 목적 : 데코레이터 활용
# 작성자 : 강창진
# 작성일 : 2025-03-06
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

"""
이 모듈은 Python 데코레이터를 활용하여 함수 실행 시간을 로그로 기록
"""

import functools
import time


def log_time(func):
    """
    주어진 함수를 감싸 실행 시간을 측정하고, 이를 로그 파일에 저장하는 데코레이터.

    Args:
        func (Callable): 실행 시간을 측정할 대상 함수.

    Returns:
        Callable: 실행 시간을 기록하는 래퍼 함수.
    """
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        start = time.time()
        func(*args, **kwargs)
        end = time.time()

        # 로그 메시지 생성
        log_message = f"실행 시간 : {end - start:.6f}초"

        # 텍스트 파일로 로그 저장 (UTF-8 인코딩 명시)
        with open("execution_log.txt", "a", encoding="utf-8") as log_file:
            log_file.write(log_message + "\n")

    return wrapper


@log_time
def example_function():
    """
    2초 동안 대기한 후 '작업 완료!'를 출력합니다.
    """
    time.sleep(2)
    print("작업 완료!")


example_function()

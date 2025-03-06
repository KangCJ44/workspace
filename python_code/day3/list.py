# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# SKALA
# 작성 목적 : 리스트와 Numpy 배열 연산 속도 비교
# 작성자 : 강창진
# 작성일 : 2025-03-06
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

"""
리스트와 NumPy 배열의 연산 속도 비교
"""

import time
import numpy as np

SIZE = 1_000_000
numbers_list = list(range(SIZE))
numbers_np = np.arange(SIZE)

# 리스트 시간 계산
start_time = time.time()
numbers_list = [x ** 2 for x in numbers_list]
list_time = time.time() - start_time

# Numpy 배열 시간 계산
start_time = time.time()
numbers_np = numbers_np ** 2
np_time = time.time() - start_time

# 결과 출력
print(f"리스트 연산 시간: {list_time:.6f}")
print(f"Numpy 연산 시간: {np_time:.6f}")

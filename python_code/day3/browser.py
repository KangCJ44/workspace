# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# SKALA
# 작성 목적 : 뒤로가기 구현
# 작성자 : 강창진
# 작성일 : 2025-03-06
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

from collections import deque


def browser_navigation(commands):
    back_stack = deque()
    current = None

    for command in commands:
        if command == "back":
            if back_stack:
                current = back_stack.pop()
        else:
            if current:
                back_stack.append(current)
            current = command
        print(current)


commands = ["google.com", "facebook.com", "back", "twitter.com", "back"]
browser_navigation(commands)

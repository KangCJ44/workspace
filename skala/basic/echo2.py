import re

def validate_password(password):
    # 각 조건에 대한 정규 표현식 패턴
    has_lower = bool(re.search(r"[a-z]", password))  # 소문자 포함 여부
    has_upper = bool(re.search(r"[A-Z]", password))  # 대문자 포함 여부
    has_digit = bool(re.search(r"\d", password))     # 숫자 포함 여부
    has_special = bool(re.search(r"[^a-zA-Z0-9]", password))  # 특수 문자 포함 여부

    # 각 조건 검사 결과 출력
    print(f"🔹 소문자 포함: {'✅' if has_lower else '❌'}")
    print(f"🔹 대문자 포함: {'✅' if has_upper else '❌'}")
    print(f"🔹 숫자 포함: {'✅' if has_digit else '❌'}")
    print(f"🔹 특수 문자 포함: {'✅' if has_special else '❌'}")

    # 모든 조건을 만족하면 True 반환, 그렇지 않으면 False 반환
    return has_lower and has_upper and has_digit and has_special

# 사용자 입력 받기
user_password = input("비밀번호를 입력하세요: ")

# 검증 결과 출력
if validate_password(user_password):
    print("✅ 유효한 비밀번호입니다!")
else:
    print("❌ 비밀번호 조건을 만족하지 않습니다.")

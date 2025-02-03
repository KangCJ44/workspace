while True:  # 무한 루프 (반복 실행)
    user_input = input("문장을 입력하세요 ('!quit' 입력 시 종료): ")
    
    if user_input == "!quit":  # 종료 조건 확인
        print("프로그램을 종료합니다.")
        break  # while 반복문 종료
    
    print("입력한 문장은 : ", user_input)  # 입력한 문장 출력
# student.py

"""
Module for processing student scores.
"""

import csv

students = [
    {"name": "Alice", "scores": {"math": 85, "science": 90, "english": 88}},
    {"name": "Bob", "scores": {"math": 78, "science": 85, "english": 92}},
    {"name": "Charlie", "scores": {"math": 92, "science": 88, "english": 95}},
    {"name": "David", "scores": {"math": 76, "science": 82, "english": 79}},
    {"name": "Eve", "scores": {"math": 89, "science": 94, "english": 87}}
]


# 1 평균 계산 함수
def calculate_average_scores(students_list):
    """
    Calculate the average score for each subject across all students.
    """
    subject_totals = {"math": 0, "science": 0, "english": 0}
    num_students = len(students_list)

    # 학생들 점수 총합 계산
    for student in students_list:
        for subject, score in student["scores"].items():
            subject_totals[subject] += score

    # 평균 계산 후 반환
    subject_averages = {
        subject: total / num_students
        for subject, total in subject_totals.items()
    }
    return subject_averages


averages = calculate_average_scores(students)
for subject_name, avg in averages.items():
    print(f"{subject_name.capitalize()} Average: {avg:.2f}")


# 2 가장 높은 평균 점수와 가장 낮은 평균 점수 찾기
max_subject = max(averages, key=averages.get)
min_subject = min(averages, key=averages.get)

print(f"Highest Average Score: {max_subject.capitalize()} - "
      f"{averages[max_subject]:.2f}")
print(f"Lowest Average Score: {min_subject.capitalize()} - "
      f"{averages[min_subject]:.2f}")


# 3 학생별 평균 점수 계산 및 최저/최고 학생 찾기
def calculate_student_averages(students_list):
    """
    Calculate the average score for each student.
    """
    for student in students_list:
        avg_score = sum(student["scores"].values()) / len(student["scores"])
        student_avg_scores[student["name"]] = avg_score
    return student_avg_scores


# 학생별 평균 점수 계산 및 출력
student_avg_scores = calculate_student_averages(students)

# 평균 점수가 가장 높은 학생과 가장 낮은 학생 찾기
max_student = max(student_avg_scores, key=student_avg_scores.get)
min_student = min(student_avg_scores, key=student_avg_scores.get)

print(f"Highest Student Average: {max_student} - "
      f"{student_avg_scores[max_student]:.2f}")
print(f"Lowest Student Average: {min_student} - "
      f"{student_avg_scores[min_student]:.2f}")


# 4 90점 이상인 과목이 하나라도 있는 학생들의 이름을 리스트로 만들어 출력
def students_with_high_scores(students_list, threshold=90):
    """
    Return a list of students who have at least one score above the threshold.
    """
    high_score_students = []
    for student in students_list:
        if any(score >= threshold for score in student["scores"].values()):
            high_score_students.append(student["name"])
    return high_score_students


high_scorers = students_with_high_scores(students)
print("Students with at least one score of 90 or above:", high_scorers)


# 5 모든 과목에서 80점 이상을 받은 학생들의 이름을 리스트로 만들어 출력
def students_with_all_high_scores(students_list, threshold=80):
    """
    모든 과목에서 80점 이상을 받은 학생들의 이름을 리스트로 만들어 출력.
    """
    all_high_score_students = []
    for student in students_list:
        if all(score >= threshold for score in student["scores"].values()):
            all_high_score_students.append(student["name"])
    return all_high_score_students


all_high_scorers = students_with_all_high_scores(students)
print("Students with 80 or above in all subjects:", all_high_scorers)


# 6 각 학생의 성적 데이터를 CSV 파일로 저장
def save_student_scores_to_csv(students_list, filename="student_scores.csv"):
    """
    Save student scores to a CSV file.
    """
    fields = ["name", "math", "science", "english"]
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=fields)
        writer.writeheader()

        # 각 학생의 데이터를 한 줄씩 작성
        for student in students_list:
            row = {"name": student["name"], **student["scores"]}
            writer.writerow(row)


# 학생 성적 데이터를 CSV 파일로 저장
save_student_scores_to_csv(students)
print("Save 'student_scores.csv'.")


# 7 저장한 CSV 파일을 다시 읽어와서 각 과목별 최고 점수를 받은 학생의 이름을 출력
def read_student_scores_from_csv(filename="student_scores.csv"):
    """
    Read student scores from a CSV file.
    """
    students_list = []
    with open(filename, mode="r", encoding="utf-8") as file:
        reader = csv.DictReader(file)
        for row in reader:
            # 점수를 숫자로 변환하여 저장
            student = {
                "name": row["name"],
                "scores": {
                    "math": int(row["math"]),
                    "science": int(row["science"]),
                    "english": int(row["english"])
                }
            }
            students_list.append(student)
    return students_list


# 과목별로 최고 점수를 받은 학생 반환
def find_top_students(students_list):
    """
    Find the top scorer in each subject.
    """
    subject_max_scores = {"math": -1, "science": -1, "english": -1}
    subject_top_students = {"math": "", "science": "", "english": ""}

    for student in students_list:
        for subject, score in student["scores"].items():
            if score > subject_max_scores[subject]:
                subject_max_scores[subject] = score
                subject_top_students[subject] = student["name"]

    # 결과 출력
    for subject, student_name in subject_top_students.items():
        print(f"Top scorer in {subject.capitalize()}: {student_name}")


students_from_csv = read_student_scores_from_csv()
find_top_students(students_from_csv)

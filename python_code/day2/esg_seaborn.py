import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns 

# CSV 파일 읽기
file_path = "ESG_CO2_2021.csv"
df = pd.read_csv(file_path)

# 결측치 확인 및 처리
print("결측치 개수:")
print(df.isnull().sum())
df = df.fillna(df.mean())  # 평균값으로 결측치 대체

# 데이터 타입 확인 및 변환
print("\n데이터 타입:")
print(df.dtypes)

# 데이터 출력 (pandas)
print(df.head())

# 데이터 출력 (numpy)
numpy_array = df.to_numpy()
print(numpy_array[:5])

# matplotlib을 이용한 데이터 시각화
plt.figure(figsize=(10, 6))
plt.plot(df['year'], df.iloc[:, 1], label=df.columns[1], marker='o', linestyle='-')  # 'year' 컬럼을 x축으로 설정
plt.xlabel("Year")
plt.ylabel("Net Emission")
plt.title("Data Visualization")
plt.legend()
plt.grid()

# 그래프 저장
plt.savefig("data_visualization_matplotlib.png")
plt.show()

# seaborn을 이용한 데이터 시각화
plt.figure(figsize=(10, 6))
sns.lineplot(x=df['year'], y=df.iloc[:, 1], marker='o', label=df.columns[1])  # seaborn의 lineplot 사용
plt.xlabel("Year")
plt.ylabel("Net Emission")
plt.title("Data Visualization with Seaborn")
plt.legend()
plt.grid()

# 그래프 저장
plt.savefig("data_visualization_seaborn.png")
plt.show()

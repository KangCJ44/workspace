import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

# CSV 파일 읽기
file_path = "ESG_CO2_2021.csv"
df = pd.read_csv(file_path, encoding="utf-8")

# 결측치 확인 및 처리
print("결측치 개수:")
print(df.isnull().sum())
df = df.fillna(df.mean())  # 평균값으로 결측치 대체

# 데이터 타입 확인
print("\n데이터 타입:")
print(df.dtypes)

# 데이터 출력 (pandas)
print(df.head())

# 데이터 출력 (numpy)
numpy_array = df.to_numpy()
print(numpy_array[:5])

# Linear Regression을 위한 데이터 준비
X = df['year'].values.reshape(-1, 1)  # 연도를 feature로 사용
y = df['net emission'].values  # net emission 값을 target으로 사용

# 모델 훈련
model = LinearRegression()
model.fit(X, y)

# 5년 후 예측을 위해, 기존 연도 범위에 추가
future_years = np.arange(df['year'].max() + 1, df['year'].max() + 6).reshape(-1, 1)
predicted_emissions = model.predict(future_years)

df_predicted = pd.DataFrame({
    'year': future_years.flatten(),
    'net emission': predicted_emissions
})

# 데이터 합치기
df_combined = pd.concat([df[['year', 'net emission']], df_predicted], ignore_index=True)

# 기존 데이터와 예측 결과 시각화
plt.figure(figsize=(10, 6))
plt.plot(df_combined['year'], df_combined['net emission'], label='Actual Net Emissions', marker='o', linestyle='-', color='blue')

# 그래프 레이블 및 제목 설정
plt.xlabel("Year")
plt.ylabel("Net Emission")
plt.title("Data Visualization with Predictions")

# 범례 추가
plt.legend()
plt.grid()

# 그래프 저장
plt.savefig("data_visualization_with_prediction.png")

# 그래프 출력
plt.show()

# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# SKALA
# 작성목적 : ESG_2022 분석
# 작성팀 : DAY6(1조)
# 작성일 : 2025-03-05
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

# CSV 파일 로드 (사용자가 직접 업로드 필요)
file_path = "ESG_2022.csv"
df = pd.read_csv(file_path, encoding="utf-8")

# 결측치 처리 (평균값으로 대체)
df = df.fillna(df.mean())

# 2. 다중 선형 회귀 분석
X = df.drop(columns=['Total Emissions', 'year'])  # 독립 변수 (연도 제외)
y = df.drop(columns=['year'])  # 모든 종속 변수 (Total Emissions 포함)

# 학습 데이터 준비
X_train = X
y_train = y

# 모델 학습
models = {}
for column in y.columns:
    model = LinearRegression()
    model.fit(X_train, y_train[column])
    models[column] = model

# 향후 10년 예측 (향후 10년 예측)
future_years = np.arange(df['year'].max() + 1, df['year'].max() + 11).reshape(-1, 1)

# 향후 10년 예측을 위한 X 값 준비 (현재 데이터에서 연도만 바꿔서 예측)
future_data = X.iloc[-1].values.reshape(1, -1)  # 최신 데이터
predictions = {}

# 각 변수에 대해 예측값을 저장
for column, model in models.items():
    pred_values = []
    for year in future_years:
        # 모델에 맞는 특성만 사용하여 예측 수행
        pred_values.append(model.predict(future_data)[0])
    predictions[column] = pred_values

# 예측값을 DataFrame으로 변환
future_predictions_df = pd.DataFrame(future_years, columns=['year'])
for column, pred_values in predictions.items():
    future_predictions_df[column] = pred_values

# 예측 결과 출력
print(future_predictions_df)

# 1. 시각화 (연도별 데이터 변화 + 예측 데이터 포함)
columns_to_plot = ['Total Emissions', 'Net Emissions', 'Energy', 'fuel combustion', 'Dealing', 'Carbon dioxide transport and storage', 'mineral industry', 'chemical industry', 'metal industry', 'non-energy fuels', 'Electronics Industry', 'ozone-depleting']

plt.figure(figsize=(12, 6))
for col in columns_to_plot:
    plt.plot(df['year'], df[col], marker='o', linestyle='-', label=col)
    
# 예측 데이터 추가 (예측에 대한 범례는 제외)
for column in future_predictions_df.columns[1:]:
    plt.plot(future_predictions_df['year'], future_predictions_df[column], 'r--', marker='x')

plt.xlabel("Year")
plt.ylabel("Value")
plt.title("Trends of Emissions and Related Factors with Future Predictions")
plt.legend(loc='upper left')  # 범례 위치 설정 (예측 데이터 제외된 범례)
plt.grid(True)
plt.savefig("esg_2022_10year.png")
plt.show()

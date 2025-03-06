from wordcloud import WordCloud
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm

plt.rcParams['font.family'] = 'NanumGothic'
plt.rcParams['axes.unicode_minus'] = False

# 시스템에 설치된 폰트 찾기
fonts = [f.name for f in fm.fontManager.ttflist]
print("사용 가능한 폰트:", fonts)

korean_fonts = [f for f in fonts if 'Nanum' in f or 'Malgun' in f]
print("사용 가능한 한글 폰트:", korean_fonts)

if korean_fonts:
    font_path = fm.findfont(korean_fonts[0])
else:
    print("한글 폰트를 찾을 수 없습니다. 기본 폰트를 사용합니다.")
    font_path = None

# 샘플 텍스트 데이터
text = """
현대적인 디자인의 이 건물은 친환경 요소를 적극 도입했습니다.
태양광 패널과 우수 재활용 시스템을 갖추고 있으며,
자연 채광을 최대화하는 대형 창문이 특징입니다.
개방형 평면과 유연한 공간 구성으로 다양한 용도에 적합합니다.
"""

# 전처리: 불필요한 문자 제거
import re
text = re.sub(r'[^\w\s]', '', text)

# 워드클라우드 생성
wordcloud = WordCloud(width=800, height=400,
                      background_color='white',
                      font_path=font_path,
                      max_font_size=100).generate(text)

# 워드클라우드 이미지 저장
wordcloud.to_file("/wordcloud_image.png")

# matplotlib을 사용한 시각화 및 저장
plt.figure(figsize=(10, 5))
plt.imshow(wordcloud, interpolation='bilinear')
plt.axis('off')
plt.title('기본 워드클라우드', fontproperties=fm.FontProperties(fname=font_path))
plt.savefig("/wordcloud_plot.png", dpi=300, bbox_inches='tight')
plt.show()

print("워드클라우드 이미지가 저장되었습니다.")

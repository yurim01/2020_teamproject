#main
import cv2
import os
import numpy as np
import functions as fs
import modules

#이미지 불러오기
image=cv2.imread("image/8_drum.jpg", cv2.IMREAD_ANYCOLOR)

#전처리 1. 보표 영역 추출 및 그 외 노이즈 제거-modules-fs
masked_image = modules.remove_noise(image)

#전처리 2. 오선 제거
removed_image, staves = modules.remove_staves(masked_image)

#전처리 3. 악보 이미지 정규화
normalized_image, staves = modules.normalization(removed_image, staves, 10)

#이미지 띄우기
cv2.imshow('image', masked_image)
cv2.waitKey(0)
cv2.destroyAllWindows()

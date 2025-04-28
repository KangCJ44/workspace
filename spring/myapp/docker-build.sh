#!/bin/bash
NAME=sk002
IMAGE_NAME="myapp"
VERSION="1.0.0"

# Docker 이미지 빌드
docker build \
  --tag ${NAME}-${IMAGE_NAME}:${VERSION} \
  --file Dockerfile \
  ${IS_CACHE} .

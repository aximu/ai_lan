## FastAPI 后端:

进入 backend-fastapi 目录
创建并激活一个 Python 虚拟环境
运行 pip install -r requirements.txt
运行 uvicorn main:app --reload 启动 FastAPI 服务器
或者，你可以使用 Docker:

构建 Docker 镜像: docker build -t fastapi-language-learning .
运行容器: docker run -d -p 8000:8000 fastapi-language-learning
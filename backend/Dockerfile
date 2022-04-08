FROM python:3.7.8

WORKDIR /backend

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY . /backend

CMD ["python", "./run.py"]

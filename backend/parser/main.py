import os
from kafka import KafkaConsumer

TOPIC_NAME = os.environ['KAFKA_TOPIC_NAME']
BOOTSTRAP_SERVER = f"{os.environ['KAFKA_INTERNAL_HOST']}:{os.environ['KAFKA_INTERNAL_PORT']}"


def start_consumer():
    try:
        consumer = KafkaConsumer(TOPIC_NAME, bootstrap_servers=BOOTSTRAP_SERVER)
        for msg in consumer:
            print(msg)
    except Exception as e:
        print(e)


if __name__ == "__main__":
    start_consumer()

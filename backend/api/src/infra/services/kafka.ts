import { Kafka } from "kafkajs"

const { KAFKA_TOPIC_NAME, KAFKA_INTERNAL_HOST, KAFKA_INTERNAL_PORT } = process.env;

export default class KafkaService {
  client;
  constructor() {
    this.client = new Kafka({
      clientId: `${Buffer.from(KAFKA_TOPIC_NAME).toString('base64')}`,
      brokers: [`${KAFKA_INTERNAL_HOST}:${KAFKA_INTERNAL_PORT}`]
    })
  }

  producer() {
    return this.client.producer()
  }

  async send(data: object) {
    const producer = this.producer();
    await producer.connect();
    await producer.send({
      topic: KAFKA_TOPIC_NAME,
      messages: [ data ]
    })
  }

}
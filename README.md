# Kafka sample
Sample kafka and Spring boot

This sample in contains:
- docker-file containing kafka and zookeper images
- kafka configuration for sprint boot
- Small scheduler that sends messages every one second.
- Kafka Producer
- Kafka Consumer
- Kafka Stream (to upper case)


## The flow

1. On startaup message scheduler 
-> 
2. MuleKafkaProducer
->
3. MuleKafkaStream (toUpperCaseStream)
->
4. MulecodeKafkaListener ( prints the message)


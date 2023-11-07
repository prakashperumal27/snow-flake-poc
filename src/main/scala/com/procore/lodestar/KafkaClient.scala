package com.procore.lodestar

import org.apache.kafka.clients.producer._

import java.util.Properties

class KafkaClient {
    val topic = "master_companies_dev"
    val bootstrapServers = "pkc-n98pk.us-west-2.aws.confluent.cloud:9092"
    val props = new Properties()
        props.put("bootstrap.servers", bootstrapServers)
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("security.protocol", "SASL_SSL")
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='AAXODADFPBXH27TE' password='Kf7URGxjbNYuyA5y5dV/5vGSqR0Ya4abJihpmLmICwItckNo17PVLejhMzuvUM4Z';")
        props.put("sasl.mechanism", "PLAIN")
        props.put("client.dns.lookup", "use_all_dns_ips")
        props.put("session.timeout.ms", 45000)
        props.put("acks", "all")

    def produceToKafka(key: String, message: String) = {
        val producer = new KafkaProducer[String, String](props)
        try {
            val record = new ProducerRecord[String, String](topic, key, message)
            producer.send(record)
        } finally {
            producer.close()
        }
    }

}

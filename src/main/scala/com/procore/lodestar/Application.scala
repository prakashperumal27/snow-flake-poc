package com.procore.lodestar

import com.procore.lodestar.rulesEngine.RuleEngine
import com.procore.lodestar.transformers.Transformers
import play.api.libs.json.Json
import org.slf4j.LoggerFactory

object Application {

    private val snowflakeClient = new SnowflakeClient
    private val kafkaClient = new KafkaClient
    private val transformers = new Transformers
    private val ruleEngine = new RuleEngine

    val logger = LoggerFactory.getLogger(getClass)

    def main(args: Array[String]): Unit = {

        /** Fetch records from snowflake - only delta load using connector */
        val companyRows = snowflakeClient.fetchCompanies

        /** Apply Transformation ex. query result set to company objects */
        val companies = transformers.toCompany(companyRows)

        /** Apply rules for columns - validation */
        val validationResults = ruleEngine.validateCompanyList(companies)

        /** Validation result - data quality */
        validationResults.foreach { case (company, validationErrors) =>
            if (!validationErrors.isEmpty) {
                logger.error(s"Validation errors for company: ${company.company_name}, Procore Id: ${company.procore_id}:")
                validationErrors.foreach(error => logger.error(s"\t $error"))
            }
        }

        /** Producing message to Kafka */
        companies foreach (company => {
            val obj = Json.toJson(company).toString()
            //kafkaClient.produceToKafka(company.procore_id, obj)
        })
    }


}



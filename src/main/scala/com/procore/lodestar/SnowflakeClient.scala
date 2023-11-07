package com.procore.lodestar
import com.snowflake.snowpark.Session

class SnowflakeClient {

    val snowflakeConf: Map[String, String] = Map(
      "URL" -> "https://procoreit.snowflakecomputing.com:443",
      "USER" -> "prakash.perumal-contractor@procore.com",
      "PASSWORD" -> "27@Jun@2023",
      "ROLE" -> "PDP_ENGINEER_ROLE",
      "WAREHOUSE" -> "LATENTVIEW_ENGINEER_LARGE_WH",
      "DB" -> "PDP_MDM_DEV_PRAKASH",
      "SCHEMA" -> "MASTER_COMPANIES"
    )

    val sfSession = Session.builder.configs(snowflakeConf).create

    val companiesQuery = "select * from master_companies_poc limit 10"

    def fetchCompanies = sfSession.sql(companiesQuery).collect()

}

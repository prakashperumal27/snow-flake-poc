package com.procore.lodestar
import com.snowflake.snowpark.Session

class SnowflakeClient {

    val snowflakeConf: Map[String, String] = Map(
      "URL" -> sys.env.get("account").getOrElse(),
      "USER" -> sys.env.get("username").getOrElse(),
      "PASSWORD" -> sys.env.get("password").getOrElse(),
      "ROLE" -> sys.env.get("role").getOrElse(),
      "WAREHOUSE" -> sys.env.get("warehouse").getOrElse(),
      "DB" -> sys.env.get("database").getOrElse(),
      "SCHEMA" -> sys.env.get("schema").getOrElse(),
    )

    val sfSession = Session.builder.configs(snowflakeConf).create

    val companiesQuery = "select * from master_companies_poc limit 10"

    def fetchCompanies = sfSession.sql(companiesQuery).collect()

}

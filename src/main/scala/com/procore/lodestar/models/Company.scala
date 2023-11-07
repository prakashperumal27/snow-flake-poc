package com.procore.lodestar.models

import play.api.libs.json.Json


case class Company(procore_id: String, company_id_src: String, ccd_id: String, company_name: String,
                   country_code: String, country_name: String, city_name: String, street_name: String, postal_code: String)

object Company {
  implicit val company_format = Json.format[Company]
}
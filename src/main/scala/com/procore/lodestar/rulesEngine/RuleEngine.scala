package com.procore.lodestar.rulesEngine

import com.procore.lodestar.models.Company

class RuleEngine {

    def validateCompanyList(companyList: List[Company]): List[(Company, List[String])] = {
        companyList.map { companyInfo =>
            val validationErrors = validateCompanyInfo(companyInfo)
            (companyInfo, validationErrors)
        }
    }

    def validateCompanyInfo(company: Company): List[String] = {
        var validationErrors: List[String] = List()
        // Rule 1: Validate Postal Code
        if (!isValidPostalCode(company.postal_code, company.country_code))
            validationErrors = s"Invalid Postal Code: ${company.postal_code} " :: validationErrors

        // Rule 2: Validate Country Name
        if (!isValidCountryName(company.country_name))
            validationErrors = s"Invalid country name: ${company.country_name} ":: validationErrors
        validationErrors
    }

    def isValidPostalCode(postalCode: String, country: String): Boolean =
        ("""^\d{5}$""".r).findFirstMatchIn(postalCode).isDefined

    def isValidCountryName(countryName: String): Boolean =
         Set("United States", "Canada", "United Kingdom", "Germany", "India").contains(countryName)
}


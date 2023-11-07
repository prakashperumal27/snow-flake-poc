package com.procore.lodestar.transformers

import com.procore.lodestar.Utils
import com.procore.lodestar.models.Company
import com.snowflake.snowpark.Row
import scala.reflect.runtime.universe.typeOf

class Transformers {

    def toCompany(rows: Array[Row]) =
        rows.map(row => Utils.rowToCaseClass(row, typeOf[Company]).asInstanceOf[Company]).toList

}

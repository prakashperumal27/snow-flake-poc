package com.procore.lodestar

import com.snowflake.snowpark.Row

import scala.reflect.runtime.universe.{MethodSymbol, Type, runtimeMirror}

object Utils {

  // Function to convert Snowflake Row to a case class dynamically
  def rowToCaseClass(row: Row, caseClassType: Type): Any = {
    val constructorMethod = caseClassType.decls.collect {
      case m: MethodSymbol if m.isPrimaryConstructor => m
    }.head.asMethod

    val constructorArgs = constructorMethod.paramLists.head.zipWithIndex.map { case (param, index) =>
      val columnValue = row.get(index)
      columnValue
    }
    val mirror = runtimeMirror(getClass.getClassLoader)
    val classSymbol = caseClassType.typeSymbol.asClass
    val classMirror = mirror.reflectClass(classSymbol)
    val constructor = classSymbol.primaryConstructor.asMethod
    val obj = classMirror.reflectConstructor(constructor)

    obj(constructorArgs: _*)
  }
}

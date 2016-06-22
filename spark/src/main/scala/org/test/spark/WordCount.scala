package org.test.spark


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.log4j.LogManager

object WordCount {
  def main(args: Array[String]) ={
    
    val log = LogManager.getRootLogger
//    val log = Logger.getLogger(getClass.getName)

    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    
    Logger.getLogger("org.test.spark.WordCount").setLevel(Level.WARN)
//    Logger.getLogger("akka").setLevel(Level.WARN)
//    Logger.getRootLogger().setLevel(Level.WARN)
    log.warn(getClass.getName)
    log.setLevel(Level.WARN)
    val test = sc.textFile("wordcout.txt")
    test.flatMap { line => 
      line.split(" ") 
      }
    .map { word =>
      (word,1)
      }
    .reduceByKey(_+_)
    .saveAsTextFile("WordCountOutput")
  }
}
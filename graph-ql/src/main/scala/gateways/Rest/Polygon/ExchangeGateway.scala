package gateways.Rest.Polygon

import entities.Stocks.Exchange
import gateways.Rest.RESTGateway
import io.circe.Json
import io.circe.optics.JsonPath.root
import io.circe.parser.parse

class ExchangeGateway extends RESTGateway[Exchange]{

  override def jsonValToEntity(json: Json):Exchange = {
    val id = root.id.number.getOption(json).get.toString
    val tape = root.tape.string.getOption(json).getOrElse("")
    val name = root.name.string.getOption(json).getOrElse("")
    val mic = root.mic.string.getOption(json).getOrElse("")
    val exchangeType = root.exchangeType.string.getOption(json).getOrElse("")
    val market = root.market.string.getOption(json).getOrElse("")
    val created = ""
    val updated = ""
    Exchange(id, created, updated, name, tape, mic, exchangeType, market)
  }


  override def baseURL: String = "https://api.polygon.io"

  override def route: String = "/v1/meta/exchanges"



  override def parameters: Map[String, String] = Map("apiKey"->"N_hg7UlQ85bbstyWLkbpdtSxR8XIQE3W")
}

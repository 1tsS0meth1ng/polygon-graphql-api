package gateways.Rest.Polygon

import entities.Stocks.{Code, Ticker}
import gateways.Rest.RESTGateway
import io.circe.{Json, JsonObject}
import io.circe.optics.JsonPath.root
import io.circe.parser.parse

class TickerGateway extends RESTGateway[Ticker] {

  override def baseURL: String = "https://api.polygon.io"

  override def route: String = "/v2/reference/tickers"

  override def parameters: Map[String, String] = Map("apiKey"->"N_hg7UlQ85bbstyWLkbpdtSxR8XIQE3W")

  override def jsonValToEntity(json: Json): Ticker = {
    val ticker = root.ticker.string.getOption(json).getOrElse("")
    val name = root.name.string.getOption(json).getOrElse("")
    val market = root.market.string.getOption(json).getOrElse("")
    val locale = root.locale.string.getOption(json).getOrElse("")
    val typeValue = root.selectDynamic("type").string.getOption(json).getOrElse("")
    val currency = root.currency.string.getOption(json).getOrElse("")
    val active = root.active.boolean.getOption(json).getOrElse(false)
    val primaryExch = root.primaryExch.string.getOption(json).getOrElse("")
    val updated = root.updated.string.getOption(json).getOrElse("")
    val codes= root.codes.obj.getOption(json).getOrElse(JsonObject())
    val url = root.url.string.getOption(json).getOrElse("")

    Ticker("","",ticker,name,market,locale,typeValue,currency,active = active,primaryExch,updated,jsonValToCode(codes),url)
  }

  private def jsonValToCode(json: JsonObject): Seq[Code] = {
    val test = json.toMap
    test.map( value  => {
      val name = value._1
      val valueOf = value._2.asString.get
      Code(name, valueOf)
    }).toSeq

  }

  override def jsonListToEntityList(jsonString: String): Seq[Ticker] = {
    parse(jsonString) match {
      case Right(json) => {
        val array = root.tickers.arr
        array.getOption(json).get.map(jsonValToEntity)
      }
      case _ => Seq[Ticker]()
    }
  }
}

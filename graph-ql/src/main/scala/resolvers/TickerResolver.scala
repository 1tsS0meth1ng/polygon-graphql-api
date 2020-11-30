package resolvers

import gateways.Rest.Polygon.TickerGateway
import com.softwaremill.macwire._
import entities.Stocks.Ticker

import scala.concurrent.Future

class TickerResolver {
  lazy val tickerGateway: TickerGateway = wire[TickerGateway]
  def tickers(perPage: Int, page: Int, sort: String = "ticker"): Future[Seq[Ticker]] = {
    val parameters = Map[String, String](("perpage",perPage.toString), ("page", page.toString), ("sort", sort))
    tickerGateway.readAll(parameterValues = parameters)
  }
}

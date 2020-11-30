package resolvers

import entities.Stocks.Exchange
import gateways.Rest.Polygon.ExchangeGateway
import com.softwaremill.macwire._

import scala.concurrent.Future

class StocksResolver {
  lazy val exchangeGateway: ExchangeGateway = wire[ExchangeGateway]
  val exchanges:Future[Seq[Exchange]] = exchangeGateway.readAll()
}

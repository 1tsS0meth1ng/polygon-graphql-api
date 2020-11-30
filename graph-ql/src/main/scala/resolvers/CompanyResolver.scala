package resolvers

import com.softwaremill.macwire.wire
import entities.Stocks.{Company, Ticker}
import gateways.Rest.Polygon.CompanyGateway

import scala.concurrent.Future

class CompanyResolver {
  lazy val companyGateway: CompanyGateway = wire[CompanyGateway]
  def company(id:String): Future[Option[Company]] = {
    val company = companyGateway.read(selectorParameters = Seq[String](id))
    company
  }
}

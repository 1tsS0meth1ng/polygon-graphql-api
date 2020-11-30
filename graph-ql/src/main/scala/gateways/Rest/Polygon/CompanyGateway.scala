package gateways.Rest.Polygon

import entities.Stocks.Company
import gateways.Rest.RESTGateway
import io.circe.optics.JsonPath.root
import io.circe.Json

class CompanyGateway extends RESTGateway[Company]{

  override def baseURL: String = "https://api.polygon.io"

  override def route: String = "/v1/meta/symbols/{symbol}/company"

  override def parameters: Map[String, String] = Map("apiKey"->"N_hg7UlQ85bbstyWLkbpdtSxR8XIQE3W")

  override def jsonValToEntity(json: Json): Company = {
    println(json.asString)
    val logo = root.logo.string.getOption(json).getOrElse("")
    val listdate = root.listdate.string.getOption(json).getOrElse("")
    val cik = root.cik.string.getOption(json).getOrElse("")
    val bloomberg = root.bloomberg.string.getOption(json).getOrElse("")
    val figi = root.figi.string.getOption(json).getOrElse("")
    val lei = root.lei.string.getOption(json).getOrElse("")
    val sic = root.sic.int.getOption(json).getOrElse(0)
    val country = root.country.string.getOption(json).getOrElse("")
    val industry = root.industry.string.getOption(json).getOrElse("")
    val sector = root.sector.string.getOption(json).getOrElse("")
    val marketcap = root.marketcap.int.getOption(json).getOrElse(0)
    val employees = root.employees.int.getOption(json).getOrElse(0)
    val phone = root.phone.string.getOption(json).getOrElse("")
    val ceo = root.ceo.string.getOption(json).getOrElse("")
    val url = root.url.string.getOption(json).getOrElse("")
    val description = root.description.string.getOption(json).getOrElse("")
    val exchange = root.exchange.string.getOption(json).getOrElse("")
    val name = root.name.string.getOption(json).getOrElse("")
    val symbol = root.symbol.string.getOption(json).getOrElse("")
    val exchangeSymbol = root.exchangeSymbol.string.getOption(json).getOrElse("")
    val hq_address:String = root.hq_address.string.getOption(json).getOrElse("")
    val hqState: String = root.hqState.string.getOption(json).getOrElse("")
    val hq_country = root.hq_country.string.getOption(json).getOrElse("")
    val typeOf: String = root.typeOf.string.getOption(json).getOrElse("")
    val updated: String = root.updated.string.getOption(json).getOrElse("")
    val tags: Seq[String] = root.tags.arr.getOption(json).map(x => x.map(y => y.asString.get)).getOrElse(Seq[String]())
    val similar: Seq[String] = root.similar.arr.getOption(json).map(x => x.map(y => y.asString.get)).getOrElse(Seq[String]())
    val active = root.active.boolean.getOption(json).getOrElse(false)
    Company("","",logo,listdate,cik,bloomberg,figi,lei, sic, country,industry,sector,marketcap,employees,phone,ceo,url,description,exchange,name,symbol,exchangeSymbol,hq_address,hqState,hq_country,typeOf,updated, tags, similar, active = active)
  }
}

package Types

import com.softwaremill.macwire.wire
import com.sun.jdi.IntegerType
import entities.Stocks.{Code, Company, Exchange, Ticker}
import resolvers.{CompanyResolver, StocksResolver, TickerResolver}
import sangria.schema.{Action, Argument, BooleanType, Context, Field, IntType, ListType, ObjectType, OptionType, StringType, fields}

trait StockTypes {
  lazy val stocksResolver: StocksResolver = wire[StocksResolver]
  lazy val tickerResolver: TickerResolver = wire[TickerResolver]
  lazy val companyResolver: CompanyResolver = wire[CompanyResolver]

  lazy implicit val exchangeType: ObjectType[Unit, Exchange] = ObjectType(
    "Exchange",
    fields[Unit, Exchange](
      Field("id", StringType, Some("The exchange ID."), resolve = _.value.id),
      Field("name", StringType, Some("The exchange name."), resolve = _.value.name),
      Field("tape", StringType, Some("Tape id of the exchange."), resolve = _.value.tape),
      Field("mic", StringType, Some("Market Identification Code."), resolve = _.value.mic),
      Field("exchangeType", StringType, Some("The type of exchange."), resolve = _.value.exchangeType),
      Field("market", StringType, Some("Market data type this exchange contains."), resolve = _.value.market)
    )
  )
  // codes: Seq[Option[Code]],
  lazy implicit val tickerType: ObjectType[Unit, Ticker] = ObjectType(
    "Ticker",
    fields[Unit, Ticker](
      Field("id", StringType, Some("The Ticker ID."), resolve = _.value.id),
      Field("ticker", StringType, Some("An actual exchange symbol this item is traded under."), resolve = _.value.ticker),
      Field("name", StringType, Some("Name of the item."), resolve = _.value.name),
      Field("market", StringType, Some("The market id."), resolve = _.value.market),
      Field("locale", StringType, Some("The locale id."), resolve = _.value.locale),
      Field("typeValue", StringType, Some("The type of ticker."), resolve = _.value.typeValue),
      Field("currency", StringType, Some("The currency of the ticker."), resolve = _.value.currency),
      Field("active", BooleanType, Some("Is the ticker active?"), resolve = _.value.active),
      Field("primaryExchange", StringType, Some("The primary exchange the ticker runs on."), resolve = _.value.primaryExch),
      Field("updated", StringType, Some("The last time the ticker was updated"), resolve = _.value.updated),
      Field("url", StringType, Some("URL of this symbol. Use this to get this symbols endpoints."), resolve = _.value.url),
      Field("codes", ListType(codeType), Some("The codes related to the ticker."), resolve = _.value.codes),
      Field("company", OptionType(companyType), Some("The company."), resolve = x => companyResolver.company(x.value.ticker))
    )
  )
  lazy implicit val codeType: ObjectType[Unit, Code] = ObjectType(
    "Code",
    fields[Unit, Code](
      Field("name", StringType, Some("The code name."), resolve = _.value.name),
      Field("value", StringType, Some("The value of the code."), resolve = _.value.value))
  )
  lazy implicit val companyType: ObjectType[Unit, Company] = ObjectType(
    "CompanyInformation",
    fields[Unit, Company](
      Field("logo", StringType, Some("The companies url."), resolve = _.value.logo),
      Field("exchange", StringType, Some("Symbols primary exchange"), resolve = _.value.exchange),
      Field("exchangeSymbol", StringType, Some("The exchange code (id) of the symbol's primary exchange."), resolve = _.value.exchangeSymbol),
      Field("type", StringType, Some("The type or class of the security."), resolve = _.value.typeOf),
      Field("name", StringType, Some("The name of the company/entity."), resolve = _.value.name),
      Field("symbol", StringType, Some("The companies url."), resolve = _.value.symbol),
      Field("listDate", StringType, Some("The date that the symbol was listed on the exchange."), resolve = _.value.listdate),
      Field("cik", StringType, Some("TThe official CIK guid used for SEC database/filings."), resolve = _.value.cik),
      Field("bloomberg", StringType, Some("The Bloomberg guid for the symbol."), resolve = _.value.bloomberg),
      Field("figi", StringType, Some("The OpenFigi project guid for the symbol."), resolve = _.value.figi),
      Field("lei", StringType, Some("The Legal Entity Identifier (LEI) guid for the symbol."), resolve = _.value.lei),
      Field("sic", IntType, Some("Standard Industrial Classification (SIC) id for the symbol."), resolve = _.value.sic),
      Field("country", StringType, Some("The country in which the company is registered."), resolve = _.value.country),
      Field("industry", StringType, Some("The industry in which the company operates."), resolve = _.value.industry),
      Field("sector", StringType, Some("The sector of the industry in which the symbol operates."), resolve = _.value.sector),
      Field("marketCap", IntType, Some("The current market cap for the company."), resolve = _.value.marketcap),
      Field("employees", IntType, Some("The approximate number of employees for the company."), resolve = _.value.employees),
      Field("phone", StringType, Some("The phone number for the company. This is usually a corporate contact number."), resolve = _.value.phone),
      Field("ceo", StringType, Some("The name of the company's current CEO."), resolve = _.value.ceo),
      Field("url", StringType, Some("The URL of the company's website."), resolve = _.value.url),
      Field("description", StringType, Some("A description of the company and what they do/offer."), resolve = _.value.description),
      Field("hqAddress", StringType, Some("The street address for the company's headquarters."), resolve = _.value.hq_address),
      Field("hqState", StringType, Some("The state in which the company's headquarters is located."), resolve = _.value.hq_state),
      Field("hqCountry", StringType, Some("The country in which the company's headquarters is located."), resolve = _.value.hq_country),
      Field("similar", ListType(StringType), Some("A list of ticker symbols for similar companies."), resolve = _.value.similar),
      Field("tags", ListType(StringType), Some("A list of words related to the company."), resolve = _.value.tags),
      Field("updated", StringType, Some("The last time this company record was updated."), resolve = _.value.updated),
      Field("active", BooleanType, Some("Indicates if the security is actively listed. If false, this means the company is no longer listed and cannot be traded."), resolve = _.value.active)),
  )
  val companyID: Argument[String] = Argument("companyID", StringType, description = "ID of the company")
}

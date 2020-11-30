package entities.Stocks

import entities.Entity

case class Ticker (
                    id: String,
                    created: String,
                    ticker: String,
                    name: String,
                    market: String,
                    locale: String,
                    typeValue: String,
                    currency: String,
                    active: Boolean,
                    primaryExch: String,
                    updated: String,
                    codes: Seq[Code],
                    url: String) extends Entity

case class Code (name: String,
                 value: String)

# investment-drip-tracker
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker?ref=badge_shield)

Java (Spring boot) application to help you tracking DRIP (dividend reinvestment plan)

# Required API key
This software is powered by the Alphavantage API (to get current exchange rate, company overview details) for stocks 
exchanged from New York Stock Exchange and the Nasdaq. 

You can claim your free API key [here](https://www.alphavantage.co/support/#api-key) and edit both application.properties files to add your own API key.

**Remarks**

The free API key allows up to 5 API requests per minute and 500 requests per day. Since this is a homebrew project, this limitation should not be an issue. You can purchase a [premium plan](https://www.alphavantage.co/premium/) if you want to increase the number of requests per minute.

For stocks from the TSX, another approach is used to gather stock the information needed to track drip. The limitation mentioned above doesn't apply in that use case/scenario.

# Prerequisite to build the application

* Java 8
* Lombok - Make sure to install [Lombok plugin](https://www.baeldung.com/lombok-ide) on your IDE.

##Donate##

**Please help keep this project alive!** Donations are welcome and will go toward the further development of this project.
Any contribution is highly appreciated.

[paypal.me/pbelanger81](https://paypal.me/pbelanger81)

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker?ref=badge_large)
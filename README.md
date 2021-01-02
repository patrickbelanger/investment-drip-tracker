# investment-drip-tracker
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker?ref=badge_shield)

Java (Spring boot) application to help you tracking DRIP (dividend reinvestment plan)

# Required API key
This software is powered by the Alphavantage API (to get current exchange rate, company overview details).

You can claim your free API key [here](https://www.alphavantage.co/support/#api-key) and edit both application.properties file to add your own API key.

**Remark**

The free API key allows up to 5 API requests per minute and 500 requests per day. Since this is a homebrew project, this limitation implies a "forced" loading time to properly updates your portfolio. You can purchase a [premium plan](https://www.alphavantage.co/premium/) if you want to increase the number of requests per minute.

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpatrickbelanger%2Finvestment-drip-tracker?ref=badge_large)
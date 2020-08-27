import React, { useEffect, useState } from "react";
import Ticker from "react-ticker";

/// /////////////////////////////////////
/// /////////////////////////////////////
// This part is just to mimic API Calls
/// /////////////////////////////////////
/// /////////////////////////////////////
/// /////////////////////////////////////
const SERVICE_URL = "http://localhost:8080/api";

function getRandomIndex(min, max) {
  var offset = min;
  var range = max - min + 1;
  var randomNumber = Math.floor(Math.random() * range) + offset;
  return randomNumber;
}

const rates = [
  "Apple: 192.79",
  "Citigroup: 64.68",
  "General Electric: 10.10",
  "Google: 1.191",
  "Microsoft: 118.71",
];


class TickerFeed extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rates: [],
      config: {
        type: "line",
        series: [
          {
            values: [],
          },
        ],
      },
      trades: [],
    };
  }
  GetRatesFromAPI = () => {
    // I am using the new React Hooks API here for brevity
    //const [rate, setRate] = useState("");
    const rate = this.state.rates;

    return rate ? (
      <p className="rate">{rate} +++ </p>
    ) : (
      <p className="rate rate--placeholder">Placeholder</p>
    );
  };
  componentWillReceiveProps(nextProps) {
    fetch(SERVICE_URL + "/tickers")
      .then((data) => data.json())
      .then((data) => this.setState({ rates: data }))
      .catch((error) => {
        console.log("error:", error);
      });
  }
  componentDidMount() {
    fetch(SERVICE_URL + "/tickers")
      .then((data) => data.json())
      .then((data) => this.setState({ rates: data }))
      .catch((error) => {
        console.log("error:", error);
      });
  }
  render() {
    return (
      <Ticker offset="run-in" speed={10}>
        {() => <this.GetRatesFromAPI />}
      </Ticker>
    );
  }
}

export default TickerFeed;

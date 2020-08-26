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

function MakeFakeAPICall() {
  const number = getRandomIndex(0, 4);
  return new Promise((resolve) => {
    window.setTimeout(() => {
      resolve(rates[number]);
    }, 500);
  });
}
/// /////////////////////////////////////
/// /////////////////////////////////////
/// /////////////////////////////////////

const GetRatesFromAPI = () => {
  // I am using the new React Hooks API here for brevity
  const [rate, setRate] = useState("");
  useEffect(() => {
    async function fetchData() {
      const rateFromAPI = await MakeFakeAPICall();
      setRate(rateFromAPI);
    }
    fetchData();
  }, []);
  /*useEffect(() =>
    fetch(SERVICE_URL + "/tickers")
      .then((data) => data.json())
      .then((data) => console.log(data.json()))
      //.then((data) => this.setState({ rates: data }))
      .catch((error) => {
        console.log("error:", error);
      })
  );*/
  // A placeholder is needed, to tell react-ticker, that width and height might have changed
  // It uses MutationObserver internally
  return rate ? (
    <p className="rate">{rate} +++ </p>
  ) : (
    <p className="rate rate--placeholder">Placeholder</p>
  );
};

const TickerFeed = () => {
  /*useEffect(() =>
    fetch(SERVICE_URL + "/tickers")
      .then((data) => data.json())
      .then((data) => console.log(data.json()))
      //.then((data) => this.setState({ rates: data }))
      .catch((error) => {
        console.log("error:", error);
      })
  );*/
  return (
    <Ticker offset="run-in" speed={10}>
      {() => <GetRatesFromAPI />}
    </Ticker>
  );
};

export default TickerFeed;

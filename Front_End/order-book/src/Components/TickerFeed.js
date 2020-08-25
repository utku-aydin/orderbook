import React, { useEffect, useState } from 'react'
import Ticker from 'react-ticker'

/// /////////////////////////////////////
/// /////////////////////////////////////
// This part is just to mimic API Calls
/// /////////////////////////////////////
/// /////////////////////////////////////
/// /////////////////////////////////////

function getRandomIndex (min, max) {
  var offset = min
  var range = (max - min) + 1
  var randomNumber = Math.floor(Math.random() * range) + offset
  return randomNumber
}

const rates = [
  'Apple: 192.79',
  'Citigroup: 64.68',
  'General Electric: 10.10',
  'Google: 1.191',
  'Microsoft: 118.71'
]

function MakeFakeAPICall () {
  const number = getRandomIndex(0, 4)
  return new Promise((resolve) => {
    window.setTimeout(() => {
      resolve(rates[number])
    }, 500)
  })
}
/// /////////////////////////////////////
/// /////////////////////////////////////
/// /////////////////////////////////////

const GetRatesFromAPI = () => {
  // I am using the new React Hooks API here for brevity
  const [rate, setRate] = useState('')
  useEffect(() => {
    async function fetchData () {
      const rateFromAPI = await MakeFakeAPICall()
      setRate(rateFromAPI)
    }
    fetchData()
  }, [])
  // A placeholder is needed, to tell react-ticker, that width and height might have changed
  // It uses MutationObserver internally
  return rate
    ? <p className='rate'>{rate} +++ </p>
    : <p className='rate rate--placeholder'>Placeholder</p>
}

const TickerFeed = () => (
  <Ticker
    offset='run-in'
    speed={10}
  >
    {() => <GetRatesFromAPI />}
  </Ticker>
)

export default TickerFeed
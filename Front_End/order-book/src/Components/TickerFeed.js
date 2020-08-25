import React, { useState, useEffect } from 'react'
import Ticker from 'react-ticker'

const GetTradesFromAPI = () =>{
    const [trades,setTrades ] = useState("");
    useEffect(() => {
        async function fetchData(){
            const tradesFromAPI = await fetch("http://localhost:8080/api/buyOrders")
            console.log("Trades from API are " + tradesFromAPI)
            setTrades(tradesFromAPI);
        }
        fetchData();
    }, []);
    console.log("trades are:" + trades);
    return trades ? (
        <p style={{whiteSpace: "nowrap"}}>{trades} +++</p>
    ) : (<p style={{ visibility: "hidden"}}>Placeholder</p>
    );
     
};


const TickerFeed = () => (
    <Ticker>
        {({ index }) => (
            <>
                <h1>This is the Headline of element #{index}!</h1>
                <img src="www.my-image-source.com/" alt=""/>
            </>
        )}
    </Ticker>
)

export default TickerFeed
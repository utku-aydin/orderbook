import React from 'react';

import OrderBook from "./Components/OrderBook"
import OrderForm from "./Components/OrderForm"
import Graph from "./Components/Graph"
import TickerFeed from "./Components/TickerFeed"
import SessionHistory from "./Components/SessionHistory"

import { container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'



function App() {
  return (
    <container fluid>
      <Row>
        <TickerFeed />
      </Row>
      <Row>
        <Col>
          <h1 className="text-center">Order Book</h1>
        </Col>
      </Row>
      <Row>
        <OrderBook />
      </Row>
      <Row>
        <Col>
          <Graph />
        </Col>
        <Col>
          <OrderForm />
        </Col>
        <Col>
          <SessionHistory />
        </Col>


      </Row>

    </container>

  );
}

export default App;

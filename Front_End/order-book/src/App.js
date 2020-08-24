import React from 'react';

import OrderBook from "./Components/OrderBook"
import OrderForm from "./Components/OrderForm"
import Graph from "./Components/Graph"
import TickerFeed from "./Components/TickerFeed"
import SessionHistory from "./Components/SessionHistory"

import { Container, Row, Col } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'


const SERVICE_URL = "http://localhost:8080/api"

class App extends React.Component {

  state = {
    buyOrders: [],
    sellOrders: [],
  }

  componentDidMount() {
    console.log("App is now mounted. ")
    this.setState({ loading: true })
    console.log("Loading order Data data")
    fetch(SERVICE_URL + "/buyOrders")
      .then(data => data.json())
      .then(data => this.setState({ buyOrders: data }
      )).catch((error) => {
        console.log('error:', error);
      });
    fetch(SERVICE_URL + "/sellOrders")
      .then(data => data.json())
      .then(data => this.setState({ sellOrders: data }
      )).catch((error) => {
        console.log('error:', error);
      });
  }

  // handleCancelOrder = (event) => {
  //   if (event) event.preventDefault();
  //   let orderId = event.target.value;

  //   console.log(`sumbitting delete for order id ${orderId}`)

  //   fetch(SERVICE_URL+)
  // }

  render() {
    return (
      <Container fluid>
        <Row>
          <TickerFeed />
        </Row>
        <Row>
          <Col>
            <h1 className="text-center">Order Book</h1>
          </Col>
        </Row>
        <Row>
          <OrderBook sellOrders={this.state.sellOrders} buyOrders={this.state.buyOrders}  />
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

      </Container>

    );
  }
}
export default App;

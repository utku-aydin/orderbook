import React from "react";

import OrderBook from "./Components/OrderBook";
import OrderForm from "./Components/OrderForm";
import Graph from "./Components/Graph";
import TickerFeed from "./Components/TickerFeed";

import { Container, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
require("dotenv").config();
const SERVICE_URL = process.env.REACT_APP_SERVICE_URL;

class App extends React.Component {
  state = {
    buyOrders: [],
    sellOrders: [],
    stocks: [],
    users: [],
    newOrderData: {
      side: [],
      symbol: [],
      owner: [],
      quantity: 0,
      price: 0,
    },
    graphData: {
      interval: 5,
      count: 10,
    },
    trades: [],
  };

  componentDidMount() {
    this.loadOrderData();
    this.loadFormData();
    this.handleGraphDataSubmit();
  }

  handleGraphDataChange = (event) => {
    let inputName = event.target.name;
    let inputValue = event.target.value;
    let newGraphData = this.state.graphData;

    if (newGraphData.hasOwnProperty(inputName)) {
      newGraphData[inputName] = inputValue;
      this.setState({ graphData: newGraphData });
    }
  };

  handleGraphDataSubmit = (event) => {
    fetch(SERVICE_URL + "/interval/10/5")
      .then((data) => data.json())
      .then((data) => this.setState({ trades: data }))
      .catch((error) => {
        console.log("error:", error);
      });
  };

  handleAddFormChange = (name, event) => {
    let inputName = name;
    let inputValue = event.value;
    let label = event.label;
    let orderData = this.state.newOrderData;

    if (orderData.hasOwnProperty(inputName)) {
      orderData[inputName] = { label, inputValue };
      this.setState({ newOrderData: orderData });
    }
  };

  handleCancelOrder = (event) => {
    if (event) event.preventDefault();
    let version = event.target.dataset.version;
    let id = event.target.dataset.id;

    fetch(SERVICE_URL + "/order/", {
      method: "DELETE",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(event.target.dataset),
    })
      .then((data) => {
        this.loadOrderData();
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  handleUpdateOrder = (event) => {
    if (event) event.preventDefault();
    let version = event.target.dataset.version;
    let id = event.target.dataset.id;

    fetch(SERVICE_URL + "/order/", {
      method: "PUT",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(event.target.dataset),
    })
      .then((data) => {
        this.loadOrderData();
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  handleChangeNumber = (event) => {
    let inputName = event.target.name;
    let inputValue = event.target.value;

    let orderData = this.state.newOrderData;

    if (orderData.hasOwnProperty(inputName)) {
      orderData[inputName] = inputValue;
      this.setState({ newOrderData: orderData });
    }
  };

  handleOrderFormSubmit = (event) => {
    let newOrder = {
      price: this.state.newOrderData.price,
      order_size: this.state.newOrderData.quantity,
      number_matched: 0,
      side: this.state.newOrderData.side.inputValue,
      status: "ACTIVE",
      usr_id: this.state.newOrderData.owner.inputValue,
      stock_id: this.state.newOrderData.symbol.inputValue,
    };

    if (event) event.preventDefault();

    fetch(SERVICE_URL + "/order/", {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(newOrder),
    })
      .then((respose) => respose.json)
      .then((data) => {
        this.setState({
          newOrderData: {
            side: [],
            symbol: [],
            owner: [],
            quantity: 0,
            price: 0,
          },
        });
        this.loadOrderData();
      })
      .catch((error) => {
        console.log("Add Order - Error:");
        console.log(error);
      });
  };

  loadOrderData() {
    fetch(SERVICE_URL + "/buyOrders")
      .then((data) => data.json())
      .then((data) => this.setState({ buyOrders: data }))
      .catch((error) => {
        console.log("error:", error);
      });
    fetch(SERVICE_URL + "/sellOrders")
      .then((data) => data.json())
      .then((data) => this.setState({ sellOrders: data }))
      .catch((error) => {
        console.log("error:", error);
      });
  }

  loadFormData() {
    fetch(SERVICE_URL + "/stocks")
      .then((data) => data.json())
      .then((data) => this.setState({ stocks: data }))
      .catch((error) => {
        console.log("error:", error);
      });
    fetch(SERVICE_URL + "/users")
      .then((data) => data.json())
      .then((data) => this.setState({ users: data }))
      .catch((error) => {
        console.log("error:", error);
      });
  }

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
          <OrderBook
            sellOrders={this.state.sellOrders}
            buyOrders={this.state.buyOrders}
            cancelOrder={this.handleCancelOrder}
            updateOrder={this.handleUpdateOrder}
          />
        </Row>
        <Row>
          <Col>
            <Graph
              graphData={this.state.graphData}
              trades={this.state.trades}
              handleGraphDataChange={this.handleGraphDataChange}
              handleGraphDataSubmit={this.handleGraphDataSubmit}
            />
          </Col>
          <Col>
            <OrderForm
              handleChangeNumber={this.handleChangeNumber}
              handleChange={this.handleAddFormChange}
              handleOrderFormSubmit={this.handleOrderFormSubmit}
              orderData={this.state.newOrderData}
              stocks={this.state.stocks}
              users={this.state.users}
            />
          </Col>
          <Col></Col>
        </Row>
      </Container>
    );
  }
}
export default App;

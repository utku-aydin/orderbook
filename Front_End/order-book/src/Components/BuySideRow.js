import React from "react";
import { Table, Button, OverlayTrigger, Tooltip } from "react-bootstrap";
import OrderAdjuster from "./OrderAdjuster";
import styled from "styled-components";

const RedText = styled.p`
  color: #ed1212;
`;

const GreenText = styled.p`
  color: #008000;
`;

class BuySideRow extends React.Component {
  state = {
    editOrder: {
      quantity: "",
      price: "",
    },
  };

  constructor(props) {
    super(props);
    this.state.editOrder.quantity =
      this.props.order.order_size - this.props.order.number_matched;
    this.state.editOrder.price = this.props.order.price;
    this.state.editOrder.total =
      this.props.order.price * this.state.editOrder.quantity;
  }

  componentDidUpdate(prevProps) {
    if (this.props.order.id !== prevProps.order.id) {
      //   let quantity = this.props.order.order_size - this.props.order.number_matched;
      //   let price = this.props.order.price;
      let newOrderData = {
        quantity: this.props.order.order_size - this.props.order.number_matched,
        price: this.props.order.price,
      };

      this.setState({ editOrder: newOrderData });
    }
  }

  resetEdit = () => {
    let newOrderData = {
      quantity: this.props.order.order_size - this.props.order.number_matched,
      price: this.props.order.price,
    };

    this.setState({ editOrder: newOrderData });
  };

  handleChangeNumber = (name, value, event) => {
    let inputName = name;
    let inputValue = value;
    if (event.shiftKey) {
      inputValue = value * 5;
    }

    let orderData = this.state.editOrder;

    if (orderData.hasOwnProperty(inputName)) {
      console.log(orderData[inputName]);
      console.log(inputValue);
      orderData[inputName] += inputValue;

      this.setState({ editOrder: orderData });
    }
  };

  render() {
    let {
      price,
      order_size,
      number_matched,
      side,
      stock,
      id,
      placed_at,
      status,
      user,
    } = this.props.order;
    let quantity = order_size - number_matched;
    let total = price * quantity;
    let sum = this.state.editOrder.price * this.state.editOrder.quantity;
    console.log("value of id in buyside row is :" + id.id + " " + id.version);

    let editTotal = (Math.round(sum * 100) / 100).toFixed(2);

    const renderTooltip = (props) => (
      <Tooltip id="button-tooltip" {...props}>
        Simple tooltip
      </Tooltip>
    );

    return (
      <tr>
        <td>
          <Button
            size="sm"
            data-id={id.id}
            data-version={id.version}
            onClick={this.props.cancelOrder}
          >
            Cancel
          </Button>
          {this.state.editOrder.quantity != quantity ||
          this.state.editOrder.price != price ? (
            <React.Fragment>
              {" "}
              <Button
                size="sm"
                data-id={id.id}
                data-version={id.version}
                data-price={this.state.editOrder.price}
                data-order_size={this.state.editOrder.quantity +number_matched}
                data-number_matched={number_matched}
                data-side={side}
                data-placed_at={placed_at}
                data-status={status}
                data-usr_id={user.id}
                data-stock_id={stock.id}
                onClick={this.props.updateOrder}
              >
                Update
              </Button>
              <Button size="sm" onClick={this.resetEdit}>
                Reset
              </Button>
            </React.Fragment>
          ) : null}
          <OverlayTrigger
            placement="right"
            delay={{ show: 250, hide: 400 }}
            overlay={renderTooltip}
          >
            <Button variant="success"></Button>
          </OverlayTrigger>
        </td>
        <td>{id.id}</td>
        <td>{stock.stock_symbol}</td>
        <td>
          {total}
          {editTotal > total ? <GreenText> {editTotal}</GreenText> : null}
          {editTotal < total ? <RedText> {editTotal}</RedText> : null}
        </td>
        <td>
          <OrderAdjuster
            value={quantity}
            editValue={this.state.editOrder.quantity}
            handleChangeNumber={this.handleChangeNumber}
            name={"quantity"}
            tickSize={1}
          />
        </td>
        <td>
          <OrderAdjuster
            value={price}
            editValue={this.state.editOrder.price}
            handleChangeNumber={this.handleChangeNumber}
            name={"price"}
            tickSize={0.01}
          />
        </td>
      </tr>
    );
  }
}

export default BuySideRow;

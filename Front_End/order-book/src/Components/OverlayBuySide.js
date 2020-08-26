import React from "react";
import { Table, Button } from "react-bootstrap";
import OrderAdjuster from "./OrderAdjuster";
import OverlayRow from "./OverlayRow";

const BuySideHeader = () => {
  return (
    <tr>
      <th>id</th>
      <th>Symbol</th>
      <th>total</th>
      <th>quantity</th>
      <th>price</th>
    </tr>
  );
};

// const BuySideRow = ({ order }) => {
//     let {price,order_size,number_matched,side,stock} = order;
//     let quantity = order_size - number_matched;

//     return (<tr>
//         <td><Button>Cancel</Button></td>
//         <td>{stock.stock_symbol}</td>
//         <td>{price * quantity}</td>
//         <td><OrderAdjuster value={quantity}/></td>
//         <td>{price}</td>

//     </tr>
//     );
// }

class OverlayBuySide extends React.Component {
  state = {
    displayAll: false,
  };

  constructor(props) {
    super(props);
    this.state.displayAll = false;
  }

  static defaultProps = {
    orders: [
      {
        price: 1.0,
        quantity: 100,
        symbol: "AMZN",
      },
      {
        price: 0.99,
        quantity: 110,
        symbol: "AMZN",
      },
      {
        price: 0.98,
        quantity: 120,
        symbol: "AMZN",
      },
    ],
  };

  changeDisplay() {
    // console.log(this.state.displayAll)
    this.setState({ displayAll: !this.state.displayAll });
  }

  render() {
    // Here is a debug method to moniter incoming contact data
    console.log("Rending Order Book:");
    console.log(this.props.orders);
    let orderSlice;
    if (!this.state.displayAll) {
      orderSlice = this.props.orders.slice(0, 5);
    } else {
      orderSlice = this.props.orders;
    }

    return (
      <React.Fragment>
        <h1 className="text-center">Order</h1>
        <Table striped bordered hover size="sm">
          <thead>
            <BuySideHeader />
          </thead>
          <tbody>
            {orderSlice.map((order, i) => {
              return (
                <OverlayRow
                  order={order}
                  key={i}
                  cancelOrder={this.props.cancelOrder}
                  updateOrder={this.props.updateOrder}
                />
              );
            })}
          </tbody>
        </Table>
      </React.Fragment>
    );
  }
}

export default OverlayBuySide;

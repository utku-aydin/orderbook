import React from "react";
import { Table, Button } from "react-bootstrap";
import SellSideRow from "./SellSideRow"


const SellSideHeader = () => {
    return (
        <tr>
            <th>price</th>
            <th>quantity</th>
            <th>total</th>
            <th>Symbol</th>
            <th>id</th>
            <th>Cancel</th>
        </tr>
    );
}

// const SellSideRow = ({ order }) => {
//     let {price,order_size,number_matched,side,stock} = order;
//     let quantity = order_size - number_matched;

//     return (<tr>
//         <td>{price}</td>
//         <td>{quantity}</td>
//         <td>{price * quantity}</td>
//         <td>{stock.stock_symbol}</td>
//         <td><Button>Cancel</Button></td>
//     </tr>
//     );
// }

class SellSide extends React.Component {
    state = {
        displayAll: false
    }

    constructor(props){
        super(props);
        this.state.displayAll = false;
    }

    static defaultProps = {
        orders: [
            {
                "price": 1.00,
                "quantity": 100,
                "symbol": "AMZN"

            },
            {
                "price": 0.99,
                "quantity": 110,
                "symbol": "AMZN"

            },
            {
                "price": 0.98,
                "quantity": 120,
                "symbol": "AMZN"

            }
        ]
    }


    static defaultProps = {
        orders: [
            {
                "price": 1.01,
                "quantity": 100,
                "symbol": "AMZN"

            },
            {
                "price": 1.04,
                "quantity": 110,
                "symbol": "AMZN"

            },
            {
                "price": 1.07,
                "quantity": 120,
                "symbol": "AMZN"

            }
        ]
    }

    changeDisplay(){
        // console.log(this.state.displayAll)
         this.setState({displayAll:!this.state.displayAll})
     }


    render() {
        // Here is a debug method to moniter incoming contact data
        console.log("Rending Order Book:")
        console.log(this.props.orders)
        let orderSlice
        if(!this.state.displayAll){
         orderSlice = this.props.orders.slice(0,5);
        } else{
            orderSlice=this.props.orders;
        }



        return (
            <React.Fragment>
                <h1 className="text-center">SELL</h1>
            <Table striped bordered hover size="sm">
                <thead>
                    <SellSideHeader/>
                </thead>
                <tbody>
                {orderSlice.map((order, i) => {
                        return <SellSideRow order={order} key={i} cancelOrder={this.props.cancelOrder} updateOrder={this.props.updateOrder} />
                    })}

                </tbody>
            </Table>
            <Button onClick={ () => this.changeDisplay()}>{this.state.displayAll? <p>Show {this.props.orders.length -5} less orders</p> : <p>Show {this.props.orders.length -5} more orders</p> }</Button>
            </React.Fragment>)
    }
}

export default SellSide